import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

export default function AvailableEvents() {
    const [events, setEvents] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch("http://localhost:8080/api/events")
            .then((res) => {
                if (!res.ok) throw new Error(`HTTP ${res.status}`);
                return res.json();
            })
            .then((data) => {
                const mapped = data.map((e) => ({
                    id: e.id,
                    title: e.title,
                    date: new Date(e.startDateTime).toLocaleDateString(),
                }));
                setEvents(mapped);
            })
            .catch((err) => {
                console.error("Error al cargar eventos:", err);
                setError("No se pudieron cargar los eventos.");
            });
    }, []);

    if (error) {
        return (
            <div className="min-h-screen p-8 bg-gray-100">
                <p className="text-red-600">{error}</p>
            </div>
        );
    }

    return (
        <div className="min-h-screen p-8 bg-gray-100">
            <h2 className="text-3xl font-bold mb-6 text-gray-900">
                Eventos Disponibles
            </h2>
            <ul className="space-y-4">
                {events.map((event) => (
                    <li
                        key={event.id}
                        className="p-4 bg-white rounded shadow hover:shadow-lg transition"
                    >
                        <Link
                            to={`/events/${event.id}`}
                            className="text-gray-900 font-semibold text-xl"
                        >
                            {event.title}
                        </Link>
                        <p className="text-gray-600">Fecha: {event.date}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
