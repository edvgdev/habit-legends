import { createStat, updateStat } from '@/api/api';
import app from '@/services/firebase-config';
import { Stat } from '@/types/stat';
import { deletePhoto, uploadPhotoToFirebase } from '@/utils/firebase-storage';
import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';

interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: () => void;
    stat?: Stat | null;
}

const StatFormModal = ({ open, onClose, stat, onSuccess }: Props) => {

    const [name, setName] = useState(stat?.name || "");
    const [description, setDescription] = useState(stat?.description || "");
    const [iconUrl, setIconUrl] = useState(stat?.icon || "");
    const [icon, setIcon] = useState<File | null>(null);
    const [uploading, setUploading] = useState(false);

    useEffect(() => {
        if (stat) {
            setName(stat.name);
            setDescription(stat.description);
            setIconUrl(stat.icon);
        } else {
            setName("");
            setDescription("");
            setIconUrl("");
            setIcon(null);
        }
    }, [stat])

    const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0];

        if (!file) {
            alert("No file selected!");
            return;
        }

        const validTypes = ["image/png", "image/jpeg"];
        if (!validTypes.includes(file.type)) {
            alert("Only PNG and JPEG files are allowed.");
            return;
        }

        const maxSizeInMB = 5;
        if (file.size > maxSizeInMB * 1024 * 1024) {
            alert(`File size should not exceed ${maxSizeInMB} MB.`);
            return;
        }

        setIcon(file); // Save the valid file
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        setUploading(true);

        let updatedIconUrl = iconUrl;

        if (icon) {
            updatedIconUrl = await uploadPhotoToFirebase(icon, name, "icons");
        }

        const statData = {
            id: stat?.id || null,
            name,
            description,
            icon: updatedIconUrl,
            createdAt: stat?.createdAt || null
        }

        try {

            const savedStat = stat
                ? await updateStat(statData)
                : await createStat(statData);

            toast.success(
                stat
                    ? `Successfully updated stat: ${savedStat.name}`
                    : `Successfully created stat: ${savedStat.name}`
            );
        } catch (error) {
            toast.error(
                stat
                    ? `Failed to update stat ${error}`
                    : `Failed to create stat ${error}`

            );
            // Deletes the newly uploaded icon if the backend fails to save stats.
            if (icon && updatedIconUrl !== stat?.icon) {
                await deletePhoto(updatedIconUrl);
            }
            throw error;
        } finally {
            setUploading(false);
            if (icon && stat?.icon) {
                await deletePhoto(stat.icon);
            }
            onSuccess();
            setName("");
            setDescription("");
            setIconUrl("");
            setIcon(null);
            onClose();
        }
    };

    const isSaveDisabled = !name || !description || (!icon && !iconUrl);

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
                <h2>{stat ? 'Edit Stat' : 'Create New Stat'}</h2>
                <form>
                    <div className='modal-content-form'>
                        <label>Stat Name:</label>
                        <input
                            type="text"
                            placeholder='Enter stat name'
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
                        <label>Icon:</label>
                        {iconUrl && !icon && (
                            <div>
                                <p>Current Icon:</p>
                                <img
                                    src={iconUrl}
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
                        {icon && <p>Selected File: {icon.name}</p>}
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
                        disabled={isSaveDisabled}
                    >
                        {uploading ? "Uploading..." : (stat ? 'Update Stat' : 'Save Stat')}
                    </button>
                </div>
            </div>

        </Modal>
    )
}

export default StatFormModal
