import { createUserPlan, updateUserPlan } from '@/services/api';
import { UserPlan } from '@/types/user-plan';
import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';

interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: () => void;
    userPlan?: UserPlan | null;
}

const UserPlanFormModal = ({ open, onClose, userPlan, onSuccess }: Props) => {

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [price, setPrice] = useState(0.00);
    const [expMultiplier, setExpMultiplier] = useState(1);
    const [statMultiplier, setStatMultiplier] = useState(1);


    useEffect(() => {
        if (userPlan) {
            setName(userPlan.name || '');
            setDescription(userPlan.description || '');
            setPrice(userPlan.price || 0.00);
            setExpMultiplier(userPlan.expMultiplier || 1);
            setStatMultiplier(userPlan.statMultiplier || 1);

        } else {
            setName('');
            setDescription('');
            setPrice(0.00);
            setExpMultiplier(1);
            setStatMultiplier(1);
        }

    }, [userPlan]);

    const handleSubmit = async (e: React.FormEvent) => {

        e.preventDefault();

        const userPlanToSave: UserPlan = {
            id: userPlan?.id || null,
            name,
            description,
            price,
            expMultiplier,
            statMultiplier,
            createdAt: userPlan?.createdAt || null,
            updatedAt: userPlan?.updatedAt || null,
        }

        try {
            const savedUserPlan = userPlan
                ? await updateUserPlan(userPlanToSave)
                : await createUserPlan(userPlanToSave);

            toast.success(
                userPlan
                    ? `Successfully updated userPlan: ${savedUserPlan.name}`
                    : `Successfully created userPlan: ${savedUserPlan.name}`
            );

            onSuccess();
        } catch (error) {
            toast.error(
                userPlan
                    ? `Failed to update userPlan ${error}`
                    : `Failed to create userPlan ${error}`

            );
        } finally {
            onClose();
        }

    };

    const isSaveDisabled = !name || !description;


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
                }
            }}
        >
            <div className='modal-content'>
                <h2>{userPlan ? 'Edit User Plan' : 'Create New User Plan'}</h2>
                <form>
                    <div>
                        <label>User Plan Name:</label>
                        <input
                            type="text"
                            placeholder='Enter user plan name'
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>Description:</label>
                        <textarea
                            placeholder='Enter Description'
                            required
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>Price:</label>
                        <div className='price-input'>
                            <span>$</span>
                            <input
                                type='number'
                                required
                                min={0.00}
                                value={price.toFixed(2)}
                                onChange={(e) => setPrice(e.target.value ? parseFloat(e.target.value) : 0.00)}
                            />
                        </div>
                    </div>
                    <div>
                        <label>Experience Multiplier:</label>
                        <input
                            type='number'
                            required
                            min={0.00}
                            value={expMultiplier}
                            onChange={(e) => setExpMultiplier(e.target.value ? parseFloat(e.target.value) : 0.00)}
                        />
                    </div>
                    <div>
                        <label>Stat Point Multiplier:</label>
                        <input
                            type='number'
                            required
                            min={0.00}
                            value={statMultiplier}
                            onChange={(e) => setStatMultiplier(e.target.value ? parseFloat(e.target.value) : 0.00)}
                        />
                    </div>

                </form>
                <div>
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
                        {userPlan ? 'Update User Plan' : 'Save User Plan'}
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default UserPlanFormModal
