import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { signOut } from "firebase/auth";
import { auth } from "../firebase";
import { useAuth } from "../context/AuthContext";
import { apiRequest } from "../api";
import "./Dashboard.css";

const Dashboard = () => {
  const nav = useNavigate();
  const { user } = useAuth();

  const [events, setEvents] = useState([]);
  const [eventName, setEventName] = useState("");
  const [eventType, setEventType] = useState("");
  const [budget, setBudget] = useState("");
  const [eventDate, setEventDate] = useState("");
  const [error, setError] = useState("");

  useEffect(() => {
    const loadEvents = async () => {
      if (!user) return;
      try {
        const token = await user.getIdToken();
        const data = await apiRequest(`/event/get?username=${user.uid}`, "GET", token);
        setEvents(data);
      } catch (err) {
        console.error("Failed to load events:", err);
      }
    };
    loadEvents();
  }, [user]);

  const handleLogout = async () => {
    await signOut(auth);
    nav('/');
  };

  const handleProfile = () => nav('/profile');

  const handleCreateEvent = async (e) => {
    e.preventDefault();
    setError("");

    if (!eventName || !eventType || !budget || !eventDate) {
      setError("Please fill in all fields");
      return;
    }

    try {
      const token = await user.getIdToken();
      const newEvent = await apiRequest("/event/create", "POST", token, {
        username: user.uid,
        eventName,
        eventType,
        budget,
        eventDate
      });

      setEvents([...events, newEvent]);
      setEventName("");
      setEventType("");
      setBudget("");
      setEventDate("");

    } catch (err) {
      setError("Failed to create event: " + err.message);
    }
  };

  return (
    <div>
      <h1>Simple Events</h1>
      <ul>
        <li>Simple Events</li>
        <li onClick={handleProfile}>Manage Profile</li>
        <li onClick={handleLogout}>Logout</li>
      </ul>

      <div className="mainEvents">
        <div className="eventDesc"></div>
        <div className="eventCard">
          {events.map((event) => (
            <div key={event.id} onClick={() => nav(`/event/${event.id}`)}>
              <h3>{event.eventName}</h3>
              <p>{event.eventType}</p>
            </div>
          ))}
        </div>
      </div>

      <div className="createEvent">
        <form onSubmit={handleCreateEvent}>
          <input type="text" placeholder="Event Name" value={eventName} onChange={(e) => setEventName(e.target.value)} />
          <input type="text" placeholder="Event Type" value={eventType} onChange={(e) => setEventType(e.target.value)} />
          <input type="text" placeholder="Budget" value={budget} onChange={(e) => setBudget(e.target.value)} />
          <input type="date" value={eventDate} onChange={(e) => setEventDate(e.target.value)} />
          <br /><br />
          <button type="submit">Create Event</button>
        </form>
        {error && <p style={{ color: "red" }}>{error}</p>}
      </div>
    </div>
  );
};

export default Dashboard;
