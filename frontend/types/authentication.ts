export interface TokenResponse {
    statusCode: number;
    token: string;
    refreshToken: string;
    expirationTime: string;
    message: string;
};

export interface LoginRequest {
    email: string;
    password: string;
}

export interface RegistrationRequest {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
}

export interface AuthResponse {
    statusCode: number;
    message: string;
}
