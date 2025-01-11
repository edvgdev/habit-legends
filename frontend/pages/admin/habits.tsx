import HabitFormModal from '@/components/admin/habits/habit-form-modal';
import HabitTable from '@/components/admin/habits/habit-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteHabit, getHabits } from '@/services/api';
import { Habit, HabitDetails } from '@/types/habit';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const Habits = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [habits, setHabits] = useState<HabitDetails[]>([]);
    const [selectedHabit, setSelectedHabit] = useState<HabitDetails | null>(null);

    const router = useRouter();

    const openModal = (habit?: HabitDetails) => {
        if (habit) {
            setSelectedHabit(habit);
        } else {
            setSelectedHabit(null);
        }
        setIsFormModalOpen(true);
    };
    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedHabit(null);
    };

    const openConfirmationModal = (habit: HabitDetails) => {
        setSelectedHabit(habit);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedHabit(null);
    };



    const fetchHabits = async () => {
        try {
            const data = await getHabits();
            setHabits(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedHabit) {
            try {
                await deleteHabit(selectedHabit.habit.id!);
                toast.success('Habit deleted successfully');
                fetchHabits(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete habit');
                console.error('Error deleting habit:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchHabits();
    };

    useEffect(() => {
        fetchHabits();
    }, []);

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Habits</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New Habit
                </button>
            </div>
            <div className='table-container'>
                <HabitTable
                    habitDetails={habits}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <HabitFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                habitDetails={selectedHabit}
                onSuccess={handleSuccessfulSave}
            />

            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete Habit'
                message={`You are about to delete "${selectedHabit?.habit.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}
export default Habits
