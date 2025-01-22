import { Habit, HabitDetails } from '@/types/habit';
import { StatNameAndReward } from '@/types/stat';
import React, { Dispatch, SetStateAction } from 'react'
import { FaPlus } from 'react-icons/fa';

interface Props {
    habit: HabitDetails;
    statRewards: StatNameAndReward[];
    setSelected: Dispatch<SetStateAction<HabitDetails | undefined>>;
    openConfirmation: () => void;
}

const QuestCard = ({ habit, statRewards, setSelected, openConfirmation }: Props) => {



    return (
        <div className='quest-card-container'>
            <div className='quest-card-image'>
                <img src={habit.habit.imageLink} />
            </div>
            <div className='quest-card-info'>
                <h3>{habit.habit.name}</h3>
                <p>
                    {habit.habit.description}
                </p>
                <div className='quest-card-reward'>
                    <p><span>+</span>{` ${habit.habit.baseExpReward} exp`}</p>
                    {statRewards.map((statReward) => (
                        <p><span>+</span>{` ${statReward.reward} ${statReward.name}`}</p>
                    ))}
                </div>
                <div className='quest-card-action'>
                    <button
                        className='action-button-primary'
                        style={{ height: "40px", padding: "0 2rem" }}
                        onClick={() => {
                            setSelected(habit);
                            openConfirmation();
                        }}>
                        <FaPlus /> Add
                    </button>
                </div>
            </div>
        </div>
    )
}

export default QuestCard
