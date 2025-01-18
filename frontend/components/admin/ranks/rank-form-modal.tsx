import { createRank, updateRank } from '@/api/api';
import { Rank } from '@/types/Rank';
import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';

interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: () => void;
    rank?: Rank | null;
}

const RankFormModal = ({ open, onClose, rank, onSuccess }: Props) => {

    const [name, setName] = useState('');
    const [minExp, setMinExp] = useState(0);

    useEffect(() => {
        if (rank) {
            setName(rank.name || '');
            setMinExp(rank.minExp || 0);
        } else {
            setName('');
            setMinExp(0);
        }

    }, [rank]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const rankToSave: Rank = {
            id: rank?.id || null,
            name,
            minExp,
        }

        try {
            const savedRank = rank
                ? await updateRank(rankToSave)
                : await createRank(rankToSave);

            toast.success(
                rank
                    ? `Successfully updated rank: ${savedRank.name}`
                    : `Successfully created rank: ${savedRank.name}`
            );

            onSuccess();
        } catch (error) {
            toast.error(
                rank
                    ? `Failed to update rank ${error}`
                    : `Failed to create rank ${error}`

            );
        } finally {
            onClose();
        }
    }

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
                <h2>{rank ? 'Edit Rank' : 'Create New Rank'}</h2>
                <form>
                    <div className='modal-content-form'>
                        <label>Rank Name:</label>
                        <input
                            type="text"
                            placeholder='Enter rank name'
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div className='modal-content-form'>
                        <label>Minimum Exp:</label>
                        <input
                            type='number'
                            required
                            min={0}
                            value={minExp}
                            onChange={(e) => setMinExp(e.target.value ? parseFloat(e.target.value) : 0)}
                        />
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
                        disabled={!name}
                    >
                        {rank ? 'Update Rank' : 'Save Rank'}
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default RankFormModal
