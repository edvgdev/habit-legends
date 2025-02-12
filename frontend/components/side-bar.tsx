
import { useAuth } from '@/context/auth-context';
import useUserStore from '@/types/user';
import { orbitron } from '@/utils/fonts';
import Link from 'next/link';
import { useRouter } from 'next/router'
import React, { useState } from 'react'
import { FaAngleLeft, FaAngleRight, FaCog, FaList, FaSignOutAlt, FaTasks, FaUserCog } from 'react-icons/fa';
import ProfileModal from './profile-modal';

interface Props {
    isSidebarExpanded: boolean;
    toggleSidebar: () => void;
}

const SideBar = ({ isSidebarExpanded, toggleSidebar }: Props) => {

    const { logout } = useAuth();
    const router = useRouter();
    const { userProfile, userProgressDetails } = useUserStore();

    const [isProfileModalOpen, setIsProfileModalOpen] = useState(false);

    const menus = [
        { name: "Daily Quests", path: "/daily-quest", icon: <FaTasks /> },
        { name: "Quests Overview", path: "/quests-overview", icon: <FaList /> },
        { name: "Admin", path: "/admin", icon: <FaUserCog /> },
    ];

    const openModal = () => {
        setIsProfileModalOpen(true);
    }

    const closeModal = () => {
        setIsProfileModalOpen(false);
    }


    return (
        (
            <div className={`side-bar ${isSidebarExpanded ? 'expanded' : 'minimized'}`}>
                <div className='app-title'>

                    {!isSidebarExpanded ? (<img
                        src='/qlogo.png'
                    />) : null}
                    <h2 className={`${orbitron.className}`}>Questlyf</h2>
                    <button
                        className='toggle-sidebar'
                        onClick={toggleSidebar}>
                        {isSidebarExpanded ? <FaAngleLeft /> : <FaAngleRight />}
                    </button>
                </div>
                {/* Profile Section */}
                <div className="profile-summary">
                    <div
                        className="profile-summary-image"
                        onClick={openModal}
                    >
                        <img
                            src={userProfile?.profilePictureLink ? userProfile.profilePictureLink : "/profile.jpg"} // Replace with your profile image URL
                            alt="Profile"
                        />
                        <span className="profile-tooltip">View Profile</span>
                    </div>
                    {/* Profile Details */}
                    <div className='profile-summary-details'>
                        {/* Profile Details */}
                        <div className='profile-summary-info'>
                            <h2>{`${userProfile?.firstName} ${userProfile?.lastName} `}</h2>
                            <p >{`${userProgressDetails?.rank}-Rank`}</p>
                            <p >{`Lvl. ${userProgressDetails?.level}`}</p>
                        </div>
                        {/* Progress Bar */}
                        <div className='profile-summary-progress'>
                            <div className="profile-summary-progress-container">
                                <div
                                    className="profile-summary-progress-bar"
                                    style={{ width: '60%' }} // Adjust progress percentage here
                                ></div>
                            </div>
                            <p>{`${userProgressDetails?.exp}/${userProgressDetails?.expRequiredForNextLevel} exp`}</p>
                        </div>
                    </div>

                </div>

                {/* Menu Section */}
                <div className="menu">
                    <ul>
                        {menus.map((menu) => (
                            <li
                                key={menu.name}
                                className={router.pathname.startsWith(menu.path) ? "active-menu" : ""}
                            >
                                <Link href={menu.path}>
                                    <div className="menu-item-container">
                                        <span className="menu-icon">{menu.icon}</span>
                                        {!isSidebarExpanded && (
                                            <span className="tooltip">{menu.name}</span>
                                        )}
                                        {isSidebarExpanded && (
                                            <span className="menu-text">{menu.name}</span>
                                        )}
                                    </div>
                                </Link>
                            </li>
                        ))}
                        <li
                        >
                            <Link href="/" onClick={logout}>
                                <div className="menu-item-container">
                                    <span className="menu-icon"><FaSignOutAlt /></span>
                                    {!isSidebarExpanded && (
                                        <span className="tooltip">Logout</span>
                                    )}
                                    {isSidebarExpanded && (
                                        <span className="menu-text">Logout</span>
                                    )}
                                </div>
                            </Link>
                        </li>
                    </ul>
                </div>

                {isSidebarExpanded && <div className='upgrade-notice'>
                    <h2>Unlock more Features</h2>
                    <p>Upgrade now to Premium to unlock all features</p>
                    <button
                        style={{ fontSize: "0.8rem", padding: "0 1.5rem" }}
                        className='action-button-primary'
                    >
                        Upgrade
                    </button>
                </div>}
                <ProfileModal
                    open={isProfileModalOpen}
                    onClose={closeModal}
                    userProfile={userProfile!}
                    userProgress={userProgressDetails!}
                />
            </div>
        )
    )
}

export default SideBar
