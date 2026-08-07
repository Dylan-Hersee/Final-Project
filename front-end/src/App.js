
import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Dashboard from './pages/Dashboard';
import Event from './pages/Event';
import { AuthProvider } from './pages/Auth';



function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Login/>}/>
          <Route path="/Dashboard" element={<Dashboard/>}/>
          <Route path="/Event/:id" element={<Event/>} />
       </Routes>
      </BrowserRouter>
   </AuthProvider>
  );
}

export default App;
