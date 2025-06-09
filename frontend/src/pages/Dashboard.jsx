import { useState } from "react";
import { Link } from "react-router-dom";
import EventCard from "../components/EventCard";

export default function Dashboard() {
    const [events] = useState([
        { id: 1, title: "Concierto Rock", date: "2025-07-20", description: "Evento musical con bandas locales." },
        { id: 2, title: "Feria de Tecnología", date: "2025-08-05", description: "Exposición de avances tecnológicos." },
        { id: 3, title: "Maratón Solidario", date: "2025-09-12", description: "Carrera benéfica en la ciudad." },
    ]);

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
