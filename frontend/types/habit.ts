import { StatReward } from "./stat";

export interface Habit {
    id: number | null;
    name: string;
    description: string;
    categoryId: number;
    baseExpReward: number;
    imageLink: string;
    createdAt: string | null;
    updatedAt: string | null;
};

export interface HabitAndStatRewards {
    habit: Habit;
    statRewards: StatReward[];
};

export interface HabitDetails {
    habit: Habit;
    categoryName: string;
    habitStatRewards: HabitStatReward[];
};

export interface HabitStatReward {
    id: number | null;
    habitId: number | null;
    statId: number | null;
    baseStatReward: number;
    createdAt: string | null;
    updatedAt: string | null;
};

export interface UserHabitDetails {
    userId: number | null;
    habitDetails: HabitDetails;
};

export interface UserHabit {
    id: number;
    userId: number;
    habitId: number;
    createdAt: string;
}