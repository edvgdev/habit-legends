import Table from '@/components/table';
import { QuestDetails } from '@/types/quest';
import { Box } from '@mui/material';
import { MRT_ColumnDef } from 'material-react-table';
import React, { useMemo } from 'react'

interface Props {
    questDetails: QuestDetails[]
    openModal: (quest?: QuestDetails) => void;
    onDelete: (quest: QuestDetails) => void;
}

const QuestTable = ({ questDetails, openModal, onDelete }: Props) => {
    const columns = useMemo<MRT_ColumnDef<QuestDetails>[]>(
        () => [
            {
                accessorKey: "quest.name",
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
                            height={50}
                            src={row.original.quest.imageLink}
                            loading="lazy"
                            style={{ borderRadius: '50%', display: "flex" }}
                        />
                        {/* using renderedCellValue instead of cell.getValue() preserves filter match highlighting */}
                        <span>{renderedCellValue}</span>
                    </Box>
                ),
            },
            {
                accessorKey: "quest.description",
                header: "Description"
            },
            {
                accessorKey: "categoryName",
                header: "Category"
            },
        ], [],
    );

    return (
        <Table
            data={questDetails}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    )
}

export default QuestTable
