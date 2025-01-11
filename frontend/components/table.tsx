import { createTheme, ListItemIcon, MenuItem, ThemeProvider } from '@mui/material';
import { MaterialReactTable, MRT_ColumnDef, MRT_RowData, useMaterialReactTable } from 'material-react-table'
import React, { useMemo } from 'react'
import { FaEdit, FaTrash } from 'react-icons/fa';

interface Props<T extends MRT_RowData> {
    data: T[];
    columns: MRT_ColumnDef<T>[];
    openModal: (item?: T) => void;
    onDelete: (item: T) => void;
}

const Table = <T extends MRT_RowData>({ data, columns, openModal, onDelete }: Props<T>) => {

    const table = useMaterialReactTable({
        columns,
        data,
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
        renderRowActionMenuItems: ({ row, closeMenu }) => {
            return [
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
            ];
        },
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
                                padding: '1rem 0',
                                maxWidth: "100%",
                                overflowX: "auto"
                            },
                        },
                    },
                },
            }),
        []
    );

    return (
        <ThemeProvider theme={darkTheme}>
            <MaterialReactTable table={table} />
        </ThemeProvider>

    )
}

export default Table
