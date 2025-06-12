import { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";

export default function AuthForm({ onLogin, onRegister }) {
    const location = useLocation();
    const [mode, setMode] = useState("login");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [name, setName] = useState("");

    useEffect(() => {
        if (location.state?.mode === "register") {
            setMode("register");
        } else {
            setMode("login");
        }
    }, [location.state]);

    const handleSubmit = (e) => {
        e.preventDefault();
        if (mode === "login") {
            onLogin({ email, password });
        } else {
            if (!name || !email || !password) {
                alert("Completa todos los campos");
                return;
            }
            onRegister({ name, email, password });
        }
    };

    return (
        <div className="max-w-md mx-auto bg-white shadow-md rounded-xl p-8 mt-10">
            <div className="flex justify-center mb-6">
                <button
                    onClick={() => setMode("login")}
                    className={`px-4 py-2 rounded-l-md font-bold ${mode === "login" ? "bg-gray-800 text-white" : "bg-gray-200"}`}
                >
                    Iniciar sesión
                </button>
                <button
                    onClick={() => setMode("register")}
                    className={`px-4 py-2 rounded-r-md font-bold ${mode === "register" ? "bg-gray-800 text-white" : "bg-gray-200"}`}
                >
                    Registrarse
                </button>
            </div>

            <form onSubmit={handleSubmit} className="space-y-4">
                {mode === "register" && (
                    <div>
                        <label className="block text-sm font-semibold mb-1">Nombre</label>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            className="w-full px-4 py-2 border rounded-md"
                        />
                    </div>
                )}

                <div>
                    <label className="block text-sm font-semibold mb-1">Correo electrónico</label>
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        className="w-full px-4 py-2 border rounded-md"
                        required
                    />
                </div>

                <div>
                    <label className="block text-sm font-semibold mb-1">Contraseña</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="w-full px-4 py-2 border rounded-md"
                        required
                    />
                </div>

                <button
                    type="submit"
                    className="w-full py-2 mt-4 bg-gray-800 text-white rounded-md font-bold hover:bg-gray-700 transition"
                >
                    {mode === "login" ? "Iniciar sesión" : "Registrarse"}
                </button>
            </form>
        </div>
    );
}
