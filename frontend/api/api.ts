import { Category } from "@/types/category";
import { QuestAndStatRewards, QuestCompletion, QuestCompletionFilterDetails, QuestDetails, UserQuest, UserQuestDetails } from "@/types/quest";
import { Rank } from "@/types/Rank";
import { Stat } from "@/types/stat";
import { UserProgressDetails, UserStatDetails } from "@/types/user";
import { UserPlan } from "@/types/user-plan";
import api from "@/utils/axios-helper";
import axios, { AxiosInstance, AxiosResponse } from "axios";


// Categories

export const createCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.post("category", category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create category");
    }
};

export const updateCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.put(`category/${category.id}`, category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update category");
    }
};

export const getCategories = async (): Promise<Category[]> => {
    try {
        const response: AxiosResponse<Category[]> = await api.get("category/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve category: ${error.response?.status}`);
    }
};

export const deleteCategory = async (id: number): Promise<void> => {
    try {
        await api.delete(`category/${id}`);
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

// Quests

export const createQuest = async (questStatRewards: QuestAndStatRewards): Promise<QuestDetails> => {
    try {
        const response: AxiosResponse<QuestDetails> = await api.post("quest", questStatRewards);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create Quest");
    }
};

export const updateQuest = async (questStatRewards: QuestAndStatRewards): Promise<QuestDetails> => {
    try {
        const response: AxiosResponse<QuestDetails> = await api.put(`quest/${questStatRewards.quest.id}`, questStatRewards);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update quest");
    }
};

export const getQuests = async (): Promise<QuestDetails[]> => {
    try {
        const response: AxiosResponse<QuestDetails[]> = await api.get("quest/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve Quests: ${error.response?.status}`);
    }
};

export const deleteQuest = async (id: number): Promise<void> => {
    try {
        await api.delete(`quest/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete quest");
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

export const addQuestToUser = async (userQuestDetails: UserQuestDetails): Promise<UserQuest> => {
    try {
        const response: AxiosResponse<UserQuest> = await api.post("user-quest", userQuestDetails);
        return response.data;
    } catch (error: any) {
        console.error(error);
        throw new Error("Failed to add quest");
    }
};

export const getAllUserQuests = async (userId: number): Promise<UserQuestDetails[]> => {
    try {
        const response: AxiosResponse<UserQuestDetails[]> = await api.get(`user-quest/all-details/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve quests");
    }
};

export const submitQuestCompletion = async (completion: QuestCompletion): Promise<QuestCompletion> => {
    try {
        const response: AxiosResponse<QuestCompletion> = await api.post("quest-completion", completion);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to save quest completion");
    }
};

export const getAllQuestCompletions = async (filterDetails: QuestCompletionFilterDetails): Promise<QuestCompletion[]> => {
    try {
        const response: AxiosResponse<QuestCompletion[]> = await api.get('quest-completion',
            {
                params: filterDetails
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
        const response: AxiosResponse<UserStatDetails[]> = await api.get(`stat-progress/all-user-stat/${userId}`);
        return response.data;
    } catch (error) {
        console.error(error);
        throw new Error("Failed to retrieve user stats");
    }
};
