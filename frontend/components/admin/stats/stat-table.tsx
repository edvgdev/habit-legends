import Table from '@/components/table';
import { Stat } from '@/types/stat';
import { Box } from '@mui/material';
import { MRT_ColumnDef } from 'material-react-table';
import React, { useMemo } from 'react'

interface Props {
    stats: Stat[]
    openModal: (stat?: Stat) => void;
    onDelete: (stat: Stat) => void;
}

const StatTable = ({ stats, openModal, onDelete }: Props) => {

    const columns = useMemo<MRT_ColumnDef<Stat>[]>(
        () => [
            {
                accessorKey: "name",
                header: "Name",
                Cell: ({ renderedCellValue, row }) => (
                    <Box
                        sx={{
                            display: 'flex',
                            alignItems: 'center',
                            gap: '1.5rem',
                        }}
                    >
                        <img
                            alt="avatar"
                            height={30}
                            src={row.original.icon}
                            loading="lazy"
                            style={{ borderRadius: '50%', display: "flex" }}
                        />
                        {/* using renderedCellValue instead of cell.getValue() preserves filter match highlighting */}
                        <span>{renderedCellValue}</span>
                    </Box>
                ),
            },
            {
                accessorKey: "description",
                header: "Description"
            }
        ], [],
    );

    return (
        <Table
            data={stats}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    )
}

export default StatTable
