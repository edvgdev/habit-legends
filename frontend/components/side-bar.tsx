import { logout } from '@/api/auth';
import Link from 'next/link';
import { useRouter } from 'next/router'
import React from 'react'

const SideBar = () => {

    const router = useRouter();


    const menus = [
        { name: "Daily Quests", path: "/daily-quest" },
        { name: "Quests Overview", path: "/quests-overview" },
        { name: "Admin", path: "/admin" },
        { name: "Settings", path: "/settings" },
    ];

    return (
        (
            <div className="side-bar">
                {/* Profile Section */}
                <div className="profile-summary">
                    <div className="profile-summary-image">
                        <img
                            src="/profile.jpg" // Replace with your profile image URL
                            alt="Profile"
                        />
                    </div>
                    {/* Profile Details */}
                    <div className='profile-summary-details'>
                        {/* Profile Details */}
                        <div className='profile-summary-info'>
                            <h2>John Doe</h2>
                            <p >E-Rank</p>
                            <p >Lvl. 3</p>
                        </div>
                        {/* Progress Bar */}
                        <div className="profile-summary-progress-container">
                            <div
                                className="profile-summary-progress"
                                style={{ width: '60%' }} // Adjust progress percentage here
                            ></div>
                        </div>
                    </div>

                </div>

                {/* Menu Section */}
                <div className="menu">
                    <ul>
                        {menus.map((menu) => (
                            <li
                                key={menu.name}
                                className={router.pathname === menu.path ? "active-menu" : ""}
                            >
                                <Link href={menu.path}>{menu.name}</Link>
                            </li>
                        ))}
                        <li
                        >
                            <Link href="/" onClick={logout}>Logout</Link>
                        </li>
                        {/* <li > <a href="#">Daily Quests</a></li>
                        <li > <a href="#"> Quests Overview</a></li>
                        <li > <a href="#">Admin</a></li>
                        <li > <a href="#">Settings</a></li> */}
                    </ul>
                </div>
            </div>
        )
    )
}

export default SideBar
