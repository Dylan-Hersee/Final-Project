import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { getAuth, signInWithEmailAndPassword, createUserWithEmailAndPassword } from "firebase/auth";
import { auth } from "../firebase";

const Event = () => {

    return (
        <>
            <div>
                <div className="Due"> Event Due Date</div>
                <div className="progressBar"></div>

                <div className="eventdetails">
                    <h1>Event Details</h1>
                    <h2 className="nameHead">Event Name</h2>
                    <p className="eventName"></p><button className="change"></button>

                    <h2 className="eventType">Event Name</h2>
                    <p className="eventType"></p><button className="change"></button>
                </div>
                <div className="calender">
                    <h2>Due Date</h2>
                    <div className="month">
                        <ul>
                            <li className="prev">&#10094;</li>
                            <li className="next">&#10095;</li>
                            <li>
                                August<br />
                                <span style={{ fontSize: '18px' }}>2021</span>
                            </li>
                        </ul>
                    </div>

                    <ul className="weekdays">
                        <li>Mo</li>
                        <li>Tu</li>
                        <li>We</li>
                        <li>Th</li>
                        <li>Fr</li>
                        <li>Sa</li>
                        <li>Su</li>
                    </ul>

                    <ul className="days">
                        <li>1</li>
                        <li>2</li>
                        <li>3</li>
                        <li>4</li>
                        <li>5</li>
                        <li>6</li>
                        <li>7</li>
                        <li>8</li>
                        <li>9</li>
                        <li><span className="active">10</span></li>
                        <li>11</li>
                        <li>12</li>
                        <li>13</li>
                        <li>14</li>
                        <li>15</li>
                        <li>16</li>
                        <li>17</li>
                        <li>18</li>
                        <li>19</li>
                        <li>20</li>
                        <li>21</li>
                        <li>22</li>
                        <li>23</li>
                        <li>24</li>
                        <li>25</li>
                        <li>26</li>
                        <li>27</li>
                        <li>28</li>
                        <li>29</li>
                        <li>30</li>
                        <li>31</li>
                    </ul>
                </div>
            </div>
            <div className="checklist"></div>
            <div className="guestlist"></div>
        </>
    );
}

export default Event;