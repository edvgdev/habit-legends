import Table from '@/components/table';
import { UserPlan } from '@/types/user-plan';
import { MRT_ColumnDef } from 'material-react-table';
import React, { useMemo } from 'react';

interface Props {
    userPlans: UserPlan[]
    openModal: (userPlan?: UserPlan) => void;
    onDelete: (userPlan: UserPlan) => void;
}

const UserPlanTable = ({ userPlans, openModal, onDelete }: Props) => {

    const columns = useMemo<MRT_ColumnDef<UserPlan>[]>(
        () => [
            {
                accessorKey: "name",
                header: "Name",
                size: 150,
            },
            {
                accessorKey: "description",
                header: "Description"
            },
            {
                accessorKey: "price",
                header: "Price",
                size: 100,
                Cell: ({ cell }) => `$ ${cell.getValue<number>().toFixed(2)}`,
            },
            {
                accessorKey: "expMultiplier",
                header: "Experience Multiplier",
                size: 150,
            },
            {
                accessorKey: "statMultiplier",
                header: "Stat Point Multiplier",
                size: 150,
            }
        ], [],
    );

    return (
        <Table
            data={userPlans}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    );
}

export default UserPlanTable
