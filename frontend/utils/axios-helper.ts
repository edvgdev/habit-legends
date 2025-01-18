import axios, { AxiosInstance, AxiosResponse, AxiosRequestConfig, AxiosError } from "axios";
import { useRouter } from "next/router";

// Base Axios instance
const api: AxiosInstance = axios.create({
    baseURL: "/api/",
    withCredentials: true,
});

api.interceptors.response.use(
    (response: AxiosResponse) => response,
    async (error: AxiosError) => {
        const originalRequest = error.config;

        const router = useRouter();

        if (error.response?.status === 401 && !(originalRequest as any)._retry) {
            (originalRequest as any)._retry = true;

            try {
                await api.post('/api/auth/refresh-token');
                return api(originalRequest as AxiosRequestConfig);
            } catch (refreshError) {
                console.error('Failed to refresh tokens:', refreshError);
                router.push('/login');
                throw refreshError;
            }
        }

        if (error.response?.status === 401) {
            router.push('/login');
        }

        return Promise.reject(error);
    }
);

// // Helper function to get access token
// const getAccessToken = (): string | null => {
//     return localStorage.getItem("accessToken");
// };

// // Helper function to refresh token
// const refreshAccessToken = async (): Promise<string | null> => {

//     const router = useRouter();

//     try {
//         const response: AxiosResponse<TokenResponse> = await axios.post("/api/auth/refresh-token", {
//             token: localStorage.getItem("refreshToken"),
//         });
//         const { token } = response.data;
//         localStorage.setItem("accessToken", token);
//         return token;
//     } catch (error: any) {
//         console.error("Failed to refresh token:", error.response?.data?.message || error.message);
//         // Handle token refresh failure (e.g., logout user)
//         localStorage.removeItem("accessToken");
//         localStorage.removeItem("refreshToken");
//         router.push("/login");
//         return null;
//     }
// };

// api.interceptors.request.use(
//     async (config: InternalAxiosRequestConfig): Promise<InternalAxiosRequestConfig> => {
//         const authEndpoints = ["/auth/login", "/auth/register", "/auth/is-email-existing"];

//         // Skip adding Authorization header for auth-related endpoints
//         if (!authEndpoints.some((endpoint) => config.url?.includes(endpoint))) {
//             let token = localStorage.getItem("accessToken");

//             if (token) {
//                 config.headers.Authorization = `Bearer ${token}`;
//             } else {
//                 // If no token or token expired, refresh token
//                 token = await refreshAccessToken();
//                 if (token) {
//                     config.headers.Authorization = `Bearer ${token}`;
//                 }
//             }
//         }

//         return config;
//     },
//     (error) => {
//         return Promise.reject(error);
//     }
// );

// // Response interceptor to handle errors and refresh tokens
// api.interceptors.response.use(
//     (response: AxiosResponse) => response,
//     async (error) => {
//         const originalRequest = error.config;

//         // Handle token expiration
//         if (error.response?.status === 401 && !originalRequest._retry) {
//             originalRequest._retry = true; // Prevent infinite retry loop
//             const token = await refreshAccessToken();
//             if (token) {
//                 originalRequest.headers.Authorization = `Bearer ${token}`;
//                 return api(originalRequest); // Retry original request
//             }
//         }

//         return Promise.reject(error);
//     }
// );

export default api;