import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { auth } from "../firebase";

const Dashboard = () => {
    const nav = useNavigate();

    const handleEvent = () => nav('/Event');

    return (
        <div>
            <h1>Simple Events</h1>
            <ul>
                <li>Simple Events</li>
                <li>Manage Profile</li>
                <li>Logout</li>
            </ul>
            <div className="mainEvents">
                <div className="eventDesc" >

                </div>
                <div className="eventCard">

                </div>
            </div>
            <div className="createEvent">
                <form action="">
                    <input type="text" placeholder="Event Name" />
                    <input type="text" placeholder="Event Type" />
                    <input type="text" placeholder="Budget" />
                    <input type="date" placeholder="Event Date" />
                    <input type="text" placeholder="Task" multiple></input>
                    <br />
                    <br />
                </form>
            </div>
        </div>
    );
};


export default Dashboard;
