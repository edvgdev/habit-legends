import { getUserProfile } from '@/api/api';
import { isUserAuthenticated, loginAuth, logoutUser } from '@/api/auth';
import { LoginRequest } from "@/types/authentication";
import useUserStore, { UserProfile } from "@/types/user";
import { useRouter } from "next/router";
import { createContext, useContext, useEffect, useState } from "react";

interface AuthContextType {
    isAuthenticated: boolean;
    login: (credentials: any) => Promise<void>;
    logout: () => void;
    googleLogin: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const { userProfile, setUserProfile, clearUserProfile } = useUserStore();
    const router = useRouter();

    useEffect(() => {
        const checkAuth = async () => {
            try {
                if (isAuthenticated === false && !userProfile) {
                    // If not authenticated, clear the user profile and redirect to login
                    clearUserProfile();
                    setIsAuthenticated(false);
                    console.log("not auth");
                    console.log(userProfile);

                    if (router.pathname !== "/login" && router.pathname !== "/register") {
                        router.push("/login");
                    }
                } else {
                    // If authenticated, fetch the full user profile
                    console.log("authenticated");
                    setIsAuthenticated(true);
                    console.log(userProfile);
                }
            } catch (error) {
                // If the check fails, clear the user profile and redirect to login
                clearUserProfile();
                setIsAuthenticated(false);

                if (router.pathname !== "/login" && router.pathname !== "/register") {
                    router.push("/login");
                }
            }
        };

        // Check for OAuth2 success
        const urlParams = new URLSearchParams(window.location.search);
        if (urlParams.get('login') === 'success') {
            getCurrentUser(); // Fetch the user profile
            setIsAuthenticated(true);
            window.history.replaceState({}, document.title, window.location.pathname);
        }

        checkAuth();
    }, [router, isAuthenticated, userProfile]);

    // Login function
    const login = async (credentials: LoginRequest) => {
        const response = await loginAuth(credentials); // Replace with your login API call
        if (response.statusCode === 200) {
            setIsAuthenticated(true);
            getCurrentUser();
            router.push("/");
        }
    };

    const getCurrentUser = async () => {
        const profile = await getUserProfile();
        setUserProfile(profile);
        if (!profile) {
            router.push("/");
        }
    }

    const googleLogin = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    }

    // Logout function
    const logout = async () => {
        await logoutUser();
        clearUserProfile();
        setIsAuthenticated(false);
        router.push("/login");
    };

    return (
        <AuthContext.Provider value={{ isAuthenticated, login, logout, googleLogin }}>
            {children}
        </AuthContext.Provider>
    );
};

export const useAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error("useAuth must be used within an AuthProvider");
    }
    return context;
};