import { AuthProvider, useAuth } from "@/context/auth-context";
import "@/styles/globals.css";
import type { AppProps } from "next/app";
import dynamic from "next/dynamic";
import { useRouter } from "next/router";

// Dynamically import the AppContent component to ensure it only runs on the client side
const AppContent = dynamic(() => import("../components/app-content"), { ssr: false });

export default function App({ Component, pageProps }: AppProps) {

  const router = useRouter();

  // Define routes without the main layout
  const authRoutes = ["/login", "/register"];

  // Check if the current route is in the `noLayoutRoutes` array
  const isAuthPage = authRoutes.includes(router.pathname);

  return (
    <AuthProvider>
      <AppContent
        Component={Component}
        pageProps={pageProps}
        isAuthPage={isAuthPage}
      />
    </AuthProvider>
  );
}
