import { CircularProgress } from '@mui/material';
import { useRouter } from 'next/router';
import React, { useEffect } from 'react';
import Modal from 'react-modal';

interface Props {
    isOpen: boolean;
    onClose: () => void;
}

const RegistrationSuccessModal = ({ isOpen, onClose }: Props) => {


    useEffect(() => {
        if (isOpen) {
            const timer = setTimeout(() => {
                onClose(); // Close the modal
            }, 5000);

            // Clean up the timer on component unmount
            return () => clearTimeout(timer);
        }
    }, [isOpen, onClose]);

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
                },
            }}
        >
            <div className='modal-content'>
                <div className='modal-content-message'>
                    <h2>Welcome to Questlyf!</h2>
                    <p>You have successfully registered an account. You can now start leveling up your life.
                        <br /> Navigating to the login page...
                    </p>
                    <CircularProgress />
                </div>
            </div>
        </Modal>
    )
}

export default RegistrationSuccessModal
