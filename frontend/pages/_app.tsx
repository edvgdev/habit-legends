import SideBar from "@/components/side-bar";
import "@/styles/globals.css";
import { poppins } from "@/utils/fonts";
import type { AppProps } from "next/app";

export default function App({ Component, pageProps }: AppProps) {
  return (
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
