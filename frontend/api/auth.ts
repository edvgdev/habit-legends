import { LoginRequest, RegistrationRequest, RegistrationResponse, TokenResponse } from "@/types/authentication";
import api from "@/utils/axios-helper";
import { AxiosResponse } from "axios";

export const login = async (loginRequest: LoginRequest): Promise<TokenResponse> => {

    try {
        const response: AxiosResponse<TokenResponse> = await api.post("/auth/login", loginRequest);

        if (response.data.token) {
            localStorage.setItem("accessToken", response.data.token);
            localStorage.setItem("refreshToken", response.data.refreshToken);
        }
        return response.data;
    } catch (error: any) {
        throw new Error(error.response?.data?.message || "Failed to login");
    }
}

export const register = async (registrationRequest: RegistrationRequest): Promise<RegistrationResponse> => {
    try {
        const response: AxiosResponse<RegistrationResponse> = await api.post("/auth/register", registrationRequest);
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