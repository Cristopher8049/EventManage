import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function EventForm({ initialData = {}, onSubmit }) {
    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [startDateTime, setStartDateTime] = useState("");
    const [endDateTime, setEndDateTime] = useState("");
    const [location, setLocation] = useState("");
    const [capacity, setCapacity] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        if (Object.keys(initialData).length > 0) {
            setTitle(initialData.title || "");
            setDescription(initialData.description || "");
            setStartDateTime(initialData.startDateTime?.slice(0, 16) || "");
            setEndDateTime(initialData.endDateTime?.slice(0, 16) || "");
            setLocation(initialData.location || "");
            setCapacity(initialData.maxCapacity || "");
        }
    }, [initialData]);

    const handleSubmit = (e) => {
        e.preventDefault();

        if (!title || !startDateTime || !endDateTime || !location || !capacity) {
            alert("Por favor, completa todos los campos obligatorios.");
            return;
        }

        onSubmit({
            title,
            description,
            startDateTime,
            endDateTime,
            location,
            capacity: parseInt(capacity),
        });
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="bg-white rounded-lg shadow-lg p-8 w-full max-w-lg"
        >
            <div className="mb-4">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="title">Título:</label>
                <input
                    id="title"
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    required
                />
            </div>

            <div className="mb-4">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="description">Descripción:</label>
                <textarea
                    id="description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    rows="3"
                />
            </div>

            <div className="mb-4">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="startDateTime">Fecha y hora de inicio:</label>
                <input
                    id="startDateTime"
                    type="datetime-local"
                    value={startDateTime}
                    onChange={(e) => setStartDateTime(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    required
                />
            </div>

            <div className="mb-4">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="endDateTime">Fecha y hora de fin:</label>
                <input
                    id="endDateTime"
                    type="datetime-local"
                    value={endDateTime}
                    onChange={(e) => setEndDateTime(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    required
                />
            </div>

            <div className="mb-4">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="location">Ubicación:</label>
                <input
                    id="location"
                    type="text"
                    value={location}
                    onChange={(e) => setLocation(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    required
                />
            </div>

            <div className="mb-6">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="capacity">Capacidad máxima:</label>
                <input
                    id="capacity"
                    type="number"
                    value={capacity}
                    onChange={(e) => setCapacity(e.target.value)}
                    className="w-full px-4 py-2 border rounded-md"
                    required
                />
            </div>

            <div className="flex justify-between">
                <button
                    type="button"
                    onClick={() => navigate("/dashboard")}
                    className="px-6 py-2 bg-gray-300 rounded-md font-semibold hover:bg-gray-400 transition"
                >
                    Cancelar
                </button>
                <button
                    type="submit"
                    className="px-6 py-2 bg-gray-600 text-white rounded-md font-semibold hover:bg-gray-700 transition"
                >
                    Guardar
                </button>
            </div>
        </form>
    );
}
