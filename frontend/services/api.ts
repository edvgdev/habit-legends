import { Category } from "@/types/category";
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

