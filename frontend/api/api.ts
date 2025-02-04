import { Category } from "@/types/category";
import { HabitAndStatRewards, HabitCompletion, HabitDetails, UserHabit, UserHabitDetails } from "@/types/habit";
import { Rank } from "@/types/Rank";
import { Stat } from "@/types/stat";
import { UserProgressDetails, UserStatDetails } from "@/types/user";
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

export const getUserProfile = async (): Promise<any> => {
    try {
        const response: AxiosResponse = await api.get("user/profile");
        return response.data;
    } catch (error: any) {
        console.error(error)
    }
};

export const getUserProgressDetails = async (userId: number): Promise<UserProgressDetails> => {
    try {
        const response: AxiosResponse<UserProgressDetails> = await api.get(`user-progress/details/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve progress");
    }
}

export const addQuestToUser = async (userHabit: UserHabitDetails): Promise<UserHabit> => {
    try {
        const response: AxiosResponse<UserHabit> = await api.post("user-habit", userHabit);
        return response.data;
    } catch (error: any) {
        console.error(error);
        throw new Error("Failed to add quest");
    }
};

export const getAllUserQuests = async (userId: number): Promise<UserHabitDetails[]> => {
    try {
        const response: AxiosResponse<UserHabitDetails[]> = await api.get(`user-habit/all-details/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve quests");
    }
};

export const submitQuestCompletion = async (completion: HabitCompletion): Promise<HabitCompletion> => {
    try {
        const response: AxiosResponse<HabitCompletion> = await api.post("habit-completion", completion);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to save quest completion");
    }
};

export const getAllQuestCompletion = async (userId: number): Promise<HabitCompletion[]> => {
    try {
        const response: AxiosResponse<HabitCompletion[]> = await api.get(`habit-completion/all-user-completed/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve quest completion");
    }
};

export const getAllQuestCompletionToday = async (userId: number): Promise<HabitCompletion[]> => {
    try {
        const today = new Date();
        const formattedStartDate = today.toISOString().split("T")[0] + "T00:00:00"; // Start of day
        const formattedEndDate = today.toISOString().split("T")[0] + "T23:59:59"; // End of day

        const response: AxiosResponse<HabitCompletion[]> = await api.get('habit-completion',
            {
                params: {
                    userId: userId,
                    startDate: formattedStartDate,
                    endDate: formattedEndDate
                }
            }
        );
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve quest completion");
    }
};

export const getAllUserStatsByUser = async (userId: number): Promise<UserStatDetails[]> => {
    try {
        const response: AxiosResponse<UserStatDetails[]> = await api.get(`user-habit-stat/all-user-stat/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve user stats");
    }
};
