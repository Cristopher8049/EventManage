import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import EventForm from "../components/EventForm";

export default function EditEvent() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [initialData, setInitialData] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        // Simula obtener evento del backend
        const eventFromBackend = {
            id,
            title: "Evento de ejemplo",
            date: "2025-07-20",
            description: "Descripción ejemplo para editar.",
        };

        setInitialData(eventFromBackend);
        setLoading(false);
    }, [id]);

    const handleUpdate = (updatedEvent) => {
        // Aquí actualizarías el evento en el backend
        console.log("Actualizado:", updatedEvent);
        alert("Evento actualizado correctamente");
        navigate("/dashboard");
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center min-h-screen">
                <p className="text-gray-700 text-lg">Cargando evento...</p>
            </div>
        );
    }

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-6">
            <h1 className="text-3xl font-bold mb-8 text-gray-900">Editar Evento</h1>
            <EventForm initialData={initialData} onSubmit={handleUpdate} />
        </div>
    );
}
