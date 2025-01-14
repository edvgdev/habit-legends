import { Category } from "@/types/category";
import { HabitAndStatRewards, HabitDetails } from "@/types/habit";
import { Rank } from "@/types/Rank";
import { Stat } from "@/types/stat";
import { UserPlan } from "@/types/user-plan";
import api from "@/utils/axios-helper";
import axios, { AxiosInstance, AxiosResponse } from "axios";


// Categories

export const createCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.post("habit-category", category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create category");
    }
};

export const updateCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.put(`habit-category/${category.id}`, category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update category");
    }
};

export const getCategories = async (): Promise<Category[]> => {
    try {
        const response: AxiosResponse<Category[]> = await api.get("habit-category/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve category: ${error.response?.status}`);
    }
};

export const deleteCategory = async (id: number): Promise<void> => {
    try {
        await api.delete(`habit-category/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete category");
    }
};

// Stats

export const createStat = async (stat: Stat): Promise<Stat> => {
    try {
        const response: AxiosResponse<Stat> = await api.post("stat", stat);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create stat");
    }
};

export const updateStat = async (stat: Stat): Promise<Stat> => {
    try {
        const response: AxiosResponse<Stat> = await api.put(`stat/${stat.id}`, stat);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update stat");
    }
};

export const getStats = async (): Promise<Stat[]> => {
    try {
        const response: AxiosResponse<Stat[]> = await api.get("stat/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve stat: ${error.response?.status}`);
    }
};

export const deleteStat = async (id: number): Promise<void> => {
    try {
        await api.delete(`stat/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete stat");
    }
};

// Ranks

export const createRank = async (rank: Rank): Promise<Rank> => {
    try {
        const response: AxiosResponse<Rank> = await api.post("rank", rank);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create rank");
    }
};

export const updateRank = async (rank: Rank): Promise<Rank> => {
    try {
        const response: AxiosResponse<Rank> = await api.put(`rank/${rank.id}`, rank);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update rank");
    }
};

export const getRanks = async (): Promise<Rank[]> => {
    try {
        const response: AxiosResponse<Rank[]> = await api.get("rank/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve rank: ${error.response?.status}`);
    }
};

export const deleteRank = async (id: number): Promise<void> => {
    try {
        await api.delete(`rank/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete rank");
    }
};

// User plan

export const createUserPlan = async (userPlan: UserPlan): Promise<UserPlan> => {
    try {
        const response: AxiosResponse<UserPlan> = await api.post("user-plan", userPlan);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create UserPlan");
    }
};

export const updateUserPlan = async (userPlan: UserPlan): Promise<UserPlan> => {
    try {
        const response: AxiosResponse<UserPlan> = await api.put(`user-plan/${userPlan.id}`, userPlan);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update UserPlan");
    }
};

export const getUserPlans = async (): Promise<UserPlan[]> => {
    try {
        const response: AxiosResponse<UserPlan[]> = await api.get("user-plan/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve UserPlan: ${error.response?.status}`);
    }
};

export const deleteUserPlan = async (id: number): Promise<void> => {
    try {
        await api.delete(`user-plan/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete UserPlan");
    }
};

// Habit

export const createHabit = async (habitStatRewards: HabitAndStatRewards): Promise<HabitDetails> => {
    try {
        const response: AxiosResponse<HabitDetails> = await api.post("habit", habitStatRewards);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create Habit");
    }
};

export const updateHabit = async (habitStatRewards: HabitAndStatRewards): Promise<HabitDetails> => {
    try {
        const response: AxiosResponse<HabitDetails> = await api.put(`habit/${habitStatRewards.habit.id}`, habitStatRewards);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update habit");
    }
};

export const getHabits = async (): Promise<HabitDetails[]> => {
    try {
        const response: AxiosResponse<HabitDetails[]> = await api.get("habit/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve Habits: ${error.response?.status}`);
    }
};

export const deleteHabit = async (id: number): Promise<void> => {
    try {
        await api.delete(`habit/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete habit");
    }
};




