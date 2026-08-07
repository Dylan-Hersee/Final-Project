
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';



function App() {
  return (
   <BrowserRouter>
      <Routes>
      <Route path="/" element={<Login/>}/>
      <Route path="/Dashboard" element={<Dashboard/>}/>
      <Route path="/Event/:id" element={<Event/>} />

    </Routes>
   </BrowserRouter>
  );
}

export default App;
