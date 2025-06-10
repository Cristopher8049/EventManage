import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import EventForm from "../components/EventForm";

export default function EditEvent() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [initialData, setInitialData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        fetch(`http://localhost:8080/api/events/${id}`)
            .then((res) => {
                if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
                return res.json();
            })
            .then((data) => {
                setInitialData({
                    title: data.title,
                    description: data.description,
                    startDateTime: data.startDateTime,
                    endDateTime: data.endDateTime,
                    location: data.location,
                    capacity: data.maxCapacity,
                });
                setLoading(false);
            })
            .catch((err) => {
                setError("Error al cargar evento");
                setLoading(false);
                console.error(err);
            });
    }, [id]);

    const handleUpdate = (updatedEvent) => {
        fetch(`http://localhost:8080/api/events/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedEvent),
        })
            .then((res) => {
                if (!res.ok) throw new Error(`HTTP error! status: ${res.status}`);
                return res.json();
            })
            .then(() => {
                alert("Evento actualizado correctamente");
                navigate("/dashboard");
            })
            .catch((err) => {
                alert("Error al actualizar evento");
                console.error(err);
            });
    };

    if (loading) {
        return (
            <div className="flex justify-center items-center min-h-screen">
                <p className="text-gray-700 text-lg">Cargando evento...</p>
            </div>
        );
    }

    if (error) {
        return (
            <div className="flex justify-center items-center min-h-screen">
                <p className="text-red-600 text-lg">{error}</p>
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
