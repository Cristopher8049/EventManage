import { Link } from "react-router-dom";
import { useState, useEffect } from "react";

export default function AvailableEvents() {
    const [events, setEvents] = useState([]);

    useEffect(() => {
        // Aquí puedes hacer fetch real desde el backend
        // Simulación temporal:
        const fakeEvents = [
            { id: 1, title: "Hackathon UCB", date: "2025-07-01" },
            { id: 2, title: "Congreso de Tecnología", date: "2025-08-15" },
        ];
        setEvents(fakeEvents);
    }, []);

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
                        <Link to={`/events/${event.id}`} className="text-gray-600 font-semibold text-xl">
                            {event.title}
                        </Link>
                        <p className="text-gray-600">Fecha: {event.date}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
