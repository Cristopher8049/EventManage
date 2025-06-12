import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import CreateEvent from "./pages/CreateEvent";
import EditEvent from "./pages/EditEvent";
import AvailableEvents from "./pages/AvailableEvents";
import EventDetail from "./pages/EventDetail";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import AuthForm from "./pages/AuthForm";
import AttendeeList from "./pages/AttendeeList";

import "./index.css";



function Home() {
  return (
    <div className="min-h-screen flex flex-col items-center justify-center bg-gradient-to-r from-gray-900 via-gray-500 to-gray-900 text-white p-6">
    </div>
  );
}

export default function App() {
  return (
    <Router>
      <div className="flex flex-col h-screen">
        <Navbar />
        <main className="flex-grow overflow-auto">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/create-event" element={<CreateEvent />} />
            <Route path="/events" element={<AvailableEvents />} />
            <Route path="/events/:id" element={<EventDetail />} />
            <Route path="/edit-event/:id" element={<EditEvent />} />
            <Route path="/auth" element={<AuthForm />} />
            <Route path="/events/:id/attendees" element={<AttendeeList />} />

          </Routes>
        </main>
        <Footer />
      </div>
    </Router>
  );
}
