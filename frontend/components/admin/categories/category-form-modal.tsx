import { createCategory, updateCategory } from '@/services/api';
import { Category } from '@/types/category';
import React, { useEffect, useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';


interface Props {
    open: boolean;
    onClose: () => void;
    onSuccess: () => void;
    category?: Category | null;
}

const CategoryFormModal = ({ open, onClose, category, onSuccess }: Props) => {

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    useEffect(() => {
        console.log(category)
        if (category) {
            setName(category.name || '');
            setDescription(category.description || '');
        } else {
            setName('');
            setDescription('');
        }

    }, [category]);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const categoryToSave: Category = {
            id: category?.id || null,
            name,
            description,
            createdAt: category?.createdAt || null
        }

        try {
            const savedCategory = category
                ? await updateCategory(categoryToSave)
                : await createCategory(categoryToSave);
            console.log(savedCategory)

            toast.success(
                category
                    ? `Successfully updated category: ${savedCategory.name}`
                    : `Successfully created category: ${savedCategory.name}`
            );

            onSuccess();
        } catch (error) {
            toast.error(
                category
                    ? `Failed to update category ${error}`
                    : `Failed to create category ${error}`

            );
        } finally {
            onClose();
        }


    };

    return (
        <Modal
            isOpen={open}
            onRequestClose={onClose}
            style={{
                overlay: {
                    backgroundColor: " rgba(0, 0, 0, 0.7)",
                    display: "flex", // Add flex display
                    justifyContent: "center", // Center horizontally
                    alignItems: "center",
                    zIndex: "1"
                },
                content: {
                    width: "auto",
                    height: "fit-content",
                    padding: "0",
                    top: "50%", // Center vertically
                    left: "50%",
                    right: "auto",
                    bottom: "auto",
                    transform: "translate(-50%, -50%)",
                }
            }}
        >
            <div className='modal-content'>
                <h2>{category ? 'Edit Category' : 'Create New Category'}</h2>
                <form>
                    <div>
                        <label>Category Name:</label>
                        <input
                            type="text"
                            placeholder='Enter category name'
                            required
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>Description:</label>
                        <textarea
                            placeholder='Enter Description'
                            required
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                        />
                    </div>

                </form>
                <div>
                    <button
                        style={{ marginRight: "10px" }}
                        className='action-button-secondary'
                        onClick={onClose}>
                        Cancel
                    </button>
                    <button
                        type='submit'
                        className='action-button-primary'
                        onClick={handleSubmit}
                    >
                        {category ? 'Update Category' : 'Save Category'}
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default CategoryFormModal
