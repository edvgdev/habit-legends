import { UserQuestDetails } from '@/types/quest'
import React from 'react'

interface Props {
    userQuest: UserQuestDetails;
    onClick: (quest: UserQuestDetails) => void;
    isSelected: boolean;
    isCompleted: boolean;
}


const UserQuestCard = ({ userQuest, onClick, isSelected, isCompleted }: Props) => {
    return (
        <div
            className={`user-quest-item-card ${isSelected ? 'selected' : ''}`}
            onClick={() => {
                onClick(userQuest);
            }}
        >
            <div className='user-quest-item-image'>
                <img src={userQuest.questDetails.quest.imageLink} />
            </div>
            <div className='user-quest-item-info'>
                <h3>{userQuest.questDetails.quest.name}</h3>
                <p>
                    {userQuest.questDetails.quest.description}
                </p>
            </div>
            <div className='user-quest-completion'>
                {isCompleted ? (<img src='/completion.png' />) : (<></>)}

            </div>

        </div>
    )
}

export default UserQuestCard
