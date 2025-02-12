import axios, { AxiosInstance, AxiosResponse, AxiosRequestConfig, AxiosError } from "axios";

// Base Axios instance
const api: AxiosInstance = axios.create({
    baseURL: "/api/",
    withCredentials: true,
});

api.interceptors.response.use(
    (response: AxiosResponse) => response,
    async (error: AxiosError) => {
        const originalRequest = error.config;

        // Skip refresh token logic if it's a logout request
        if (originalRequest && originalRequest.url?.includes('/auth/logout')) {
            return Promise.reject(error);  // Do nothing on logout request
        }

        if ((error.response?.status === 401 && !(originalRequest as any)._retry)) {

            (originalRequest as any)._retry = true;

            try {
                await api.post('/auth/refresh-token');
                return api(originalRequest as AxiosRequestConfig);
            } catch (refreshError) {
                console.error('Failed to refresh tokens:', refreshError);
                window.location.href = "/login";
                throw refreshError;
            }
        }

        if (error.response?.status === 401) {
            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);


export default api;