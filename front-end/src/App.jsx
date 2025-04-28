//import { useState } from "react";
//import reactLogo from "./assets/react.svg";
//import viteLogo from "/vite.svg";
import "./App.css";
import Login from "./Components/Login";
import Register from "./Components/Register";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import SelectProperty from "./Components/SelectProperty";
import Dashboard from "./Components/Admin/Dashboard";

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route path="/" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="/select-property" element={<SelectProperty />} />
          <Route path="/dashboard" element={<Dashboard />} />
        </Routes>
      </Router>
    </>
  );
}

export default App;
