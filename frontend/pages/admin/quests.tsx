import QuestFormModal from '@/components/admin/quests/quest-form-modal';
import QuestTable from '@/components/admin/quests/quest-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteQuest, getQuests } from '@/api/api';
import { Quest, QuestDetails } from '@/types/quest';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const Quests = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [quests, setQuests] = useState<QuestDetails[]>([]);
    const [selectedQuest, setSelectedQuest] = useState<QuestDetails | null>(null);

    const router = useRouter();

    const openModal = (quest?: QuestDetails) => {
        if (quest) {
            setSelectedQuest(quest);
        } else {
            setSelectedQuest(null);
        }
        setIsFormModalOpen(true);
    };
    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedQuest(null);
    };

    const openConfirmationModal = (quest: QuestDetails) => {
        setSelectedQuest(quest);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedQuest(null);
    };



    const fetchQuests = async () => {
        try {
            const data = await getQuests();
            setQuests(data);
        } catch (error) {
            console.error('Error fetching quests:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedQuest) {
            try {
                await deleteQuest(selectedQuest.quest.id!);
                toast.success('Quest deleted successfully');
                fetchQuests(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete Quest');
                console.error('Error deleting Quest:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchQuests();
    };

    useEffect(() => {
        fetchQuests();
    }, []);

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Quests</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New Quest
                </button>
            </div>
            <div className='table-container'>
                <QuestTable
                    questDetails={quests}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <QuestFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                questDetails={selectedQuest}
                onSuccess={handleSuccessfulSave}
            />

            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete Quest'
                message={`You are about to delete "${selectedQuest?.quest.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}
export default Quests
