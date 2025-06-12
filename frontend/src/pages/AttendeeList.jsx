import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";

export default function AttendeeList() {
    const { id } = useParams();
    const [attendees, setAttendees] = useState([]);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/${id}/attendees`)
            .then(res => {
                if (!res.ok) throw new Error("Error al cargar asistentes");
                return res.json();
            })
            .then(data => setAttendees(data))
            .catch(err => setError(err.message));
    }, [id]);

    if (error) return <p className="text-red-600 p-4">{error}</p>;

    return (
        <div className="max-w-3xl mx-auto p-6">
            <h2 className="text-2xl font-bold mb-4">Asistentes del evento</h2>
            {attendees.length === 0 ? (
                <p>No hay asistentes inscritos aún.</p>
            ) : (
                <ul className="divide-y divide-gray-300">
                    {attendees.map((a) => (
                        <li key={a.id} className="py-3">
                            <p><strong>Nombre:</strong> {a.name}</p>
                            <p><strong>Email:</strong> {a.email}</p>
                            <p><strong>Teléfono:</strong> {a.phoneNumber}</p>
                        </li>
                    ))}
                </ul>
            )}
        </div>
    );
}
