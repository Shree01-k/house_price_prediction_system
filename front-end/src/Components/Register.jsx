import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function Register() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    gender: "male",
    role: "user",
    username: "",
    password: "",
    confirmPassword: "",
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Validate passwords match
    if (formData.password !== formData.confirmPassword) {
      alert("Passwords do not match!");
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/registration", {
        firstName: formData.firstName,
        lastName: formData.lastName,
        email: formData.email,
        gender: formData.gender,
        role: formData.role,
        username: formData.username,
        password: formData.password,
      });

      if (response.status === 200) {
        alert("Registration successful!");
        navigate("/");
      }
    } catch (error) {
      alert(error.response?.data?.message || "Registration failed!");
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100 px-4">
      <div className="w-full max-w-2xl p-8 bg-white shadow-lg rounded-2xl">
        <h2 className="text-2xl font-bold text-center text-gray-800">Register</h2>

        <form className="mt-6" onSubmit={handleSubmit}>
          <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
              <label className="block text-gray-700">First Name</label>
              <input
                type="text"
                name="firstName"
                placeholder="Enter first name"
                value={formData.firstName}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
            <div>
              <label className="block text-gray-700">Last Name</label>
              <input
                type="text"
                name="lastName"
                placeholder="Enter last name"
                value={formData.lastName}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
            <div>
              <label className="block text-gray-700">Email</label>
              <input
                type="email"
                name="email"
                placeholder="Enter email"
                value={formData.email}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
            <div>
              <label className="block text-gray-700">Gender</label>
              <select
                name="gender"
                value={formData.gender}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2 bg-transparent"
              >
                <option value="male">Male</option>
                <option value="female">Female</option>
                <option value="other">Other</option>
              </select>
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
            <div>
              <label className="block text-gray-700">Role</label>
              <select
                name="role"
                value={formData.role}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2 bg-transparent"
              >
                <option value="user">USER</option>
                <option value="admin">ADMIN</option>
              </select>
            </div>
            <div>
              <label className="block text-gray-700">Username</label>
              <input
                type="text"
                name="username"
                placeholder="Enter Username"
                value={formData.username}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
          </div>

          <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
            <div>
              <label className="block text-gray-700">Password</label>
              <input
                type="password"
                name="password"
                placeholder="Enter password"
                value={formData.password}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
            <div>
              <label className="block text-gray-700">Confirm Password</label>
              <input
                type="password"
                name="confirmPassword"
                placeholder="Confirm password"
                value={formData.confirmPassword}
                onChange={handleChange}
                className="w-full border-b border-gray-400 focus:outline-none focus:border-black py-2"
              />
            </div>
          </div>

          <button
            type="submit"
            className="w-full mt-6 bg-[#CA2F23] text-white py-2 rounded-lg transition cursor-pointer"
          >
            Register
          </button>
          <p className="mt-4 text-center text-gray-600">
            Already have an account?
            <Link to="/" className="text-blue-500 hover:underline">
              {" "}
              Login
            </Link>
          </p>
        </form>
      </div>
    </div>
  );
}

export default Register;
