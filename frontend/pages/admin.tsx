import AdminSection from '@/components/admin-section'
import React from 'react'

const Admin = () => {
    return (
        <div className='admin'>
            <div className='admin-container'>
                <h1>Admin Page</h1>
                <AdminSection title='Habits' description='View, add, edit, and delete Habits.' buttonText='Manage Habits' />
                <AdminSection title='Categories' description='View, add, edit, and delete Categories.' buttonText='Manage Categories' />
                <AdminSection title='Ranks' description='View, add, edit, and delete Ranks.' buttonText='Manage Ranks' />
                <AdminSection title='Stats' description='View, add, edit, and delete Stats.' buttonText='Manage Stats' />
                <AdminSection title='User Plans' description='View, add, edit, and delete User Plans.' buttonText='Manage User Plans' />
            </div>
        </div>
    )
}

export default Admin
