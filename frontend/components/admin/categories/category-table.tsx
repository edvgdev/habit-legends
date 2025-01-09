import React, { useMemo, } from 'react'
import {
    type MRT_ColumnDef, //if using TypeScript (optional, but recommended)
} from 'material-react-table';
import { Category } from '@/types/category';
import Table from '@/components/table';

interface Props {
    categories: Category[]
    openModal: (category?: Category) => void;
    onDelete: (category: Category) => void;
}

const CategoryTable = ({ categories, openModal, onDelete }: Props) => {

    const columns = useMemo<MRT_ColumnDef<Category>[]>(
        () => [
            {
                accessorKey: "name",
                header: "Name",
            },
            {
                accessorKey: "description",
                header: "Description"
            }
        ], [],
    );


    return (
        <Table
            data={categories}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    )
}

export default CategoryTable
