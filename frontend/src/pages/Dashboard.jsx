import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import EventCard from "../components/EventCard";

export default function Dashboard() {
    const [events, setEvents] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/events")
            .then((res) => {
                if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
                return res.json();
            })
            .then((data) => {
                setEvents(data);
                setLoading(false);
            })
            .catch((err) => {
                setError("Error al cargar eventos");
                setLoading(false);
                console.error(err);
            });
    }, []);

    if (loading) {
        return (
            <div className="min-h-screen p-8 bg-gray-50">
                <p>Cargando eventos...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="min-h-screen p-8 bg-gray-50">
                <p className="text-red-600">{error}</p>
            </div>
        );
    }

    return (
        <div className="min-h-screen p-8 bg-gray-50">
            <h2 className="text-3xl font-bold mb-6 text-gray-900">Dashboard del Organizador</h2>

            <Link
                to="/create-event"
                className="inline-block mb-8 bg-gray-600 text-white px-6 py-3 rounded-md shadow-md hover:bg-gray-700 transition"
            >
                Crear nuevo evento
            </Link>

            <h3 className="text-xl font-semibold mb-4 text-gray-800">Lista de eventos</h3>

            {events.length === 0 ? (
                <p className="text-gray-600">No tienes eventos creados.</p>
            ) : (
                <div className="grid gap-6 md:grid-cols-2 lg:grid-cols-3">
                    {events.map((event) => (
                        <EventCard key={event.id} event={event} />
                    ))}
                </div>
            )}
        </div>
    );
}
