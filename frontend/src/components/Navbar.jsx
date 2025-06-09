import { Link } from "react-router-dom";

export default function Navbar() {
    return (
        <nav className="bg-gray-700 text-white p-4 flex justify-between items-center">
            <Link to="/" className="font-bold text-xl">EventManage</Link>
            <div className="space-x-4">
                <Link to="/dashboard" className="hover:underline">Dashboard</Link>
                <Link to="/events" className="hover:underline">Eventos</Link>
                <Link to="/login" className="hover:underline">Login</Link>
                <Link to="/register" className="hover:underline">Registro</Link>
            </div>
        </nav>
    );
}
