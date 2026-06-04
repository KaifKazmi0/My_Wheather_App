import axios from "axios";

const API = axios.create({
    baseURL: "/api",
});

// Attach JWT to protected requests
API.interceptors.request.use((config) => {
    if (
        config.url?.startsWith("/login") ||
        config.url?.startsWith("/signup")
    ) {
        return config;
    }

    const token = localStorage.getItem("jwt");

    if (token) {
        config.headers.Authorization = `Bearer ${token}`;
    }

    return config;
});

// Handle expired/invalid tokens globally
API.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response?.status === 401) {
            localStorage.removeItem("jwt");
            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);

export default API;
