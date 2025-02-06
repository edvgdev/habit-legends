import { Quest, QuestDetails } from '@/types/quest';
import { StatNameAndReward } from '@/types/stat';
import React, { Dispatch, SetStateAction } from 'react'
import { FaPlus } from 'react-icons/fa';

interface Props {
    quest: QuestDetails;
    statRewards: StatNameAndReward[];
    setSelected: Dispatch<SetStateAction<QuestDetails | undefined>>;
    openConfirmation: () => void;
    isAdded: boolean;
}

const QuestCard = ({ quest, statRewards, setSelected, openConfirmation, isAdded }: Props) => {



    return (
        <div className='quest-card-container'>
            <div className='quest-card-image'>
                <img src={quest.quest.imageLink} />
            </div>
            <div className='quest-card-info'>
                <h3>{quest.quest.name}</h3>
                <p>
                    {quest.quest.description}
                </p>
                <div className='quest-card-reward'>
                    <p><span>+</span>{` ${quest.quest.baseExpReward} exp`}</p>
                    {statRewards.map((statReward) => (
                        <p><span>+</span>{` ${statReward.reward} ${statReward.name}`}</p>
                    ))}
                </div>
                <div className='quest-card-action'>
                    <button
                        className='action-button-primary'
                        style={{ height: "40px", padding: "0 2rem" }}
                        onClick={() => {
                            setSelected(quest);
                            openConfirmation();
                        }}
                        disabled={isAdded}>
                        {isAdded ? 'Added' : <><FaPlus /> Add</>}
                    </button>
                </div>
            </div>
        </div>
    )
}

export default QuestCard
