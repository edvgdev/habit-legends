import { getAllQuestCompletions, getAllUserQuests, getStats } from '@/api/api';
import UserQuestCard from '@/components/quest/user-quest-card';
import { HabitCompletion, HabitCompletionFilterDetails, HabitDetails, UserHabitDetails } from '@/types/habit';
import { Stat, StatNameAndReward } from '@/types/stat';
import useUserStore from '@/types/user';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaPlus, FaTimes } from 'react-icons/fa';
import dayjs from 'dayjs';
import CompletionModal from '@/components/quest/completion-modal';
import { ToastContainer } from 'react-toastify';

const DailyQuest = () => {

    const router = useRouter();
    const { userProfile } = useUserStore();

    const [userQuests, setUserQuests] = useState<UserHabitDetails[]>([]);
    const [selectedQuest, setSelectedQuest] = useState<UserHabitDetails | null>();
    const [selectedQuestRewards, setSelectedQuestRewards] = useState<StatNameAndReward[]>([]);
    const [userQuestCompletions, setUserQuestCompletions] = useState<HabitCompletion[]>([]);

    const [stats, setStats] = useState<Stat[]>([]);

    const [isDrawerOpen, setIsDrawerOpen] = useState(false);
    const [isFormModalOpen, setIsFormModalOpen] = useState(false);

    const dateToday = dayjs().format('MMMM D, YYYY');

    const navigateToAddPage = () => {
        router.push('/daily-quest/add');
    };

    const openFormModal = () => {
        setIsFormModalOpen(true);
    }

    const closeFormModal = () => {
        setIsFormModalOpen(false);
    }

    const onCompleteQuest = () => {
        fetchData();
        closeDrawer();
    }

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

    const checkIfCompleted = (habitId: number): boolean => {
        return userQuestCompletions.some(completion => completion.habitId === habitId);
    }

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

            const today = new Date();
            const formattedStartDate = today.toISOString().split("T")[0] + "T00:00:00"; // Start of day
            const formattedEndDate = today.toISOString().split("T")[0] + "T23:59:59"; // End of day

            const filterDetails: HabitCompletionFilterDetails = {
                userId: userProfile.id,
                startDate: formattedStartDate,
                endDate: formattedEndDate,
                description: null,
                habitId: null
            };
            const completions = await getAllQuestCompletions(filterDetails);
            setUserQuests(quests);
            setStats(stats);
            setUserQuestCompletions(completions);
        } catch (error) {
            console.error(error);
        }
    }

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
                                isCompleted={checkIfCompleted(userQuest.habitDetails.habit.id!)}
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
                                        disabled={checkIfCompleted(selectedQuest?.habitDetails.habit.id!)}
                                        onClick={() => {
                                            openFormModal();
                                        }}>
                                        {
                                            checkIfCompleted(selectedQuest?.habitDetails.habit.id!)
                                                ? 'COMPLETED'
                                                : 'COMPLETE'}
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
            <CompletionModal
                open={isFormModalOpen}
                onClose={closeFormModal}
                quest={selectedQuest}
                onSuccess={onCompleteQuest}
                userId={userProfile?.id!}
            />
            <ToastContainer position='top-right' autoClose={3000} />
        </div>
    )
}

export default DailyQuest
