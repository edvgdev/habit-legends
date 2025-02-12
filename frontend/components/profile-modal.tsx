import { getAllUserStatsByUser } from '@/api/api';
import { UserProfile, UserProgressDetails, UserStatDetails } from '@/types/user';
import React, { useEffect, useState } from 'react';
import { FaTimes } from 'react-icons/fa';
import Modal from 'react-modal';


interface Props {
    open: boolean;
    onClose: () => void;
    userProfile: UserProfile;
    userProgress: UserProgressDetails;
}

const ProfileModal = ({ open, onClose, userProfile, userProgress }: Props) => {

    const [userStats, setUserStats] = useState<UserStatDetails[]>([]);

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        if (!userProfile) return;
        try {
            const stats = await getAllUserStatsByUser(userProfile.id);
            setUserStats(stats);
        } catch (error) {
            console.error(error);
        }

    };

    const percentage = (userProgress?.exp / userProgress?.expRequiredForNextLevel) * 100;

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
            <div className='profile-modal'>
                <button
                    className='profile-close-button'
                    onClick={onClose}
                >
                    <FaTimes />
                </button>
                <div className='profile-details'>
                    <div className='profile-details-image'>
                        <img
                            src={userProfile?.profilePictureLink ? userProfile.profilePictureLink : "/profile.jpg"} // Replace with your profile image URL
                            alt="Profile"
                        />
                    </div>
                    <div className='profile-details-info-container'>
                        <div className='profile-details-info'>
                            <h2>{`${userProfile?.firstName} ${userProfile?.lastName}`}</h2>
                            <p>{`${userProgress?.rank}-Rank`}</p>
                            <p>{`Lvl. ${userProgress?.level}`}</p>
                        </div>
                        <div className='profile-details-progress'>
                            <div className="profile-details-progress-container">
                                <div
                                    className="profile-details-progress-bar"
                                    style={{ width: `${percentage}%` }} // Adjust progress percentage here
                                ></div>
                            </div>
                            <p>{`${userProgress?.exp}/${userProgress?.expRequiredForNextLevel} exp`}</p>
                        </div>
                    </div>
                </div>
                <div className='profile-stats'>
                    <h2>My Stats</h2>
                    <div className='profile-stats-container'>
                        {userStats.map((stat) => (
                            <div
                                key={stat.statName}
                                className='profile-stats-item'>
                                <img
                                    src={stat.iconUrl}
                                    alt="icon"
                                />
                                <p>{`${stat.statName}: ${stat.currentPoints}`}</p>
                            </div>
                        )

                        )}
                    </div>
                </div>


            </div>
        </Modal>
    )
}

export default ProfileModal
