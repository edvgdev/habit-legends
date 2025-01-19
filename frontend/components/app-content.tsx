import { useAuth } from "@/context/auth-context";
import { poppins } from "@/utils/fonts";
import { useRouter } from "next/router";
import SideBar from "./side-bar";
import { useEffect, useState } from "react";
import { getUserProfile } from "@/api/api";
import useUserStore from "@/types/user";

const AppContent = ({ Component, pageProps, isAuthPage }: { Component: any; pageProps: any; isAuthPage: boolean }) => {

    const router = useRouter();
    const { isAuthenticated } = useUserStore();

    // Render a loading state while authentication is being checked
    if (isAuthenticated === null) {
        return <div>Loading...</div>; // Add a spinner or skeleton screen for better UX
    }

    // Redirect to login if not authenticated and not on an auth page
    useEffect(() => {
        if (isAuthenticated === false && !isAuthPage) {
            router.push("/login");
        } else {
        }
    }, [isAuthenticated, isAuthPage, router]);


    return isAuthPage ? (
        <div className={`${poppins.className} auth-layout`}>
            <Component {...pageProps} />
        </div>
    ) : (
        <div
            className={`${poppins.className} main-layout`}
        >
            <div className="side-bar-placeholder">
                <SideBar />
            </div>
            <div className="page-placeholder">
                <Component {...pageProps} />
            </div>
        </div>

    );
}
export default AppContent;
