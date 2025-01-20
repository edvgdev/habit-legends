import { checkEmailAvailability, register } from '@/api/auth';
import RegistrationSuccessModal from '@/components/registration-sucess-modal';
import { RegistrationRequest } from '@/types/authentication';
import { useRouter } from 'next/router'
import React, { useState } from 'react'
import { FaCheck, FaEye, FaEyeSlash, FaSpinner, FaTimes } from 'react-icons/fa';
import { toast, ToastContainer } from 'react-toastify';
import * as yup from 'yup';
import { useForm, Controller } from 'react-hook-form';
import { yupResolver } from '@hookform/resolvers/yup';
import { Button, IconButton, InputAdornment, TextField, Typography } from '@mui/material';
import { Visibility, VisibilityOff } from '@mui/icons-material';
import { debounce } from 'lodash';

const schema = yup.object().shape({
    firstName: yup.string().required('First name is required'),
    lastName: yup.string().required('Last name is required'),
    email: yup.string().email('Invalid email').required('Email is required'),
    password: yup.string()
        .required('Password is required')
        .matches(
            /^(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/,
            'Password must be at least 8 characters, contain at least one number, and one special character'
        ),
    confirmPassword: yup.string()
        .oneOf([yup.ref('password'), undefined], 'Passwords must match')
        .required('Confirm password is required'),
});

const iconButtonStyles = {
    color: 'white', // White color for the icon
    '&:hover': {
        backgroundColor: '#0BBD8B', // Light background on hover
    },
};

const inputStyle = {
    mb: 2,
    '& .MuiOutlinedInput-root': {
        '& fieldset': {
            borderColor: 'white', // White outline for normal state
        },
        '&:hover fieldset': {
            borderColor: 'white', // White outline on hover
        },
        '&.Mui-focused fieldset': {
            borderColor: '#0BBD8B', // Green outline for focused state
        },
        '&.Mui-error fieldset': {
            borderColor: 'red', // Red outline for error state
        },
    },
    '& .MuiInputLabel-root': {
        color: 'white', // White label text
    },
    '& .MuiInputLabel-root.Mui-focused': {
        color: '#0BBD8B', // Green label text when focused
    },
    '& .MuiInputLabel-root.Mui-error': {
        color: 'red', // Red label text when in error state
    },
    '& .MuiOutlinedInput-input': {
        color: 'white', // White text color
    },
    '& .MuiFormHelperText-root': {
        color: 'red', // Red error message text
    },
}

const Register = () => {

    const router = useRouter();

    const [isEmailValid, setIsEmailValid] = useState<boolean | null>(null); // State to track if email is valid
    const [isCheckingEmail, setIsCheckingEmail] = useState(false);

    const [isPasswordVisible, setIsPasswordVisible] = useState(false);
    const [isConfirmPasswordVisible, setIsConfirmPasswordVisible] = useState(false);

    const [successModalOpen, setSuccessModalOpen] = useState(false);

    const { control, handleSubmit, formState: { errors }, setError, clearErrors, trigger } = useForm({
        resolver: yupResolver(schema),
    });

    const handleSuccessModalClose = () => {
        setSuccessModalOpen(false);
        router.push("/login");
    }


    const onSubmit = async (data: RegistrationRequest) => {

        // Trigger Yup validation for all fields
        const isValid = await trigger();

        // If there are Yup validation errors, stop the submission process
        if (!isValid) {
            toast.error('Please fix the form errors');
            return;
        }

        // Validate the email field and check if it's valid
        const isEmailValid = await validateEmail(data.email);

        // If the email is invalid, stop the submission process
        if (!isEmailValid) {
            toast.error('Please fix the email field errors');
            return;
        }
        const response = await register(data);
        if (response.statusCode === 200) {
            setSuccessModalOpen(true);
        } else {
            toast.error(response.message);
        }
    };

    const validateEmail = async (email: string) => {
        if (!email) {
            setError('email', { type: 'manual', message: 'Email is required' });
            return false; // Email is invalid
        }
        setIsCheckingEmail(true);
        setIsEmailValid(null);
        try {
            const emailExists = await checkEmailAvailability(email);
            setIsEmailValid(emailExists);
            if (!emailExists) {
                setError('email', { type: 'manual', message: 'Email is already taken' });
                return false; // Email is invalid
            } else {
                clearErrors('email'); // Clear the error if the email is available
                return true; // Email is valid
            }
        } catch (error) {
            console.error(error);
            setError('email', { type: 'manual', message: 'An error occurred while validating the email' });
            return false; // Email is invalid
        } finally {
            setIsCheckingEmail(false);
        }
    };

    const debouncedValidateEmail = debounce(validateEmail, 2000);

    return (
        <div className='auth-content'>
            <div className='auth-title'>
                <img src='qlogo.png' />
                <h2>Welcome to Questlyf! </h2>
                <p>Level up your life with daily quests</p>
            </div>
            <form onSubmit={handleSubmit(onSubmit)}>
                <Controller
                    name='firstName'
                    control={control}
                    defaultValue=''
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label='First Name'
                            fullWidth
                            margin='normal'
                            error={!!errors.firstName}
                            helperText={errors.firstName?.message}
                            sx={inputStyle}
                        />
                    )}
                />
                <Controller
                    name='lastName'
                    control={control}
                    defaultValue=''
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label='Last Name'
                            fullWidth
                            margin='normal'
                            error={!!errors.lastName}
                            helperText={errors.lastName?.message}
                            sx={inputStyle}
                        />
                    )}
                />
                <Controller
                    name='email'
                    control={control}
                    defaultValue=''
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label='Email'
                            fullWidth
                            margin='normal'
                            error={!!errors.email}
                            helperText={errors.email?.message}
                            onChange={(e) => {
                                field.onChange(e); // Update the field value
                                debouncedValidateEmail(e.target.value); // Trigger debounced validation
                            }}
                            slotProps={{
                                input: {
                                    endAdornment: (
                                        <IconButton>
                                            {isCheckingEmail ? (
                                                <FaSpinner className='spinner' />
                                            ) : isEmailValid === true ? (
                                                <FaCheck style={{ color: 'green' }} />
                                            ) : isEmailValid === false ? (
                                                <FaTimes style={{ color: 'red' }} />
                                            ) : null}
                                        </IconButton>
                                    ),
                                },
                            }}
                            sx={inputStyle}
                        />
                    )}
                />
                <Controller
                    name='password'
                    control={control}
                    defaultValue=''
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label='Password'
                            type={isPasswordVisible ? 'text' : 'password'}
                            fullWidth
                            margin='normal'
                            error={!!errors.password}
                            helperText={errors.password?.message}
                            slotProps={{
                                input: {
                                    endAdornment: (
                                        <IconButton
                                            onClick={() => setIsPasswordVisible(!isPasswordVisible)}
                                            sx={iconButtonStyles}>
                                            {isPasswordVisible ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    ),
                                },
                            }}
                            sx={inputStyle}
                        />
                    )}
                />
                <Controller
                    name='confirmPassword'
                    control={control}
                    defaultValue=''
                    render={({ field }) => (
                        <TextField
                            {...field}
                            label='Confirm Password'
                            type={isConfirmPasswordVisible ? 'text' : 'password'}
                            fullWidth
                            margin='normal'
                            error={!!errors.confirmPassword}
                            helperText={errors.confirmPassword?.message}
                            slotProps={{
                                input: {
                                    endAdornment: (
                                        <IconButton
                                            onClick={() => setIsConfirmPasswordVisible(!isConfirmPasswordVisible)}
                                            sx={iconButtonStyles}>
                                            {isPasswordVisible ? <VisibilityOff /> : <Visibility />}
                                        </IconButton>
                                    ),
                                },
                            }}
                            sx={inputStyle}
                        />
                    )}
                />
                <Button
                    type='submit'
                    variant='contained'
                    fullWidth
                    sx={{ mb: 1.5, backgroundColor: "#0BBD8B" }}
                >
                    Register
                </Button>
                <Button
                    variant='outlined'
                    fullWidth
                    sx={{ mb: 4, borderColor: "#0BBD8B", color: "#0BBD8B" }}
                    startIcon={<img src='/google.png' alt='Google' style={{ width: '25px' }} />}
                >
                    Login via Google
                </Button>
                <Typography variant='body2' sx={{ color: '#fff' }}>
                    Already have an account?{' '}
                    <Button
                        onClick={() => router.push('/login')}
                        sx={{ textDecoration: 'underline', color: 'white' }}
                    >
                        Login
                    </Button>
                </Typography>
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
