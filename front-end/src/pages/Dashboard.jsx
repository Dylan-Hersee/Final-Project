import { useNavigate } from "react-router-dom";
import { auth } from "../firebase";
import { signOut } from "firebase/auth";
import "./Dashboard.css";

const Dashboard = () => {
  const nav = useNavigate();

  const handleEvent = () => nav('/event');
  const handleProfile = () => nav('/profile');
  const handleLogout = async () => {
    await signOut(auth);
    nav('/');
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
        <div className="eventCard"></div>
      </div>

      <div className="createEvent">
        <form onSubmit={handleEvent}>
          <input type="text" placeholder="Event Name" />
          <input type="text" placeholder="Event Type" />
          <input type="text" placeholder="Budget" />
          <input type="date" placeholder="Event Date" />
          <input type="text" placeholder="Task" />
          <br />
          <br />
          <button type="submit">Create Event</button>
        </form>
      </div>
    </div>
  );
};

export default Dashboard;
