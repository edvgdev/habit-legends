import SideBar from "@/components/side-bar";
import "@/styles/globals.css";
import { poppins } from "@/utils/fonts";
import type { AppProps } from "next/app";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";

export default function App({ Component, pageProps }: AppProps) {

  const router = useRouter();

  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    // If there's no token and the route is not login or register, redirect to login
    if (!token && router.pathname !== "/login" && router.pathname !== "/register") {
      router.push("/login");
    } else {
      setIsAuthenticated(true);
    }
  }, [router]);

  // Render a loading state while authentication is being checked
  if (isAuthenticated === null) {
    return <div>Loading...</div>; // Add a spinner or skeleton screen for better UX
  }


  // Define routes without the main layout
  const authRoutes = ["/login", "/register"];

  // Check if the current route is in the `noLayoutRoutes` array
  const isAuthPage = authRoutes.includes(router.pathname);


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
