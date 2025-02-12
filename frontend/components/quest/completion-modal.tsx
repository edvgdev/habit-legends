import { submitQuestCompletion } from '@/api/api';
import { CompletionDetails, QuestCompletion, UserQuestDetails } from '@/types/quest';
import React, { useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';

interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: (completionDetails: CompletionDetails) => void;
    quest?: UserQuestDetails | null;
    userId: number;
}

const CompletionModal = ({ open, onClose, quest, onSuccess, userId }: Props) => {
    const [description, setDescription] = useState('');

    const handleSubmit = async () => {
        if (!userId && !quest) {
            return
        }

        const questCompletion: QuestCompletion = {
            id: null,
            userId,
            questId: quest?.questDetails.quest.id!,
            completedAt: null,
            description,
            expEarned: quest?.questDetails.quest.baseExpReward!
        }

        try {
            const savedCompletion = await submitQuestCompletion(questCompletion);
            toast.success(`${quest?.questDetails.quest.name} completed!`);
            console.log(savedCompletion);

            onSuccess(savedCompletion);
        } catch (error) {
            toast.success(`Failed to complete ${quest?.questDetails.quest.name}, ${error}`);
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
                    zIndex: "1000"
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
                <h2>{`Complete Quest: ${quest?.questDetails.quest.name}`}</h2>
                <form>
                    <p> You are about to complete a quest. Having something to look back to is always great
                        Please make sure to
                        leave journal entry note. </p>
                    <div className='modal-content-form'>
                        <label>Note:</label>
                        <textarea
                            placeholder='Enter notes here. You can log the process and how you were feeling when doing this quest'
                            required
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
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
                        disabled={!description}
                    >
                        COMPLETE
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default CompletionModal
