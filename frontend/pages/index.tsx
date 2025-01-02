import SideBar from "@/components/side-bar";
import { Geist, Geist_Mono } from "next/font/google";
import { useRouter } from "next/router";
import { useEffect } from "react";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export default function Home() {
  const router = useRouter();

  useEffect(() => {
    router.push("/daily-quest");
  }, [router])
  return null;
}
