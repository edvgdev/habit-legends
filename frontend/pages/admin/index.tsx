import AdminSection from '@/components/admin/admin-section'
import { useRouter } from 'next/router'
import React from 'react'
import AdminCategories from './categories';
import Quests from './quests';
import Ranks from './ranks';
import Stats from './stats';
import UserPlans from './user-plans';


const Admin = () => {

    const router = useRouter();

    const { subpage } = router.query;

    const renderContent = () => {
        switch (subpage) {
            case "quests":
                return <Quests />;
            case "categories":
                return <AdminCategories />;
            case "ranks":
                return <Ranks />;
            case "stats":
                return <Stats />;
            case "user-plans":
                return <UserPlans />;
            default:
                return (
                    <>
                        <h1>Admin Page</h1>
                        <AdminSection
                            title='Quests'
                            description='View, add, edit, and delete quests.'
                            buttonText='Manage'
                            onClick={() => navigateToSubpage("quests")}
                        />
                        <AdminSection
                            title='Categories'
                            description='View, add, edit, and delete Categories.'
                            buttonText='Manage'
                            onClick={() => navigateToSubpage("categories")}
                        />
                        <AdminSection
                            title='Ranks'
                            description='View, add, edit, and delete Ranks.'
                            buttonText='Manage'
                            onClick={() => navigateToSubpage("ranks")}
                        />
                        <AdminSection
                            title='Stats'
                            description='View, add, edit, and delete Stats.'
                            buttonText='Manage'
                            onClick={() => navigateToSubpage("stats")}
                        />
                        <AdminSection
                            title='User Plans'
                            description='View, add, edit, and delete User Plans.'
                            buttonText='Manage'
                            onClick={() => navigateToSubpage("user-plans")}
                        />
                    </>
                );
        }
    };

    const navigateToSubpage = (subpage: string) => {
        router.push(`/admin?subpage=${subpage}`);
    };

    return (
        <div className='admin'>
            <div className='admin-container'>
                {renderContent()}
            </div>

        </div>
    )
}

export default Admin
