import { useNavigate } from "react-router-dom";
import EventForm from "../components/EventForm";

export default function CreateEvent() {
    const navigate = useNavigate();

    const handleAddEvent = async (newEvent) => {
        try {
            const response = await fetch("http://localhost:8080/api/events", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(newEvent),
            });

            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }

            const createdEvent = await response.json();

            alert(`Evento creado: ${createdEvent.title}`);

            navigate("/dashboard");
        } catch (error) {
            alert("Error al crear evento: " + error.message);
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-6">
            <h1 className="text-3xl font-bold mb-8 text-gray-900">Crear Nuevo Evento</h1>
            <EventForm onSubmit={handleAddEvent} />
        </div>
    );
}
