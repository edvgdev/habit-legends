import CategoryFormModal from '@/components/CategoryFormModal';
import React, { useState } from 'react'
import { FaPlus } from 'react-icons/fa';
import { ToastContainer } from 'react-toastify';

const AdminCategories = () => {

    const [isModalOpen, setIsModalOpen] = useState(false);

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    return (
        <div>
            <button className='action-button-primary' onClick={openModal}>
                <FaPlus /> New Category
            </button>
            <CategoryFormModal open={isModalOpen} onClose={closeModal} />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default AdminCategories
