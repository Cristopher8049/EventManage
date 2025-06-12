import { useState } from "react";

export default function RegisterForm({ onSuccess }) {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [phoneNumber, setPhone] = useState("");

    const handleSubmit = async (e) => {
        e.preventDefault();
        if (!name || !email || !phoneNumber) return alert("Completa todos los campos");

        try {
            await fetch(`http://localhost:8080/api/attendees`, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ name, email, phoneNumber }),
            });

            onSuccess(email);
        } catch (err) {
            alert("Error al registrar");
            console.error(err);
        }
    };

    return (
        <form onSubmit={handleSubmit} className="mt-6">
            <h3 className="text-xl font-semibold mb-4">Formulario de Inscripción</h3>

            <label className="block mb-1 font-medium">Nombre</label>
            <input
                type="text"
                className="w-full p-2 border border-gray-300 rounded mb-4"
                value={name}
                onChange={(e) => setName(e.target.value)}
                required
            />

            <label className="block mb-1 font-medium">Correo electrónico</label>
            <input
                type="email"
                className="w-full p-2 border border-gray-300 rounded mb-4"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
            />

            <label className="block mb-1 font-medium">Número de teléfono</label>
            <input
                type="tel"
                pattern="[0-9]{7,15}"
                placeholder="Ej: 76543210"
                className="w-full p-2 border border-gray-300 rounded mb-4"
                value={phoneNumber}
                onChange={(e) => setPhone(e.target.value)}
                required
            />

            <button
                type="submit"
                className="bg-gray-700 text-white px-4 py-2 rounded hover:bg-gray-800"
            >
                Inscribirse
            </button>
        </form>
    );
}
