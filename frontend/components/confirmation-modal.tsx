import React from 'react';
import Modal from 'react-modal';

interface Props {
    isOpen: boolean;
    title: string;
    message: string;
    onClose: () => void;
    onConfirm: () => void;
}

const ConfirmationModal = ({ isOpen, title, message, onClose, onConfirm }: Props) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            style={{
                overlay: {
                    backgroundColor: "rgba(0, 0, 0, 0.7)",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    zIndex: '1'
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
                },
            }}
        >
            <div className='modal-content'>
                <h2>{title}</h2>
                <p>{message}</p>
                <div style={{ marginTop: "20px" }}>
                    <button
                        className="action-button-secondary"
                        onClick={onClose}
                        style={{ marginRight: "10px" }}
                    >
                        Cancel
                    </button>
                    <button
                        className="action-button-primary"
                        onClick={onConfirm}
                    >
                        Yes
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default ConfirmationModal
