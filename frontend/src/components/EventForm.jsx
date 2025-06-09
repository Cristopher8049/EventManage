import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function EventForm({ initialData = {}, onSubmit }) {
    const [title, setTitle] = useState("");
    const [date, setDate] = useState("");
    const [description, setDescription] = useState("");
    const navigate = useNavigate();

    useEffect(() => {
        setTitle(initialData.title || "");
        setDate(initialData.date || "");
        setDescription(initialData.description || "");
    }, [initialData]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!title || !date) {
            alert("Título y fecha son obligatorios");
            return;
        }

        onSubmit({ title, date, description });
    };

    return (
        <form
            onSubmit={handleSubmit}
            className="bg-white rounded-lg shadow-lg p-8 w-full max-w-lg"
        >
            <div className="mb-6">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="title">
                    Título:
                </label>
                <input
                    id="title"
                    type="text"
                    value={title}
                    onChange={(e) => setTitle(e.target.value)}
                    required
                    className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-gray-500"
                />
            </div>
            <div className="mb-6">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="date">
                    Fecha:
                </label>
                <input
                    id="date"
                    type="date"
                    value={date}
                    onChange={(e) => setDate(e.target.value)}
                    required
                    className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-gray-500"
                />
            </div>
            <div className="mb-6">
                <label className="block mb-2 font-semibold text-gray-700" htmlFor="description">
                    Descripción:
                </label>
                <textarea
                    id="description"
                    value={description}
                    onChange={(e) => setDescription(e.target.value)}
                    className="w-full px-4 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-gray-500"
                    rows="4"
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
