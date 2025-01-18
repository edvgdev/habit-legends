import { checkEmailAvailability, register } from '@/api/auth';
import RegistrationSuccessModal from '@/components/registration-sucess-modal';
import { RegistrationRequest } from '@/types/authentication';
import { useRouter } from 'next/router'
import React, { useState } from 'react'
import { FaCheck, FaEye, FaEyeSlash, FaSpinner, FaTimes } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';

const Register = () => {

    const router = useRouter();

    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');

    const [isEmailValid, setIsEmailValid] = useState<boolean | null>(null); // State to track if email is valid
    const [isCheckingEmail, setIsCheckingEmail] = useState(false);

    const [isPasswordVisible, setIsPasswordVisible] = useState(false);
    const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] = useState(false);

    const [emailError, setEmailError] = useState(false);
    const [passwordError, setPasswordError] = useState(false);
    const [confirmPasswordError, setConfirmPasswordError] = useState(false);
    const [firstNameError, setFirstNameError] = useState(false);
    const [lastNameError, setLastNameError] = useState(false);

    const [successModalOpen, setSuccessModalOpen] = useState(false);

    const handleSuccessModalClose = () => {
        setSuccessModalOpen(false);
        router.push("/login");
    }


    const handleEmailChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        setEmail(e.target.value);
        setIsEmailValid(null); // Reset status while typing
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();

        const registrationRequest: RegistrationRequest = {
            firstName,
            lastName,
            email,
            password
        }


        const response = await register(registrationRequest);
        if (response.statusCode == 200) {
            setSuccessModalOpen(true);
        } else {
            toast.error(response.message);
        }
    }

    const validateEmail = async () => {
        if (!email) {
            setEmailError(true);
            return;
        }
        setIsCheckingEmail(true);
        setIsEmailValid(null);
        try {
            const emailExists = await checkEmailAvailability(email); // Replace with actual API call
            setIsEmailValid(emailExists);
            setEmailError(!emailExists);
        } catch (error) {
            console.error(error);
        } finally {
            setIsCheckingEmail(false);
        }
    };

    const togglePasswordVisibility = () => {
        setIsPasswordVisible(!isPasswordVisible);
    };

    const toggleConfirmPasswordVisibility = () => {
        setIsConfirmPasswordVisible(!isConfirmPasswordVisible);
    };

    const validateFirstName = () => {
        setFirstNameError(firstName.trim() === '');
    };

    const validateLastName = () => {
        setLastNameError(lastName.trim() === '');
    };

    const validatePassword = () => {
        const passwordRegex = /^(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
        setPasswordError(password.trim() === '' || !passwordRegex.test(password));
    };

    const validateConfirmPassword = () => {
        setConfirmPasswordError(confirmPassword.trim() === '' || confirmPassword !== password);
    };

    // Disable submit button if there are validation errors
    const isFormValid = !emailError && !passwordError && !confirmPasswordError
        && !firstNameError && !lastNameError && email && password
        && confirmPassword && firstName && lastName;

    return (
        <div className='auth-content'>
            <h2>Welcome to Questlyf! </h2>
            <form>
                <div className='auth-content-form'>
                    <label className={firstNameError ? 'error' : ''}>First Name:</label>
                    <input
                        type="text"
                        placeholder='Enter first name'
                        required
                        value={firstName}
                        onChange={(e) => setFirstName(e.target.value)}
                        className={firstNameError ? 'error' : ''}
                        onBlur={validateFirstName}
                    />
                </div>
                <div className='auth-content-form'>
                    <label className={lastNameError ? 'error' : ''}>Last name:</label>
                    <input
                        type="text"
                        placeholder='Enter last name'
                        required
                        value={lastName}
                        onChange={(e) => setLastName(e.target.value)}
                        className={lastNameError ? 'error' : ''}
                        onBlur={validateLastName}
                    />
                </div>
                <div className='auth-content-form'>
                    <label className={emailError ? 'error' : ''}>Email:</label>
                    <div style={{ position: 'relative', marginBottom: '20px' }}>
                        <input
                            type="email"
                            placeholder='Enter email address'
                            onBlur={validateEmail}
                            required
                            value={email}
                            onChange={handleEmailChange}
                            className={emailError ? 'error' : ''}
                        />
                        <div
                            style={{
                                position: 'absolute',
                                right: '20px',
                                top: '60%',
                                transform: 'translateY(-50%)',
                                pointerEvents: 'none', // Prevent interfering with input
                            }}
                        >
                            {email &&
                                (isCheckingEmail ? (
                                    <FaSpinner className="spinner" />
                                ) : isEmailValid === true ? (
                                    <FaCheck style={{ color: 'green' }} />
                                ) : isEmailValid === false ? (
                                    <FaTimes style={{ color: 'red' }} />
                                ) : null)}
                        </div>
                    </div>
                </div>
                <div className='auth-content-form'>
                    <label className={passwordError ? 'error' : ''}>Password:</label>
                    <div style={{ position: "relative" }}>
                        <input
                            type={isPasswordVisible ? "text" : "password"}
                            placeholder='Enter password'
                            required
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            className={passwordError ? 'error' : ''}
                            onBlur={validatePassword}
                        />
                        <div
                            style={{
                                position: "absolute",
                                right: "20px",
                                top: "55%",
                                width: "2rem",
                                transform: "translateY(-50%)",
                                pointerEvents: "auto",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center"
                            }}
                        >
                            {password && (
                                <div
                                    style={{ cursor: "pointer" }}
                                    onClick={togglePasswordVisibility}
                                >
                                    {isPasswordVisible ? (
                                        <FaEyeSlash style={{
                                            color: "#fff",
                                            fontSize: "2rem",
                                        }} />
                                    ) : (
                                        <FaEye style={{
                                            color: "#fff",
                                            fontSize: "2rem",
                                        }} />
                                    )}
                                </div>
                            )}
                        </div>
                    </div>
                    {passwordError && (<p className='error'> Password must be atleast 8 characters,
                        must contain atleast one number, <br />and one special character </p>)}
                </div>
                <div className='auth-content-form'>
                    <label className={confirmPasswordError ? 'error' : ''}>Confirm Password:</label>
                    <div style={{ position: "relative" }}>
                        <input
                            type={isConfirmPasswordVisible ? "text" : "password"}
                            placeholder='Confirm password'
                            required
                            value={confirmPassword}
                            onChange={(e) => setConfirmPassword(e.target.value)}
                            className={confirmPasswordError ? 'error' : ''}
                            onBlur={validateConfirmPassword}
                        />
                        <div
                            style={{
                                position: "absolute",
                                right: "20px",
                                top: "55%",
                                width: "2rem",
                                transform: "translateY(-50%)",
                                pointerEvents: "auto",
                                display: "flex",
                                justifyContent: "center",
                                alignItems: "center"
                            }}
                        >
                            {confirmPassword && (
                                <div
                                    style={{ cursor: "pointer" }}
                                    onClick={toggleConfirmPasswordVisibility}
                                >
                                    {isConfirmPasswordVisible ? (
                                        <FaEyeSlash style={{
                                            color: "#fff",
                                            fontSize: "2rem",
                                        }} />
                                    ) : (
                                        <FaEye style={{
                                            color: "#fff",
                                            fontSize: "2rem",
                                        }} />
                                    )}
                                </div>
                            )}
                        </div>
                    </div>
                    {confirmPasswordError && (<p className='error'> Passwords does not match </p>)}
                </div>

                <button
                    type='submit'
                    className='action-button-primary'
                    style={{ width: "100%", marginBottom: "1rem" }}
                    disabled={!isFormValid}
                    onClick={handleSubmit}
                >
                    Register
                </button>
                <button
                    className='auth-oauth-button'
                    style={{ width: "100%", marginBottom: "4rem" }}
                >
                    <span>
                        <img
                            src="/google.png"
                            alt="Profile"
                            style={{ width: "25px" }}
                        />
                    </span>  Login via Google
                </button>
                <p>Already have an account?
                    <span>
                        <button
                            className='auth-undecorated-button'
                            onClick={() => {
                                router.push("/login");
                            }}
                        >
                            Login
                        </button>
                    </span>
                </p>
            </form>
            <RegistrationSuccessModal
                isOpen={successModalOpen}
                onClose={handleSuccessModalClose}
            />
            <ToastContainer position='top-center' autoClose={3000} />
        </div>

    )
}

export default Register
