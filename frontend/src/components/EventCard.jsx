import { Link } from "react-router-dom";

export default function EventCard({ event }) {
    return (
        <div className="bg-white p-6 rounded-lg shadow-md hover:shadow-lg transition-shadow">
            <h4 className="text-2xl font-semibold mb-2 text-gray-700">{event.title}</h4>
            <p className="text-gray-600 mb-2">
                <span className="font-semibold">Fecha:</span>{" "}
                {new Date(event.startDateTime).toLocaleDateString()}
            </p>
            <p className="text-gray-700 mb-4">{event.description}</p>
            <Link to={`/edit-event/${event.id}`}>
                <button className="bg-gray-500 hover:bg-gray-600 text-white px-4 py-2 rounded-md transition">
                    Editar
                </button>
            </Link>
        </div>
    );
}
