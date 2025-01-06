import { Category } from "@/types/category"
import { ColumnDef } from "@tanstack/react-table"



export const Columns: ColumnDef<Category>[] = [
    {
        accessorKey: 'name',
        header: 'Name',
    },
    {
        accessorKey: 'description',
        header: 'Description',
    },
    {
        id: 'actions',
        header: 'Actions',
        cell: ({ row }) => (
            <div className="flex space-x-2">
                <button
                    className="action-button-primary"
                    onClick={() => console.log('Edit:', row.original.id)}
                >
                    Edit
                </button>
                <button
                    className="action-button-secondary"
                    onClick={() => console.log('Delete:', row.original.id)}
                >
                    Delete
                </button>
            </div>
        ),
    },
];