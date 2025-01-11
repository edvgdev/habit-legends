export interface UserPlan {
    id: number | null;
    name: string;
    description: string;
    price: number;
    expMultiplier: number;
    statMultiplier: number;
    createdAt: string | null;
    updatedAt: string | null;
}