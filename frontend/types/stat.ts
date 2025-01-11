export interface Stat {
    id: number | null;
    name: string;
    description: string;
    createdAt: string | null;
    icon: string;
}

export interface StatReward {
    statId: number | null;
    reward: number | null;
}