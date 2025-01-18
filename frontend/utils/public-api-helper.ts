import axios, { AxiosInstance } from 'axios';

// Public Axios instance (no credentials)
const publicApi: AxiosInstance = axios.create({
    baseURL: '/api/',
    withCredentials: false, // Do not send cookies
});

export default publicApi;