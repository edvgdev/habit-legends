import { UserHabitDetails } from '@/types/habit'
import React from 'react'

interface Props {
    userQuest: UserHabitDetails;
    onClick: (quest: UserHabitDetails) => void;
    isSelected: boolean;
}


const UserQuestCard = ({ userQuest, onClick, isSelected }: Props) => {
    return (
        <div
            className={`user-quest-item-card ${isSelected ? 'selected' : ''}`}
            onClick={() => {
                onClick(userQuest);
            }}
        >
            <div className='user-quest-item-image'>
                <img src={userQuest.habitDetails.habit.imageLink} />
            </div>
            <div className='user-quest-item-info'>
                <h3>{userQuest.habitDetails.habit.name}</h3>
                <p>
                    {userQuest.habitDetails.habit.description}
                </p>
            </div>
            <div className='user-quest-completion'>
                <img src='/completion.png' />
            </div>

        </div>
    )
}

export default UserQuestCard
