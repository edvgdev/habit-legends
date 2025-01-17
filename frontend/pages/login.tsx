import { useAuth } from '@/context/auth-context';
import { LoginRequest } from '@/types/authentication';
import { useRouter } from 'next/router'
import React, { useState } from 'react'

const Login = () => {

    const { login, googleLogin } = useAuth();

    const router = useRouter();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');


    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        const loginCredentials: LoginRequest = {
            email,
            password
        };

        try {
            await login(loginCredentials);
        } catch (error) {
            setError("Invalid email or password");
        }

    }


    const handleGoogleLogin = (e: React.FormEvent) => {
        e.preventDefault();
        googleLogin();
    }


    return (
        <div className='auth-content'>
            <h2>Welcome to Questlyf! </h2>
            <form>
                <div className='auth-content-form'>
                    <label>Email:</label>
                    <input
                        type="text"
                        placeholder='Enter email address'
                        required
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                    />
                </div>
                <div style={{ marginBottom: "5rem" }} className='auth-content-form'>
                    <label>Password:</label>
                    <input
                        type="password"
                        placeholder='Enter password'
                        required
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                {/* Error message display */}
                {error && <p style={{ color: 'red', fontSize: '14px' }}>{error}</p>}

                <button
                    type='submit'
                    className='action-button-primary'
                    style={{ width: "100%", marginBottom: "1rem" }}
                    onClick={handleSubmit}
                >
                    Login via Email
                </button>
                <button
                    className='auth-oauth-button'
                    onClick={handleGoogleLogin}
                    style={{ width: "100%", marginBottom: "4rem" }} >
                    <span>
                        <img
                            src="/google.png" // Replace with your profile image URL
                            alt="Profile"
                            style={{ width: "25px" }}
                        />
                    </span>  Login via Google
                </button>
                <p>Don't have an account?
                    <span>
                        <button
                            className='auth-undecorated-button'
                            onClick={() => {
                                router.push("/register");
                            }}
                        >
                            Register an account
                        </button>
                    </span>
                </p>
            </form>
        </div>

    )
}

export default Login
