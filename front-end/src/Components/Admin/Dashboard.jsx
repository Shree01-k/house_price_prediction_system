import { useState, useEffect } from "react";
import { FaEdit, FaTrash, FaPlus } from "react-icons/fa";

const Dashboard = () => {
  const [properties, setProperties] = useState([]);
  const [editingProperty, setEditingProperty] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isAdding, setIsAdding] = useState(false);

  // Fetch properties from the backend
  useEffect(() => {
    const fetchProperties = async () => {
      try {
        const response = await fetch("http://localhost:8080/properties"); // Replace with your API endpoint
        if (!response.ok) {
          throw new Error("Failed to fetch properties");
        }
        const data = await response.json();
        setProperties(data);
      } catch (error) {
        console.error("Error fetching properties:", error);
      }
    };

    fetchProperties();
  }, []);

  const handleDelete = (id) => {
    setProperties(properties.filter((property) => property.id !== id));
  };

  const handleEditClick = (property) => {
    setEditingProperty(property);
    setIsAdding(false);
    setIsModalOpen(true);
  };

  const handleAddClick = () => {
    setEditingProperty({
      id: properties.length + 1,
      state: "",
      district: "",
      city: "",
      area: "",
      name: "",
      location: "",
      price: "",
    });
    setIsAdding(true);
    setIsModalOpen(true);
  };

  const handleChange = (e) => {
    setEditingProperty({ ...editingProperty, [e.target.name]: e.target.value });
  };

  const handleSave = () => {
    if (isAdding) {
      setProperties([...properties, { ...editingProperty, id: properties.length + 1 }]);
    } else {
      setProperties(
        properties.map((prop) =>
          prop.id === editingProperty.id ? editingProperty : prop
        )
      );
    }
    setIsModalOpen(false);
  };

  return (
    <div className="min-h-screen bg-gray-900 text-white p-6">
      <div className="flex justify-between items-center max-w-6xl mx-auto mb-6">
        <h1 className="text-3xl font-bold">Admin Dashboard</h1>
        <button
          onClick={handleAddClick}
          className="flex items-center gap-2 bg-[#CA2F23] px-4 py-2 rounded cursor-pointer"
        >
          <FaPlus /> Add Property
        </button>
      </div>

      <div className="max-w-6xl mx-auto bg-gray-800 p-6 rounded-lg shadow-lg overflow-x-auto">
        <table className="w-full border-collapse">
          <thead>
            <tr className="bg-gray-700">
              <th className="p-3 text-left">State</th>
              <th className="p-3 text-left">District</th>
              <th className="p-3 text-left">City</th>
              <th className="p-3 text-left">Area</th>
              <th className="p-3 text-left">Property Name</th>
              <th className="p-3 text-left">Location</th>
              <th className="p-3 text-left">Price</th>
              <th className="p-3 text-center">Actions</th>
            </tr>
          </thead>
          <tbody>
            {properties.map((property) => (
              <tr key={property.id} className="border-b border-gray-600">
                <td className="p-3">{property.state}</td>
                <td className="p-3">{property.district}</td>
                <td className="p-3">{property.city}</td>
                <td className="p-3">{property.area}</td>
                <td className="p-3">{property.name}</td>
                <td className="p-3">{property.location}</td>
                <td className="p-3">{property.price}</td>
                <td className="p-3 flex justify-center gap-4">
                  <button
                    onClick={() => handleEditClick(property)}
                    className="text-yellow-400 hover:text-yellow-500 cursor-pointer"
                  >
                    <FaEdit size={20} />
                  </button>
                  <button
                    onClick={() => handleDelete(property.id)}
                    className="text-red-500 hover:text-red-600 cursor-pointer"
                  >
                    <FaTrash size={20} />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
          <div className="bg-gray-800 p-6 rounded-lg shadow-lg w-96">
            <h2 className="text-xl font-bold mb-4">
              {isAdding ? "Add Property" : "Edit Property"}
            </h2>
            <input
              type="text"
              name="state"
              value={editingProperty.state}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="State"
            />
            <input
              type="text"
              name="district"
              value={editingProperty.district}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="District"
            />
            <input
              type="text"
              name="city"
              value={editingProperty.city}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="City"
            />
            <input
              type="text"
              name="area"
              value={editingProperty.area}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="Area"
            />
            <input
              type="text"
              name="name"
              value={editingProperty.name}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="Property Name"
            />
            <input
              type="text"
              name="location"
              value={editingProperty.location}
              onChange={handleChange}
              className="w-full p-2 mb-2 border rounded bg-gray-700 text-white"
              placeholder="Location"
            />
            <input
              type="text"
              name="price"
              value={editingProperty.price}
              onChange={handleChange}
              className="w-full p-2 mb-4 border rounded bg-gray-700 text-white"
              placeholder="Price"
            />
            <div className="flex justify-end gap-4">
              <button
                onClick={() => setIsModalOpen(false)}
                className="px-4 py-2 bg-gray-500 text-white rounded hover:bg-gray-600 cursor-pointer"
              >
                Cancel
              </button>
              <button
                onClick={handleSave}
                className="px-4 py-2 bg-[#CA2F23] text-white rounded cursor-pointer"
              >
                {isAdding ? "Add" : "Update"}
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default Dashboard;
