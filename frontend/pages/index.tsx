import { getUserProgressDetails } from "@/api/api";
import { useAuth } from "@/context/auth-context";
import useUserStore from "@/types/user";
import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Home() {
  const router = useRouter();
  const { setUserProgressDetails, userProfile } = useUserStore();

  useEffect(() => {
    retrieveProgress(userProfile?.id!);
    router.push("/daily-quest");
  }, [router]);

  const retrieveProgress = async (userId: number) => {
    const progress = await getUserProgressDetails(userId);
    setUserProgressDetails(progress);
  }
  return null;
}
