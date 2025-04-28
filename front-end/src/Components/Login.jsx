import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { FaEye, FaEyeSlash } from "react-icons/fa"; // Import eye icons
import Financeleaders from "/public/Images/Login.png";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async () => {
    setError(""); // Clear previous errors

    try {
      const response = await axios.post(
        "http://localhost:8080/api/login",
        {
          username: username, // Send username instead of email
          password: password,
        },
        { withCredentials: true } // Required for JWT/Auth cookies
      );

      if (response.status === 200 && username != 'ADMIN') {
        toast.success("Login successful!", { position: "top-right", autoClose: 2000 });
        navigate("/select-property");
      }
      if (response.status === 200 && username == 'ADMIN') {
        toast.success("Login successful!", { position: "top-right", autoClose: 2000 });
        navigate("/dashboard");
      }
    // eslint-disable-next-line no-unused-vars
    } catch (err) {
      setError("Invalid username or password");
      toast.error("Login failed! Please check your credentials.", { position: "top-right", autoClose: 2000 });
    }
  };

  return (
    <>
      <div
        className="relative w-screen h-screen flex justify-center items-center bg-cover bg-center"
        style={{ backgroundImage: `url(${Financeleaders})` }}
      >
        <div className="bg-white p-6 md:p-8 rounded-xl shadow-lg w-[95%] md:w-[400px] ml-auto mr-auto md:mr-28">
          <h2 className="text-xl md:text-xl font-bold">Welcome back!</h2>
          <p className="text-sm text-gray-500">Please log in to continue.</p>

          {error && <p className="text-red-500 text-sm mt-2">{error}</p>}

          {/* Username Input */}
          <div className="mt-2">
            <label className="text-sm font-medium">Username</label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="w-full border rounded-lg p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-red-500"
              placeholder="Enter your username"
            />
          </div>

          {/* Password Input */}
          <div className="mt-2">
            <label className="text-sm font-medium">Password</label>
            <div className="relative">
              <input
                type={showPassword ? "text" : "password"}
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                className="w-full border rounded-lg p-2 mt-1 focus:outline-none focus:ring-2 focus:ring-red-500"
                placeholder="********"
              />
              <button
                type="button"
                className="absolute right-3 top-3 text-gray-500"
                onClick={() => setShowPassword(!showPassword)}
              >
                {showPassword ? <FaEyeSlash /> : <FaEye />}
              </button>
            </div>
          </div>

          {/* Remember Me Checkbox */}
          <div className="flex justify-between items-center mt-2">
            <label className="flex items-center">
              <input type="checkbox" className="mr-2" /> Remember me
            </label>
          </div>

          {/* Login Button */}
          <button
            onClick={handleLogin}
            className="w-full text-white py-2 rounded-lg mt-4 cursor-pointer bg-[#CA2F23]"
          >
            Login
          </button>

          {/* Register Link */}
          <p className="text-center text-sm mt-2">
            Don't have an account?{" "}
            <a href="/register" className="text-[#073c96]">
              Create Account
            </a>
          </p>
        </div>
      </div>
    </>
  );
};

export default Login;
