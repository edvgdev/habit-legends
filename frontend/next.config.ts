import type { NextConfig } from "next";

const nextConfig: NextConfig = {
  /* config options here */
  reactStrictMode: true,
  async rewrites() {
    return [
      {
        source: '/api/:path*',  // Match requests to /api/*
        destination: 'http://localhost:8080/api/:path*',  // Forward requests to your Spring Boot backend
      },
    ];
  },
};

export default nextConfig;
