import { addQuestToUser, getHabits, getStats } from '@/api/api';
import ConfirmationModal from '@/components/confirmation-modal';
import QuestCard from '@/components/quest/quest-card'
import { HabitDetails, UserHabitDetails } from '@/types/habit';
import { Stat, StatNameAndReward } from '@/types/stat';
import useUserStore from '@/types/user';
import React, { useEffect, useState } from 'react';
import { toast, ToastContainer } from 'react-toastify';

const AddQuest = () => {

    const [quests, setQuests] = useState<HabitDetails[]>([]);
    const [stats, setStats] = useState<Stat[]>([]);
    const [selectedQuest, setSelectedQuest] = useState<HabitDetails>();

    const [openCorfirmationModal, setOpenConfirmationModal] = useState(false);

    const { userProfile } = useUserStore();

    useEffect(() => {
        fetchData();
    }, []);

    const openConfirmationModal = () => {
        setOpenConfirmationModal(true);
    }

    const closeConfirmationModal = () => {
        setOpenConfirmationModal(false);
    }



    const fetchData = async () => {

        try {
            const quests = await getHabits();
            const stats = await getStats();
            setQuests(quests);
            setStats(stats);

            console.log(quests);
            console.log(stats);

        } catch (error) {
            console.error(error);
        }
    };

    const getStatsFromHabit = (habits: HabitDetails): StatNameAndReward[] => {
        return habits.habitStatRewards.map(({ statId, baseStatReward }) => ({
            name: stats.find(stat => stat.id === statId)?.name || "Unknown",
            reward: baseStatReward,
        }));
    };

    const addQuest = async () => {
        if (!selectedQuest || !userProfile) {
            return
        }

        const userHabits: UserHabitDetails = {
            userId: userProfile?.id,
            habitDetails: selectedQuest
        }

        try {
            await addQuestToUser(userHabits);
            toast.success('Successfully added to your quests');
        } catch (error) {
            toast.error(`Failed to add to your quests ${error}`);
        } finally {
            closeConfirmationModal();
        }

    }

    return (
        <div className='daily-quests'>
            <div className='daily-quests-header'>
                <h2>Add New Quest</h2>
            </div>
            <div className='quests-container'>
                {quests.map((quest) => (
                    <QuestCard
                        openConfirmation={openConfirmationModal}
                        habit={quest}
                        statRewards={getStatsFromHabit(quest)}
                        setSelected={setSelectedQuest}
                    />
                ))}

            </div>
            <ConfirmationModal
                isOpen={openCorfirmationModal}
                onClose={closeConfirmationModal}
                title='Add to your Quests'
                message={`You are about to add ${selectedQuest?.habit.name} to your quests, Do you want to continue?`}
                onConfirm={addQuest}
            />
            <ToastContainer position='top-right' autoClose={3000} />
        </div>
    )
};

export default AddQuest
