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

export interface UserProgressDetails {
    userId: number;
    level: number;
    exp: number;
    rank: string;
    minExpToNextRank: number;
    expRequiredForNextLevel: number;
}

export interface UserStatDetails {
    userId: number;
    statName: string;
    currentPoints: number;
    iconUrl: string;
}


interface UserStore {
    userProfile: UserProfile | null;
    userProgressDetails: UserProgressDetails | null;
    isAuthenticated: boolean;
    setUserProfile: (profile: UserProfile) => void;
    setUserProgressDetails: (details: UserProgressDetails) => void;
    setIsAuthenticated: (isAuthenticated: boolean) => void;
    clearUserProfile: () => void;
}

const useUserStore = create<UserStore>()(
    persist(
        (set) => ({
            userProfile: null,
            userProgressDetails: null,
            isAuthenticated: false,
            setUserProfile: (profile: UserProfile) => set({ userProfile: profile }),
            setUserProgressDetails: (details: UserProgressDetails) => set({ userProgressDetails: details }),
            setIsAuthenticated: (isAuthenticated: boolean) => set({ isAuthenticated }),
            clearUserProfile: () => set({ userProfile: null, userProgressDetails: null, isAuthenticated: false }),
        }),
        {
            name: 'user-storage', // unique name for the storage key
            storage: createJSONStorage(() => localStorage), // or sessionStorage
            partialize: (state) => ({
                userProfile: state.userProfile,
                isAuthenticated: state.isAuthenticated,
                userProgressDetails: state.userProgressDetails,
            }),
        }
    )
);


export default useUserStore;