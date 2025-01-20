import { poppins } from "@/utils/fonts";
import SideBar from "./side-bar";
import { useState } from "react";

const AppContent = ({ Component, pageProps, isAuthPage }: { Component: React.ElementType; pageProps: Record<string, unknown>; isAuthPage: boolean }) => {

    const [isSidebarExpanded, setIsSidebarExpanded] = useState(true); // Manage state here

    const toggleSidebar = () => {
        setIsSidebarExpanded(!isSidebarExpanded);
    };

    return isAuthPage ? (
        <div className={`${poppins.className} auth-layout`}>
            <Component {...pageProps} />
        </div>
    ) : (
        <div
            className={`${poppins.className} main-layout`}
        >
            <div className={`side-bar-placeholder ${isSidebarExpanded ? '' : 'minimized'}`}>
                <SideBar
                    isSidebarExpanded={isSidebarExpanded}
                    toggleSidebar={toggleSidebar}
                />
            </div>
            <div className="page-placeholder">
                <Component {...pageProps} />
            </div>
        </div>

    );
}
export default AppContent;
