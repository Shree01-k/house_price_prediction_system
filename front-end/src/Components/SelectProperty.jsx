import React, { useState } from "react";
import dashboardImg from "/public/Images/user-bg.jpg";

function SelectProperty() {
  const data = {
    state: ["Maharashtra", "Rajasthan", "Gujarat"],
    district: ["Pune", "Jaipur", "Ahmedabad"],
    city: ["Mumbai", "Udaipur", "Surat"],
    area: ["Andheri", "Malviya Nagar", "Maninagar"],
    property: ["Flat", "Villa", "Shop"],
  };

  const dummyData = {
    "Andheri-Flat": "2 BHK Flat, 1000 sqft, ₹1.5 Cr",
    "Andheri-Villa": "3 BHK Villa, 2000 sqft, ₹3 Cr",
    "Andheri-Shop": "Commercial Shop, 500 sqft, ₹75 Lakh",
    "Malviya Nagar-Flat": "2 BHK Flat, 1200 sqft, ₹1.2 Cr",
    "Malviya Nagar-Villa": "4 BHK Villa, 2500 sqft, ₹3.5 Cr",
    "Malviya Nagar-Shop": "Retail Shop, 400 sqft, ₹65 Lakh",
    "Maninagar-Flat": "3 BHK Flat, 1400 sqft, ₹1.8 Cr",
    "Maninagar-Villa": "5 BHK Villa, 3000 sqft, ₹4 Cr",
    "Maninagar-Shop": "Office Space, 600 sqft, ₹85 Lakh",
  };

  const [selectedValues, setSelectedValues] = useState({
    state: "",
    district: "",
    city: "",
    area: "",
    property: "",
  });

  const [result, setResult] = useState("");

  const handleChange = (e, key) => {
    setSelectedValues({ ...selectedValues, [key]: e.target.value });
  };

  const handleSearch = () => {
    const key = `${selectedValues.area}-${selectedValues.property}`;
    setResult(dummyData[key] || "No data available");
  };

  return (
    <>
      <div
        className="relative w-screen h-screen flex flex-col justify-center items-center bg-cover bg-center before:absolute before:inset-0 before:bg-black before:opacity-80"
        style={{ backgroundImage: `url(${dashboardImg})` }}
      >
        <div className="relative z-10 flex flex-col gap-4 bg-transparent p-4 rounded-lg shadow-lg max-w-5xl w-full">
          <div className="flex gap-4">
            {Object.keys(data).map((key) => (
              <select
                key={key}
                value={selectedValues[key]}
                onChange={(e) => handleChange(e, key)}
                className="p-2 border border-white rounded-md text-white bg-transparent w-48 cursor-pointer "
              >
                <option value="" className="text-black">
                  {key.charAt(0).toUpperCase() + key.slice(1)}
                </option>
                {data[key].map((item, index) => (
                  <option key={index} value={item} className="text-black">
                    {item}
                  </option>
                ))}
              </select>
            ))}
          </div>
          <button
            onClick={handleSearch}
            className="p-2 bg-[#CA2F23] text-white rounded-md cursor-pointer w-32"
          >
            Search
          </button>
          {result && (
            <div className="mt-8 p-4 bg-gray-100 border rounded-lg w-full text-center text-black">
              <p className="font-semibold">Result:</p>
              <p>{result}</p>
            </div>
          )}
        </div>
      </div>
    </>
  );
}

export default SelectProperty;
