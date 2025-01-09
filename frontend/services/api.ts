import { Category } from "@/types/category";
import { Stat } from "@/types/stat";
import axios, { AxiosInstance, AxiosResponse } from "axios";

const api: AxiosInstance = axios.create({
    baseURL: "/api/"
});

export const createCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.post("habit-category", category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create category");
    }
}

export const updateCategory = async (category: Category): Promise<Category> => {
    try {
        const response: AxiosResponse<Category> = await api.put(`habit-category/${category.id}`, category);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update category");
    }
}

export const getCategories = async (): Promise<Category[]> => {
    try {
        const response: AxiosResponse<Category[]> = await api.get("habit-category/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve category: ${error.response?.status}`);
    }
}

export const deleteCategory = async (id: number): Promise<void> => {
    try {
        await api.delete(`habit-category/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete category");
    }
};

export const createStat = async (stat: Stat): Promise<Stat> => {
    try {
        const response: AxiosResponse<Stat> = await api.post("stat", stat);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to create stat");
    }
}

export const updateStat = async (stat: Stat): Promise<Stat> => {
    try {
        const response: AxiosResponse<Stat> = await api.put(`stat/${stat.id}`, stat);
        return response.data;
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to update stat");
    }
}

export const getStats = async (): Promise<Stat[]> => {
    try {
        const response: AxiosResponse<Stat[]> = await api.get("stat/all");
        return response.data;
    } catch (error: any) {
        throw new Error(`Failed to retrieve stat: ${error.response?.status}`);
    }
}

export const deleteStat = async (id: number): Promise<void> => {
    try {
        await api.delete(`stat/${id}`);
    } catch (error: any) {
        console.error(error.response?.status);
        throw new Error("Failed to delete stat");
    }
};

