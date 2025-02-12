import { LoginRequest, RegistrationRequest, AuthResponse, TokenResponse } from "@/types/authentication";
import api from "@/utils/axios-helper";
import publicApi from "@/utils/public-api-helper";
import { AxiosResponse } from "axios";

export const loginAuth = async (loginRequest: LoginRequest): Promise<AuthResponse> => {

    try {
        const response: AxiosResponse<AuthResponse> = await api.post("/auth/login", loginRequest);
        return response.data;
    } catch (error: any) {
        throw new Error(error.response?.status || "Failed to login");
    }
}

export const register = async (registrationRequest: RegistrationRequest): Promise<AuthResponse> => {
    try {
        const response: AxiosResponse<AuthResponse> = await api.post("/auth/register", registrationRequest);
        return response.data
    } catch (error: any) {
        throw new Error(error.response?.data?.message || "Failed to register");
    }
};

export const logout = (): void => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken");
    // window.location.href = "/login"; // Redirect to login page
};

export const checkEmailAvailability = async (email: string): Promise<boolean> => {
    try {
        const response = await api.get(`/auth/is-email-existing/${email}`);
        return !response.data;
    } catch (error) {
        console.log("it catches error");
        return false; // If API call fails, assume email exists
    }
};

export const isUserAuthenticated = async (): Promise<boolean> => {
    try {
        const response: AxiosResponse<boolean> = await api.get('/auth/is-authenticated/');
        return response.data;
    } catch (error) {
        console.log("it catches error");
        return false; // If API call fails, assume email exists
    }
};

export const logoutUser = async (email: string): Promise<void> => {
    try {
        await api.post(`/auth/logout?email=${email}`);
    } catch (error) {
        console.error("Logout failed");
    }
}