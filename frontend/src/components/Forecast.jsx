import { useState } from "react";
import API from "../services/api";

function Forecast() {

    const [city, setCity] = useState("");
    const [days, setDays] = useState(3);
    const [forecast, setForecast] = useState(null);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    const getForecast = async () => {

        if (!city.trim()) {
            setError("Please enter a city name");
            return;
        }

        try {

            setLoading(true);
            setError("");

            const res = await API.get(
                `/weather/forecast?city=${city}&day=${days}`
            );

            setForecast(res.data);

        } catch (err) {

            setForecast(null);
            setError("Unable to fetch forecast");
            console.error(err);

        } finally {
            setLoading(false);
        }
    };

    return (
        <div>

            <h2>Forecast</h2>

            <input
                type="text"
                placeholder="Enter city"
                value={city}
                onChange={(e) => setCity(e.target.value)}
            />

            <input
                type="number"
                min="1"
                max="14"
                value={days}
                onChange={(e) => setDays(Number(e.target.value))}
            />

            <button onClick={getForecast}>
                Get Forecast
            </button>

            {loading && <p>Loading...</p>}
            {error && <p>{error}</p>}

            {forecast && (
                <>
                    <h3>
                        {forecast.weatherResponse.city},
                        {" "}
                        {forecast.weatherResponse.country}
                    </h3>

                    <p>
                        Current: {forecast.weatherResponse.temp} °C
                    </p>

                    {forecast.dayTemps.map((day, index) => (
                        <div
                            key={index}
                            style={{
                                border: "1px solid #ccc",
                                padding: "10px",
                                margin: "10px 0"
                            }}
                        >
                            <p><strong>{day.date}</strong></p>
                            <p>Min: {day.minTemp} °C</p>
                            <p>Avg: {day.avgTemp} °C</p>
                            <p>Max: {day.maxTemp} °C</p>
                        </div>
                    ))}
                </>
            )}

        </div>
    );
}

export default Forecast;