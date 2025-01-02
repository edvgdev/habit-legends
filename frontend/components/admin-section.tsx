import React from 'react'

interface Props {
    title: string;
    description: string;
    buttonText: string;
}

const AdminSection = (props: Props) => {
    return (
        <div className='admin-section'>
            <div className='admin-section-details'>
                <h1>{props.title}</h1>
                <p>{props.description}</p>
            </div>
            <button>{props.buttonText}</button>
        </div>
    )
}

export default AdminSection
