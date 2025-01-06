import CategoryFormModal from '@/components/admin/categories/category-form-modal';
import CategoryTable from '@/components/admin/categories/category-table';
import { getCategories } from '@/services/api';
import { Category } from '@/types/category';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { ToastContainer } from 'react-toastify';

const AdminCategories = () => {

    const [isModalOpen, setIsModalOpen] = useState(false);
    const [categories, setCategories] = useState<Category[]>([]);

    const openModal = () => setIsModalOpen(true);
    const closeModal = () => setIsModalOpen(false);

    const router = useRouter();

    const fetchCategories = async () => {
        try {
            const data = await getCategories();
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    useEffect(() => {
        fetchCategories();
    }, [])

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Categories</h1>
                </div>
                <button className='action-button-primary' onClick={openModal}>
                    <FaPlus /> New Category
                </button>
            </div>
            <div className='table-container'>
                <CategoryTable categories={categories} />
            </div>
            <CategoryFormModal open={isModalOpen} onClose={closeModal} />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default AdminCategories
