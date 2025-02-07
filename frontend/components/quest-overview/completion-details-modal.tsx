import { QuestCompletion } from '@/types/quest';
import dayjs from 'dayjs';
import React from 'react';
import Modal from 'react-modal';

interface Props {
    isOpen: boolean;
    onClose: () => void;
    questCompletion: QuestCompletion;
    questName: string;
}

const CompletionDetailsModal = ({ isOpen, onClose, questCompletion, questName }: Props) => {
    return (
        <Modal
            isOpen={isOpen}
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
                    margin: "0",
                    top: "50%",
                    left: "50%",
                    right: "auto",
                    bottom: "auto",
                    transform: "translate(-50%, -50%)",
                    overflow: "visible",
                    border: "none",
                    background: "none",
                    borderRadius: "0",
                    boxShadow: "2px 2px 24px 1px rgba(0, 0, 0, 0.31)"
                }
            }}
        >
            <div className='completion-details-modal'>
                <h2>{questName}</h2>
                <div className="completion-content">
                    <p><strong>Experience Points Earned:</strong> {questCompletion?.expEarned}</p>
                    <p><strong>Completed At:</strong> {dayjs(questCompletion?.completedAt).format('MMMM D, YYYY h:mm A')}</p>
                    <p><strong>Notes:</strong> {questCompletion?.description}</p>
                </div>
                <button
                    className='action-button-primary'
                    onClick={onClose}
                    style={{ width: "300px", alignSelf: "center" }}
                >
                    Close
                </button>
            </div>
        </Modal>
    );
};

export default CompletionDetailsModal;