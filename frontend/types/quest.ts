import { StatReward } from "./stat";

export interface Quest {
    id: number | null;
    name: string;
    description: string;
    categoryId: number;
    baseExpReward: number;
    imageLink: string;
    createdAt: string | null;
    updatedAt: string | null;
};

export interface QuestAndStatRewards {
    quest: Quest;
    statRewards: StatReward[];
};

export interface QuestDetails {
    quest: Quest;
    categoryName: string;
    questStatRewards: QuestStatReward[];
};

export interface QuestStatReward {
    id: number | null;
    questId: number | null;
    statId: number | null;
    baseStatReward: number;
    createdAt: string | null;
    updatedAt: string | null;
};

export interface UserQuestDetails {
    userId: number | null;
    questDetails: QuestDetails;
};

export interface UserQuest {
    id: number;
    userId: number;
    questId: number;
    createdAt: string;
};

export interface QuestCompletion {
    id: number | null;
    userId: number;
    questId: number;
    completedAt: string | null;
    description: string;
    expEarned: number;
}

export interface QuestCompletionFilterDetails {
    userId: number | null;
    questId: number | null;
    startDate: string | null;
    endDate: string | null;
    description: string | null;
}