import { useState } from "react";
import API from "../services/api";

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [loading, setLoading] = useState(false);

    const login = async () => {

        if (!username.trim() || !password.trim()) {
            alert("Username and Password are required");
            return;
        }

        try {

            setLoading(true);

            const res = await API.post("/login", {
                username,
                password
            });

            localStorage.setItem("jwt", res.data.jwt);

            alert("Login Successful");

            // Refresh UI after login
            window.location.reload();

        } catch (error) {

            console.error(error);
            alert("Invalid Credentials");

        } finally {

            setLoading(false);
        }
    };

    return (
        <div>
            <h2>Login</h2>

            <input
                type="text"
                placeholder="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
            />

            <button
                onClick={login}
                disabled={loading}
            >
                {loading ? "Logging In..." : "Login"}
            </button>
        </div>
    );
}

export default Login;