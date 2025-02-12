import { getUserProfile, getUserProgressDetails } from '@/api/api';
import { loginAuth, logoutUser } from '@/api/auth';
import LoadingModal from '@/components/loading-modal';
import { LoginRequest } from "@/types/authentication";
import useUserStore from "@/types/user";
import { set } from 'lodash';
import { useRouter } from "next/router";
import { createContext, useContext, useEffect, useRef, useState } from "react";

interface AuthContextType {
    login: (credentials: any) => Promise<void>;
    logout: () => void;
    googleLogin: () => void;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const AuthProvider = ({ children }: { children: React.ReactNode }) => {
    const { userProfile, setUserProfile, clearUserProfile, isAuthenticated, setIsAuthenticated, setUserProgressDetails } = useUserStore();
    const router = useRouter();
    const isInitialRender = useRef(true);
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const checkAuth = async () => {
            if (isInitialRender.current) {
                isInitialRender.current = false; // Skip the initial render
                return;
            }
            try {
                if (isAuthenticated === false && !userProfile) {

                    if (router.pathname !== "/login" && router.pathname !== "/register") {
                        router.push("/login");
                    }
                }
            } catch (error) {
                // If the check fails, clear the user profile and redirect to login
                clearUserProfile();

                if (router.pathname !== "/login" && router.pathname !== "/register") {
                    router.push("/login");
                }
            }
        };

        const checkUrlParams = async () => {

            // Check for OAuth2 success
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.get('login') === 'success') {
                setIsLoading(true);
                await getCurrentUser(); // Fetch the user profile
                setIsAuthenticated(true);
                window.history.replaceState({}, document.title, window.location.pathname);
                setTimeout(() => {
                    setIsLoading(false);
                }, 1000);

            }

        }
        checkAuth();
        checkUrlParams();
    }, [router, isAuthenticated, userProfile]);

    // Login function
    const login = async (credentials: LoginRequest) => {
        setIsLoading(true);
        try {
            const response = await loginAuth(credentials); // Replace with your login API call
            if (response.statusCode === 200) {
                setIsAuthenticated(true);
                await getCurrentUser();
                setTimeout(() => {
                    setIsLoading(false);
                }, 1000);
            }
        } catch (error) {
            console.error("Login failed:", error);
        }
    };

    const getCurrentUser = async () => {
        const profile = await getUserProfile();
        setUserProfile(profile);
        const progress = await getUserProgressDetails();
        setUserProgressDetails(progress);
        if (!profile) {
            router.push("/login");
        } else {
            router.push("/");
            setIsAuthenticated(true);
        }
    }

    const googleLogin = () => {
        window.location.href = 'http://localhost:8080/oauth2/authorization/google';
    }

    // Logout function
    const logout = async () => {
        const email = userProfile?.email;
        clearUserProfile();
        localStorage.removeItem("user-storage");
        await logoutUser(email!);
        setIsAuthenticated(false);
        router.push("/login");
    };


    return (
        <AuthContext.Provider value={{ login, logout, googleLogin }}>
            {children}
            <LoadingModal
                isOpen={isLoading} // Control modal visibility
                onClose={() => setIsLoading(false)} // Close the modal
            />
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