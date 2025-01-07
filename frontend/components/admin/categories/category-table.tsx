import React, { useEffect, useMemo, useState } from 'react'
import {
    MaterialReactTable,
    useMaterialReactTable,
    type MRT_ColumnDef, //if using TypeScript (optional, but recommended)
} from 'material-react-table';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Category } from '@/types/category';
import { getCategories } from '@/services/api';
import { ListItemIcon, MenuItem } from '@mui/material';
import { FaEdit, FaTrash } from 'react-icons/fa';

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

    const table = useMaterialReactTable({
        columns,
        data: categories,
        enableRowActions: true,
        mrtTheme: {
            baseBackgroundColor: "#232C43",
        },
        enableColumnPinning: true,
        initialState: {
            showColumnFilters: true,
            showGlobalFilter: true,
            columnPinning: {
                right: ['mrt-row-actions'],
            },
        },
        renderRowActionMenuItems: ({ row, closeMenu }) => [
            <MenuItem
                key={0}
                onClick={() => {
                    openModal(row.original);
                    closeMenu();
                }}
                sx={{ m: 0 }}
            >
                <ListItemIcon>
                    <FaEdit />
                </ListItemIcon>
                Edit
            </MenuItem>,
            <MenuItem
                key={1}
                onClick={() => {
                    onDelete(row.original);
                    closeMenu();
                }}
                sx={{ m: 0 }}
            >
                <ListItemIcon>
                    <FaTrash />
                </ListItemIcon>
                Delete
            </MenuItem>,
        ],
    });



    const darkTheme = useMemo(
        () =>
            createTheme({
                palette: {
                    mode: 'dark',
                    primary: {
                        main: '#90caf9',
                    },
                    background: {
                        default: '#121212',
                        paper: '#1e1e1e',
                    },
                    text: {
                        primary: '#ffffff', // White text for main content
                        secondary: '#bdbdbd', // Gray for secondary text
                    },
                },
                typography: {
                    fontSize: 20, // Equivalent to 1rem (16px)
                },
                components: {
                    MuiTypography: {
                        styleOverrides: {
                            root: {
                                fontSize: '1rem', // Ensures all typography components use 1rem
                            },
                        },
                    },
                    MuiPaper: {
                        styleOverrides: {
                            root: {
                                borderRadius: '12px', // Rounded corners
                                boxShadow: '0px 4px 10px rgba(0, 0, 0, 0.3)',
                                padding: '1rem 0'
                            },
                        },
                    },
                },
            }),
        []
    );

    return (
        <div>
            <ThemeProvider theme={darkTheme}>
                <MaterialReactTable table={table} />
            </ThemeProvider>
        </div>
    )
}

export default CategoryTable
