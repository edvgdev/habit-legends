import { addQuestToUser, getAllUserQuests, getQuests, getStats } from '@/api/api';
import ConfirmationModal from '@/components/confirmation-modal';
import QuestCard from '@/components/quest/quest-card'
import { QuestDetails, UserQuestDetails } from '@/types/quest';
import { Stat, StatNameAndReward } from '@/types/stat';
import useUserStore from '@/types/user';
import React, { useEffect, useState } from 'react';
import { toast, ToastContainer } from 'react-toastify';

const AddQuest = () => {

    const [quests, setQuests] = useState<QuestDetails[]>([]);
    const [stats, setStats] = useState<Stat[]>([]);
    const [selectedQuest, setSelectedQuest] = useState<QuestDetails>();
    const [userQuests, setUserQuests] = useState<UserQuestDetails[]>([]);

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
            const quests = await getQuests();
            const stats = await getStats();

            setQuests(quests);
            setStats(stats);

            if (userProfile) {
                const userQuests = await getAllUserQuests(userProfile.id);
                setUserQuests(userQuests);
            }

        } catch (error) {
            console.error(error);
        }
    };

    const checkIfAdded = (questId: number): boolean => {
        return userQuests.some(userQuest => userQuest.questDetails.quest.id === questId);
    }

    const getStatsFromQuest = (quest: QuestDetails): StatNameAndReward[] => {
        return quest.questStatRewards.map(({ statId, baseStatReward }) => ({
            name: stats.find(stat => stat.id === statId)?.name || "Unknown",
            reward: baseStatReward,
        }));
    };

    const addQuest = async () => {
        if (!selectedQuest || !userProfile) {
            return
        }

        const userQuests: UserQuestDetails = {
            userId: userProfile?.id,
            questDetails: selectedQuest
        }

        try {
            await addQuestToUser(userQuests);
            toast.success('Successfully added to your quests');
        } catch (error) {
            toast.error(`Failed to add to your quests ${error}`);
        } finally {
            fetchData();
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
                        quest={quest}
                        statRewards={getStatsFromQuest(quest)}
                        setSelected={setSelectedQuest}
                        isAdded={checkIfAdded(quest.quest.id!)}
                    />
                ))}

            </div>
            <ConfirmationModal
                isOpen={openCorfirmationModal}
                onClose={closeConfirmationModal}
                title='Add to your Quests'
                message={`You are about to add ${selectedQuest?.quest.name} to your quests, Do you want to continue?`}
                onConfirm={addQuest}
            />
            <ToastContainer position='top-right' autoClose={3000} />
        </div>
    )
};

export default AddQuest
