import { useParams } from "react-router-dom";
import { useState, useEffect } from "react";

export default function EventDetail() {
    const { id } = useParams();
    const [event, setEvent] = useState(null);
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [submitted, setSubmitted] = useState(false);

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/${id}`)
            .then((res) => {
                if (!res.ok) throw new Error(`HTTP ${res.status}`);
                return res.json();
            })
            .then((data) => {
                setEvent(data);
            })
            .catch((err) => {
                console.error("Error al cargar evento:", err);
            });
    }, [id]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (!name || !email) return alert("Completa todos los campos");

        // Aquí enviarías al backend (p.ej. POST a /api/events/{id}/registrations)
        setSubmitted(true);
    };

    if (!event) return <p>Cargando...</p>;
    if (submitted)
        return (
            <p>
                Gracias por inscribirte a <strong>{event.title}</strong>. Te llegará un
                correo a <em>{email}</em>.
            </p>
        );

    // Formateo de la fecha
    const formattedDate = new Date(event.startDateTime).toLocaleDateString();

    return (
        <div className="min-h-screen p-8 bg-gray-100 flex justify-center">
            <div className="bg-white p-6 rounded-lg shadow-lg w-full max-w-lg">
                <h2 className="text-3xl font-bold mb-2 text-gray-900">
                    {event.title}
                </h2>
                <p className="mb-4 text-gray-700">Fecha: {formattedDate}</p>
                <p className="mb-6 text-gray-600">{event.description}</p>

                <h3 className="text-2xl font-semibold mb-4">Formulario de Inscripción</h3>
                <form onSubmit={handleSubmit}>
                    <label className="block mb-2 font-semibold text-gray-700">Nombre</label>
                    <input
                        type="text"
                        className="w-full p-2 border border-gray-300 rounded mb-4 focus:outline-gray-500"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />

                    <label className="block mb-2 font-semibold text-gray-700">
                        Correo electrónico
                    </label>
                    <input
                        type="email"
                        className="w-full p-2 border border-gray-300 rounded mb-4 focus:outline-gray-500"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />

                    <button
                        type="submit"
                        className="bg-gray-600 text-white py-2 px-4 rounded hover:bg-gray-700 transition"
                    >
                        Inscribirse
                    </button>
                </form>
            </div>
        </div>
    );
}
