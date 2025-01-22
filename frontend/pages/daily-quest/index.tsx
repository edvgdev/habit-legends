import { getAllUserQuests, getStats } from '@/api/api';
import UserQuestCard from '@/components/quest/user-quest-card';
import { HabitDetails, UserHabitDetails } from '@/types/habit';
import { Stat, StatNameAndReward } from '@/types/stat';
import useUserStore from '@/types/user';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaPlus, FaTimes } from 'react-icons/fa';
import dayjs from 'dayjs';

const DailyQuest = () => {

    const router = useRouter();
    const { userProfile } = useUserStore();

    const [userQuests, setUserQuests] = useState<UserHabitDetails[]>([]);
    const [selectedQuest, setSelectedQuest] = useState<UserHabitDetails | null>();
    const [selectedQuestRewards, setSelectedQuestRewards] = useState<StatNameAndReward[]>([]);

    const [stats, setStats] = useState<Stat[]>([]);

    const [isDrawerOpen, setIsDrawerOpen] = useState(false);

    const dateToday = dayjs().format('MMMM D, YYYY');

    const navigateToAddPage = () => {
        router.push('/daily-quest/add');
    };

    const handleQuestClick = (quest: UserHabitDetails) => {
        setSelectedQuest(quest);
        setSelectedQuestRewards(getStatsFromHabit(quest.habitDetails));
        setIsDrawerOpen(true);
    }

    const closeDrawer = () => {
        setIsDrawerOpen(false);
        setSelectedQuest(null);
        setSelectedQuestRewards([]);
    };

    const getStatsFromHabit = (habits: HabitDetails): StatNameAndReward[] => {
        return habits.habitStatRewards.map(({ statId, baseStatReward }) => ({
            name: stats.find(stat => stat.id === statId)?.name || "Unknown",
            reward: baseStatReward,
        }));
    };

    useEffect(() => {
        fetchData();
    }, []);

    const fetchData = async () => {
        if (!userProfile) {
            return;
        }
        try {
            const quests = await getAllUserQuests(userProfile.id);
            const stats = await getStats();
            setUserQuests(quests);
            setStats(stats);
        } catch (error) {
            console.error(error);
        }
    }

    console.log(userQuests);

    return (
        <div className='daily-quests'>
            <div className='daily-quests-header'>
                <h2>{dateToday}</h2>
                <button className='action-button-primary' onClick={() => navigateToAddPage()}>
                    <FaPlus /> New Quest
                </button>
            </div>
            <div className='user-quest-container'>
                <div className='user-quest-items'>
                    {userQuests.map((userQuest) => {
                        const isSelected =
                            userQuest.habitDetails.habit.id ===
                            selectedQuest?.habitDetails.habit.id;
                        return (
                            <UserQuestCard
                                userQuest={userQuest}
                                onClick={handleQuestClick}
                                isSelected={isSelected}
                            />
                        )
                    })}

                </div>
                <div className={`user-quest-preview ${isDrawerOpen ? 'drawerOpen' : ''}`}>
                    <div className='user-quest-preview-card'>
                        <div className='user-quest-preview-header'>
                            <h1>Quest Preview</h1>
                            <button
                                className='close-button'
                                onClick={closeDrawer}
                            >
                                <FaTimes />
                            </button>

                        </div>
                        {selectedQuest ? (
                            <>
                                <div className='user-quest-preview-image'>
                                    <img src={selectedQuest.habitDetails.habit.imageLink} />
                                </div>
                                <div className='user-quest-preview-info'>
                                    <h2>{selectedQuest?.habitDetails.habit.name}</h2>
                                    <p>
                                        {selectedQuest?.habitDetails.habit.description}
                                    </p>
                                </div>
                                <div className='quest-card-reward'>
                                    <p><span>+</span>{` ${selectedQuest.habitDetails.habit.baseExpReward} Exp`}</p>
                                    {selectedQuestRewards.map((statReward) => (
                                        <p><span>+</span>{` ${statReward.reward} ${statReward.name}`}</p>
                                    ))}
                                </div>
                                <div className='quest-card-action'>
                                    <button
                                        className='action-button-primary'
                                        style={{ height: "40px", padding: "0 2rem" }}
                                        onClick={() => {
                                        }}>
                                        COMPLETE
                                    </button>
                                </div>
                            </>
                        ) : (
                            <div className='empty-quest-preview'>
                                <p>No quest selected <br />Select one to view</p>
                            </div>
                        )}

                    </div>

                </div>
            </div>
        </div>
    )
}

export default DailyQuest
