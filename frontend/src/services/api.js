import axios from "axios";

const API = axios.create({
     baseURL: "/api",
});

API.interceptors.request.use((config) => {
    // Do not attach tokens for auth routes
    if (config.url === '/login' || config.url === '/signup') {
        return config;
    }

    const token = localStorage.getItem("jwt");
    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
});
export default API;
