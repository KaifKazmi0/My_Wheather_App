import { useState } from "react";
import API from "../services/api";

function Signup() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const signup = async () => {

        try {

            await API.post("/signup", {
                username,
                password
            });

            alert("User Created");

        }catch (error) {
    console.log(error);

    alert(
        error.response?.data?.message ||
        error.response?.data ||
        "Signup Failed"
    );
}
    };

    return (
        <div>
            <h2>Signup</h2>

            <input
                placeholder="Username"
                onChange={(e) => setUsername(e.target.value)}
            />

            <input
                type="password"
                placeholder="Password"
                onChange={(e) => setPassword(e.target.value)}
            />

            <button onClick={signup}>
                Signup
            </button>
        </div>
    );
}

export default Signup;
