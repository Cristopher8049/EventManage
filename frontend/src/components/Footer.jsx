export default function Footer() {
    return (
        <footer className="bg-gray-800 text-white text-center py-6 mt-6">
            <p className="text-sm">
                &copy; {new Date().getFullYear()} EventManage. Todos los derechos reservados.
            </p>
        </footer>
    );
}
