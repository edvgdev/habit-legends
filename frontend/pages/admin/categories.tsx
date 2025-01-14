import CategoryFormModal from '@/components/admin/categories/category-form-modal';
import CategoryTable from '@/components/admin/categories/category-table';
import ConfirmationModal from '@/components/confirmation-modal';
import { deleteCategory, getCategories } from '@/api/api';
import { Category } from '@/types/category';
import { useRouter } from 'next/router';
import React, { useEffect, useState } from 'react'
import { FaArrowLeft, FaPlus } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const AdminCategories = () => {

    const [isFormModalOpen, setIsFormModalOpen] = useState(false);
    const [isConfirmationModalOpen, setIsConfirmationModalOpen] = useState(false);
    const [categories, setCategories] = useState<Category[]>([]);
    const [selectedCategory, setSelectedCategory] = useState<Category | null>(null);

    const router = useRouter();

    const openModal = (category?: Category) => {
        if (category) {
            setSelectedCategory(category);
        } else {
            setSelectedCategory(null);
        }
        setIsFormModalOpen(true);
    };
    const closeModal = () => {
        setIsFormModalOpen(false);
        setSelectedCategory(null);
    };

    const openConfirmationModal = (category: Category) => {
        setSelectedCategory(category);
        setIsConfirmationModalOpen(true);
    };

    const closeConfirmationModal = () => {
        setIsConfirmationModalOpen(false);
        setSelectedCategory(null);
    };



    const fetchCategories = async () => {
        try {
            const data = await getCategories();
            setCategories(data);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    };

    const handleDelete = async () => {
        if (selectedCategory) {
            try {
                await deleteCategory(selectedCategory.id!);
                toast.success('Category deleted successfully');
                fetchCategories(); // Refresh table
            } catch (error) {
                toast.error('Failed to delete category');
                console.error('Error deleting category:', error);
            } finally {
                closeConfirmationModal();
            }
        }
    };

    const handleSuccessfulSave = () => {
        fetchCategories();
    };

    useEffect(() => {
        fetchCategories();
    }, []);

    return (
        <div className='admin-subpage-container'>
            <div className='admin-subpage-header' >
                <div className='admin-subpage-title'>
                    <button onClick={() => router.push('/admin')}>
                        <FaArrowLeft />
                    </button>
                    <h1>Categories</h1>
                </div>
                <button className='action-button-primary' onClick={() => openModal()}>
                    <FaPlus /> New Category
                </button>
            </div>
            <div className='table-container'>
                <CategoryTable
                    categories={categories}
                    openModal={openModal}
                    onDelete={openConfirmationModal}
                />
            </div>
            <CategoryFormModal
                open={isFormModalOpen}
                onClose={closeModal}
                category={selectedCategory}
                onSuccess={handleSuccessfulSave}
            />
            <ConfirmationModal
                isOpen={isConfirmationModalOpen}
                title='Delete Category'
                message={`You are about to delete "${selectedCategory?.name}". Do you want to proceed?`}
                onClose={closeConfirmationModal}
                onConfirm={handleDelete}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>
    )
}

export default AdminCategories
