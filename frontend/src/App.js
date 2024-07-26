import Content from './Content';
import Header from './Header';
import UserForm from './UserForm';
import Navbar from "./components/Navbar";
import Signup from './components/Signup';
import Home from "./pages/Home";
import Matches from "./pages/Matches";
import Events from "./pages/Events";
import Profile from "./pages/Profile";
import Login from './pages/Login';

import { useState, useEffect } from 'react';
import { Route, Routes } from "react-router-dom";
import { AuthProvider } from './contexts/AuthContext';

function App() {
  const API_URL = "http://localhost:8080/api/v1/users"
  // form stuff

  const addItem = (item) => {
    //think about what an item is, how it is constructed
    //set id
  }

  return (
    <div className="App">
      <Navbar />
      <AuthProvider>
        <Routes>
          <Route path="/" element={<Home API_URL={API_URL} />} />
          <Route path="/matches" element={<Matches API_URL={API_URL} />} />
          <Route path="/events" element={<Events />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
        </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
