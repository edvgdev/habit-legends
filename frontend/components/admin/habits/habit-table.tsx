import Table from '@/components/table';
import { HabitDetails } from '@/types/habit';
import { Box } from '@mui/material';
import { MRT_ColumnDef } from 'material-react-table';
import React, { useMemo } from 'react'

interface Props {
    habitDetails: HabitDetails[]
    openModal: (habit?: HabitDetails) => void;
    onDelete: (habit: HabitDetails) => void;
}

const HabitTable = ({ habitDetails, openModal, onDelete }: Props) => {
    const columns = useMemo<MRT_ColumnDef<HabitDetails>[]>(
        () => [
            {
                accessorKey: "habit.name",
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
                            src={row.original.habit.imageLink}
                            loading="lazy"
                            style={{ borderRadius: '50%', display: "flex" }}
                        />
                        {/* using renderedCellValue instead of cell.getValue() preserves filter match highlighting */}
                        <span>{renderedCellValue}</span>
                    </Box>
                ),
            },
            {
                accessorKey: "habit.description",
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
            data={habitDetails}
            columns={columns}
            openModal={openModal}
            onDelete={onDelete}
        />
    )
}

export default HabitTable
