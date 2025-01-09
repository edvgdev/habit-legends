import StatFormModal from '@/components/admin/stats/stat-form-modal';
import StatTable from '@/components/admin/stats/stat-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteStat, getStats } from '@/services/api';
import { Stat } from '@/types/stat';
import { useRouter } from 'next/router'
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa'
import { toast, ToastContainer } from 'react-toastify';

const Stats = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [selectedStat, setSelectedStat] = useState<Stat | null>(null);
    const [stats, setStats] = useState<Stat[]>([]);

    const router = useRouter();

    const openModal = (stat?: Stat) => {

        stat ? setSelectedStat(stat) : setSelectedStat(null);

        setIsFormModalOpen(true);
    };

    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedStat(null);
    };

    const openConfirmationModal = (stat: Stat) => {
        setSelectedStat(stat);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedStat(null);
    };


    const fetchStats = async () => {
        try {
            const data = await getStats();
            setStats(data);
        } catch (error) {
            console.error('Error fetching stats:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedStat) {
            try {
                await deleteStat(selectedStat.id!);
                toast.success('Stat deleted successfully');
                fetchStats(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete stat');
                console.error('Error deleting stat:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchStats();
    }

    useEffect(() => {
        fetchStats();
    }, [])

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Stats</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New Stat
                </button>
            </div>
            <div className='table-container'>
                <StatTable
                    stats={stats}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <StatFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                stat={selectedStat}
                onSuccess={handleSuccessfulSave}
            />
            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete Stat'
                message={`You are about to delete "${selectedStat?.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default Stats
