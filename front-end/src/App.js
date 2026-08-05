
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';


function App() {
  return (
   <BrowserRouter>
      <Routes>
      <Route path="/Login" element={<Login/>}/>
      <Route path="/Dashboard" element={<Dashboard/>}/>
      <Route path="/event/:id" element={<Event/>} />

    </Routes>
   </BrowserRouter>
  );
}

export default App;
