import { useNavigate } from "react-router-dom";
import EventForm from "../components/EventForm";

export default function CreateEvent() {
    const navigate = useNavigate();

    const handleAddEvent = (newEvent) => {
        // Aquí luego conectarás con el backend para guardar el evento
        console.log("Evento creado:", newEvent);

        alert("Evento creado: " + newEvent.title);

        // Volver al dashboard
        navigate("/dashboard");
    };

    return (
        <div className="min-h-screen bg-gray-100 flex flex-col items-center justify-center p-6">
            <h1 className="text-3xl font-bold mb-8 text-gray-900">Crear Nuevo Evento</h1>
            <EventForm onSubmit={handleAddEvent} />
        </div>
    );
}
