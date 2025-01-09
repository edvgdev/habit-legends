import Table from '@/components/table';
import { Rank } from '@/types/Rank';
import { MRT_ColumnDef } from 'material-react-table';
import React, { useMemo } from 'react'

interface Props {
    ranks: Rank[]
    openModal: (rank?: Rank) => void;
    onDelete: (rank: Rank) => void;
}

const RankTable = ({ ranks, openModal, onDelete }: Props) => {

    const columns = useMemo<MRT_ColumnDef<Rank>[]>(
        () => [
            {
                accessorKey: "name",
                header: "Name",
            },
            {
                accessorKey: "minExp",
                header: "Minimum Exp"
            }
        ], [],
    );

    return (
        <Table
            data={ranks}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    )
}

export default RankTable
