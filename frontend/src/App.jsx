import Login from "./components/Login";
import Signup from "./components/Signup";
import Weather from "./components/Weather";
import Forecast from "./components/Forecast";

function App() {

  return (
    <div>

      <h1>Weather App</h1>

      <Signup />

      <hr />

      <Login />

      <hr />

      <Weather />

      <hr />

      <Forecast />

    </div>
  );
}

export default App;