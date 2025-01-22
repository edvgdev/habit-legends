import { create } from 'zustand';
import { createJSONStorage, persist } from 'zustand/middleware';

export interface UserProfile {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    profilePictureLink: string;
    planId: number | null;
    role: string;
};


interface UserStore {
    userProfile: UserProfile | null;
    isAuthenticated: boolean;
    setUserProfile: (profile: UserProfile) => void;
    setIsAuthenticated: (isAuthenticated: boolean) => void;
    clearUserProfile: () => void;
}

const useUserStore = create<UserStore>()(
    persist(
        (set) => ({
            userProfile: null,
            isAuthenticated: false,
            setUserProfile: (profile: UserProfile) => set({ userProfile: profile }),
            setIsAuthenticated: (isAuthenticated: boolean) => set({ isAuthenticated }),
            clearUserProfile: () => set({ userProfile: null }),
        }),
        {
            name: 'user-storage', // unique name for the storage key
            storage: createJSONStorage(() => localStorage), // or sessionStorage
            partialize: (state) => ({
                userProfile: state.userProfile,
                isAuthenticated: state.isAuthenticated,
            }),
        }
    )
);


export default useUserStore;