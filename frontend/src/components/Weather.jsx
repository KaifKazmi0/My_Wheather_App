import { useState } from "react";
import API from "../services/api";

function Weather() {
    const [city, setCity] = useState("");
    const [weather, setWeather] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const getWeather = async () => {

        if (!city.trim()) {
            setError("Please enter a city name");
            return;
        }

        try {
            setLoading(true);
            setError("");

            const res = await API.get(`/weather/${city}`);

            setWeather(res.data);

        } catch (err) {
            setWeather(null);
            setError("Unable to fetch weather data");
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    return (
        <div>
            <h2>Current Weather</h2>

            <input
                type="text"
                placeholder="Enter city"
                value={city}
                onChange={(e) => setCity(e.target.value)}
            />

            <button onClick={getWeather}>
                Search
            </button>

            {loading && <p>Loading...</p>}
            {error && <p>{error}</p>}

            {weather && (
                <div>
                    <h3>{weather.city}</h3>
                    <p>{weather.region}</p>
                    <p>{weather.country}</p>
                    <p>{weather.condition}</p>
                    <p>{weather.temp} °C</p>
                </div>
            )}
        </div>
    );
}

export default Weather;