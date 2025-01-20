import React from 'react';
import Modal from 'react-modal';


interface Props {
    isOpen: boolean;
    onClose: () => void;
}

const LoadingModal = ({ isOpen, onClose }: Props) => {
    return (
        <Modal
            isOpen={isOpen}
            onRequestClose={onClose}
            shouldCloseOnOverlayClick={false}
            style={{
                overlay: {
                    backgroundColor: "rgba(0, 0, 0, 0.7)",
                    display: "flex",
                    justifyContent: "center",
                    alignItems: "center",
                    zIndex: '30'
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
                },
            }}
        >
            <div className='loading-modal-content'>
                <div className='loading-modal-content-message'>
                    <h2>Loading..</h2>
                    <img src='/infinity.svg' />
                </div>
            </div>

        </Modal>

    )
}

export default LoadingModal
