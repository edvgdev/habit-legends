import UserPlanFormModal from '@/components/admin/userPlans/user-plan-form-modal';
import UserPlanTable from '@/components/admin/userPlans/user-plan-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteUserPlan, getUserPlans } from '@/api/api';
import { UserPlan } from '@/types/user-plan';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const UserPlans = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [userPlans, setUserPlans] = useState<UserPlan[]>([]);
    const [selectedUserPlan, setSelectedUserPlan] = useState<UserPlan | null>(null);

    const router = useRouter();

    const openModal = (userPlan?: UserPlan) => {
        if (userPlan) {
            setSelectedUserPlan(userPlan);
        } else {
            setSelectedUserPlan(null);
        }
        setIsFormModalOpen(true);
    };
    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedUserPlan(null);
    };

    const openConfirmationModal = (userPlan: UserPlan) => {
        setSelectedUserPlan(userPlan);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedUserPlan(null);
    };



    const fetchUserPlans = async () => {
        try {
            const data = await getUserPlans();
            setUserPlans(data);
        } catch (error) {
            console.error('Error fetching user plans:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedUserPlan) {
            try {
                await deleteUserPlan(selectedUserPlan.id!);
                toast.success('UserPlan deleted successfully');
                fetchUserPlans(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete UserPlan');
                console.error('Error deleting UserPlan:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchUserPlans();
    };

    useEffect(() => {
        fetchUserPlans();
    }, []);

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>User Plans</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New User Plan
                </button>
            </div>
            <div className='table-container'>
                <UserPlanTable
                    userPlans={userPlans}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <UserPlanFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                userPlan={selectedUserPlan}
                onSuccess={handleSuccessfulSave}
            />
            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete User Plan'
                message={`You are about to delete "${selectedUserPlan?.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default UserPlans
