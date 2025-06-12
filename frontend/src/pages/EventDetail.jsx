import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";
import RegisterForm from "../components/RegisterForm";

export default function EventDetail() {
    const { id } = useParams();
    const [event, setEvent] = useState(null);
    const [showForm, setShowForm] = useState(false);
    const [registeredEmail, setRegisteredEmail] = useState("");

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/${id}`)
            .then((res) => res.json())
            .then(setEvent)
            .catch((err) => console.error("Error al cargar evento:", err));
    }, [id]);

    if (!event) return <p>Cargando...</p>;

    const formattedDate = new Date(event.startDateTime).toLocaleDateString();

    if (registeredEmail) {
        return (
            <div className="p-8">
                <p>
                    Gracias por inscribirte a <strong>{event.title}</strong>. Te llegará un correo a{" "}
                    <em>{registeredEmail}</em>.
                </p>
            </div>
        );
    }

    return (
        <div className="min-h-screen p-8 bg-gray-100 flex justify-center">
            <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-lg">
                <h2 className="text-3xl font-bold mb-2 text-gray-900">{event.title}</h2>
                <p className="mb-4 text-gray-700">Fecha: {formattedDate}</p>
                <p className="mb-6 text-gray-600">{event.description}</p>

                {!showForm ? (
                    <button
                        onClick={() => setShowForm(true)}
                        className="bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-800"
                    >
                        Registrarse
                    </button>
                ) : (
                    <RegisterForm eventId={id} onSuccess={setRegisteredEmail} />
                )}
            </div>
        </div>
    );
}
