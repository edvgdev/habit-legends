import RankFormModal from '@/components/admin/ranks/rank-form-modal';
import RankTable from '@/components/admin/ranks/rank-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteRank, getRanks } from '@/services/api';
import { Rank } from '@/types/Rank';
import { useRouter } from 'next/router'
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const Ranks = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [ranks, setRanks] = useState<Rank[]>([]);
    const [selectedRank, setSelectedRank] = useState<Rank | null>(null);

    const router = useRouter();

    const openModal = (rank?: Rank) => {
        if (rank) {
            setSelectedRank(rank);
        } else {
            setSelectedRank(null);
        }
        setIsFormModalOpen(true);
    };
    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedRank(null);
    };

    const openConfirmationModal = (rank: Rank) => {
        setSelectedRank(rank);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedRank(null);
    };

    const fetchRanks = async () => {
        try {
            const data = await getRanks();
            setRanks(data);
        } catch (error) {
            console.error('Error fetching ranks:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedRank) {
            try {
                await deleteRank(selectedRank.id!);
                toast.success('Rank deleted successfully');
                fetchRanks(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete rank');
                console.error('Error deleting rank:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchRanks();
    };

    useEffect(() => {
        fetchRanks();
    }, []);

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Ranks</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New Rank
                </button>
            </div>
            <div className='table-container'>
                <RankTable
                    ranks={ranks}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <RankFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                rank={selectedRank}
                onSuccess={handleSuccessfulSave}
            />
            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete Rank'
                message={`You are about to delete "${selectedRank?.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default Ranks
