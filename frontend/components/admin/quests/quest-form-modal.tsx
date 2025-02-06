import { createQuest, getCategories, getStats, updateQuest } from '@/api/api';
import { Category } from '@/types/category';
import { Quest, QuestAndStatRewards, QuestDetails } from '@/types/quest';
import { Stat, StatReward } from '@/types/stat';
import { deletePhoto, uploadPhotoToFirebase } from '@/utils/firebase-storage';
import React, { useEffect, useState } from 'react';
import { FaTrash } from 'react-icons/fa';
import Modal from 'react-modal';
import { toast } from 'react-toastify';

interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: () => void;
    questDetails?: QuestDetails | null;
}

const QuestFormModal = ({ open, onClose, questDetails, onSuccess }: Props) => {

    const [name, setName] = useState(questDetails?.quest.name || '');
    const [description, setDescription] = useState(questDetails?.quest.description || '');
    const [baseExpReward, setBaseExpReward] = useState(questDetails?.quest.baseExpReward || 0);
    const [imageLink, setImageLink] = useState(questDetails?.quest.imageLink || '');
    const [imageFile, setImageFile] = useState<File | null>(null);
    const [uploading, setUploading] = useState(false);


    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<Category | null>(null);

    const [stats, setStats] = useState<Stat[]>([]);
    const [statRewards, setStatRewards] = useState<StatReward[]>([{ statId: null, reward: null }]);

    useEffect(() => {
        fetchData();
    }, []);

    useEffect(() => {
        if (questDetails) {
            setName(questDetails?.quest.name);
            setDescription(questDetails?.quest.description);
            setBaseExpReward(questDetails?.quest.baseExpReward);
            setImageLink(questDetails?.quest.imageLink);

            const category = categories.find(
                (cat) => cat.id === questDetails?.quest.categoryId
            );
            setSelectedCategory(category || null);

            setStatRewards(questDetails.questStatRewards.map(reward => ({
                statId: reward.statId,
                reward: reward.baseStatReward,
            })));

        } else {
            setName("");
            setDescription("");
            setBaseExpReward(0);
            setImageFile(null);
            setImageLink("");
            setSelectedCategory(null);
            setStatRewards([{ statId: null, reward: null }]);
        }
    }, [questDetails])


    const handleCategoryChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedId = parseInt(e.target.value, 10);
        const category = categories.find(cat => cat.id === selectedId) || null;
        setSelectedCategory(category);
    };

    const fetchData = async () => {
        try {
            const categoriesData = await getCategories();
            setCategories(categoriesData);

            const statsData = await getStats();
            setStats(statsData);
        } catch (error) {

        }
    }



    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        setUploading(true);

        let updatedImageLink = imageLink;

        if (imageFile) {
            updatedImageLink = await uploadPhotoToFirebase(imageFile, name, "habits");
        }

        const questData: Quest = {
            id: questDetails?.quest.id || null,
            name,
            description,
            categoryId: selectedCategory?.id!,
            baseExpReward,
            imageLink: updatedImageLink,
            createdAt: questDetails?.quest.createdAt || null,
            updatedAt: questDetails?.quest.updatedAt || null,
        }

        const questStatRewardData: QuestAndStatRewards = {
            quest: questData,
            statRewards,
        }

        try {
            const savedQuest = questDetails
                ? await updateQuest(questStatRewardData)
                : await createQuest(questStatRewardData);

            toast.success(
                questDetails
                    ? `Successfully updated quest: ${savedQuest?.quest.name}`
                    : `Successfully created quest: ${savedQuest?.quest.name}`);
            setUploading(false);
            if (imageFile && questDetails?.quest.imageLink) {
                await deletePhoto(questDetails.quest.imageLink);
            }
            onSuccess();
            setName("");
            setDescription("");
            setSelectedCategory(null);
            setImageFile(null);
            setImageLink("");
            setBaseExpReward(0);
            setStatRewards([]);
            onClose();

        } catch (error) {
            toast.error(
                questDetails
                    ? `Failed to update quest ${error}`
                    : `Failed to create quest ${error}`

            );

            // Deletes the newly uploaded icon if the backend fails to save stats.
            if (imageFile && updatedImageLink !== questDetails?.quest.imageLink) {
                await deletePhoto(updatedImageLink);
            }
            throw error;
        }

        console.log(questStatRewardData);
    }

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];

        if (!file) {
            alert("No file selected!");
            return;
        }

        const validTypes = ["image/png", "image/jpeg", "image/svg+xml"];
        if (!validTypes.includes(file.type)) {
            alert("Only PNG, JPEG, and SVG files are allowed.");
            return;
        }

        const maxSizeInMB = 10;
        if (file.size > maxSizeInMB * 1024 * 1024) {
            alert(`File size should not exceed ${maxSizeInMB} MB.`);
            return;
        }

        setImageFile(file);
    };

    const handleStatChange = (index: number, statId: number | null) => {
        const updatedRewards = [...statRewards];
        updatedRewards[index].statId = statId;
        setStatRewards(updatedRewards);
    };

    const handleRewardChange = (index: number, reward: number | null) => {
        const updatedRewards = [...statRewards];
        updatedRewards[index].reward = reward;
        setStatRewards(updatedRewards);
    };

    const addStatReward = () => {
        setStatRewards([...statRewards, { statId: null, reward: null }]);
    };

    const removeStatReward = (index: number) => {
        const updatedRewards = [...statRewards];
        updatedRewards.splice(index, 1);
        setStatRewards(updatedRewards);
    }

    const isSaveButtonDisabled = () => {
        const isAnyStatInvalid = statRewards.some((reward) => !reward.statId || reward.reward === null);
        return (
            !name.trim() ||
            !description.trim() ||
            !selectedCategory ||
            (!imageFile && !imageLink) ||
            isAnyStatInvalid ||
            uploading
        );
    };

    return (
        <Modal
            isOpen={open}
            onRequestClose={onClose}
            style={{
                overlay: {
                    backgroundColor: " rgba(0, 0, 0, 0.7)",
                    display: "flex", // Add flex display
                    justifyContent: "center", // Center horizontally
                    alignItems: "center",
                    zIndex: "1"
                },
                content: {
                    width: "auto",
                    height: "fit-content",
                    maxHeight: "80vh",
                    overflow: "auto",
                    padding: "0",
                    top: "50%", // Center vertically
                    left: "50%",
                    right: "auto",
                    bottom: "auto",
                    transform: "translate(-50%, -50%)",
                    border: "none",
                    background: "none",
                    borderRadius: "0",
                    boxShadow: "2px 2px 24px 1px rgba(0, 0, 0, 0.31)"
                }
            }}
        >
            <div className='modal-content'>
                <h2>{questDetails ? 'Edit Quest' : 'Create New Quest'}</h2>
                <form>
                    <div className='modal-content-form'>
                        <label>Quest Name:</label>
                        <input
                            type="text"
                            placeholder='Enter quest name'
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div className='modal-content-form'>
                        <label>Description:</label>
                        <textarea
                            placeholder='Enter Description'
                            required
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                    </div>
                    <div className='modal-content-form'>
                        <label>Base Experience Reward:</label>
                        <input
                            type='number'
                            required
                            min={0}
                            value={baseExpReward}
                            onChange={(e) => setBaseExpReward(e.target.value ? parseFloat(e.target.value) : 0)}
                        />
                    </div>
                    <div className='modal-content-form'>
                        <label>Category:</label>
                        <select
                            required
                            value={selectedCategory?.id?.toString() || ''}
                            onChange={handleCategoryChange}
                        >
                            <option value="">Select a category</option>
                            {categories.map((category) => (
                                <option key={category.id} value={category.id?.toString() || ""}>
                                    {category.name}
                                </option>
                            ))}
                        </select>
                    </div>
                    <div className='modal-content-form'>
                        <label>Image:</label>
                        {imageLink && !imageFile && (
                            <div>
                                <p>Current Icon:</p>
                                <img
                                    src={imageLink}
                                    alt="Current Icon"
                                    style={{ width: "100px", height: "100px" }}
                                />
                            </div>
                        )}
                        <input
                            type="file"
                            id="icon"
                            accept="image/png, image/jpeg"
                            onChange={handleFileChange}
                        />
                        {imageFile && <p>Selected File: {imageFile.name}</p>}
                    </div>
                    <div className='modal-content-stat'>
                        <label>Stats and Rewards:</label>
                        {statRewards.map((statReward, index) => {

                            const availableStats = stats.filter(stat =>
                                !statRewards.some(sr => sr.statId === stat.id && stat.id !== statReward.statId)
                            );

                            return (
                                <div className='stat-selector' key={index} >
                                    <select
                                        value={statReward.statId || ''}
                                        onChange={(e) =>
                                            handleStatChange(index, e.target.value ? parseInt(e.target.value, 10) : null)
                                        }
                                    >
                                        <option value="">Select a stat</option>
                                        {availableStats.map((stat) => (
                                            <option key={stat.id} value={stat.id !== null ? stat.id : ''}>
                                                {stat.name}
                                            </option>
                                        ))}
                                    </select>
                                    <input
                                        type="number"
                                        min={0}
                                        placeholder="Enter reward"
                                        value={statReward.reward || ''}
                                        disabled={!statReward.statId}
                                        onChange={(e) =>
                                            handleRewardChange(index, e.target.value ? parseFloat(e.target.value) : null)
                                        }
                                    />
                                    <button type="button" onClick={() => removeStatReward(index)}>
                                        <FaTrash style={{ fontSize: "2rem" }} />
                                    </button>
                                </div>
                            );
                        })}
                        <button type="button" onClick={addStatReward}>
                            Add Stat
                        </button>
                    </div>

                </form>
                <div className='modal-button-group'>
                    <button
                        style={{ marginRight: "10px" }}
                        className='action-button-secondary'
                        onClick={onClose}>
                        Cancel
                    </button>
                    <button
                        type='submit'
                        className='action-button-primary'
                        onClick={handleSubmit}
                        disabled={isSaveButtonDisabled()}
                    >
                        {uploading ? "Uploading..." : (questDetails ? 'Update Quest' : 'Save Quest')}
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default QuestFormModal
