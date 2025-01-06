import { createCategory } from '@/services/api';
import { Category } from '@/types/category';
import React, { useState } from 'react';
import Modal from 'react-modal';
import { toast } from 'react-toastify';


interface Props {
    open: boolean;
    onClose: () => void;
}

const CategoryFormModal = (props: Props) => {

    const [name, setName] = useState('');
    const [description, setDescription] = useState('');

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const newCategory: Category = {
            id: null,
            name,
            description,
            createdAt: null
        }

        try {
            const savedCategory = await createCategory(newCategory);
            console.log(savedCategory)

            toast.success(`Successfully created category: ${savedCategory.name}`)
        } catch (error) {
            toast.error(`Failed to create category ${error}`);
        }

        console.log(name);
        console.log(description);
        props.onClose();
    };

    return (
        <Modal
            isOpen={props.open}
            onRequestClose={props.onClose}
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
                <h2>Create New Category</h2>
                <form>
                    <div>
                        <label>Category Name:</label>
                        <input
                            type="text"
                            placeholder='Enter category name'
                            required
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div>
                        <label>Description:</label>
                        <textarea
                            placeholder='Enter Description'
                            required
                            onChange={(e) => setDescription(e.target.value)}
                        />
                    </div>

                </form>
                <div>
                    <button style={{ marginRight: "10px" }} className='action-button-secondary' onClick={props.onClose}>Cancel</button>
                    <button
                        type='submit'
                        className='action-button-primary'
                        onClick={handleSubmit}
                    >
                        Save Category
                    </button>
                </div>
            </div>
        </Modal>
    )
}

export default CategoryFormModal
