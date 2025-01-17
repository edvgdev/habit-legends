import SideBar from "@/components/side-bar";
import { Geist, Geist_Mono } from "next/font/google";
import { useRouter } from "next/router";
import { useEffect } from "react";

export default function Home() {
  const router = useRouter();

  useEffect(() => {
    router.push("/daily-quest");
  }, [router])
  return null;
}
