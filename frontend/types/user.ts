import { create } from 'zustand';

export interface UserProfile {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    profilePictureLink: string;
    planId: number | null;
    role: string;
}

interface UserStore {
    userProfile: UserProfile | null;
    setUserProfile: (profile: UserProfile) => void;
    clearUserProfile: () => void;
}

const useUserStore = create<UserStore>((set) => ({
    userProfile: null,
    setUserProfile: (profile: UserProfile) => set({ userProfile: profile }),
    clearUserProfile: () => set({ userProfile: null }),
}));

export default useUserStore;