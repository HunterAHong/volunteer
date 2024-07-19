import Content from './Content';
import Header from './Header';
import UserForm from './UserForm';
import Navbar from "./components/Navbar";
import Home from "./pages/Home";
import Matches from "./pages/Matches";
import Events from "./pages/Events";
import Profile from "./pages/Profile";

import { useState, useEffect } from 'react';
import { Route, Routes } from "react-router-dom";

function App() {
  const API_URL = "http://localhost:8080/api/v1/users"
  // form stuff

  const addItem = (item) => {
    //think about what an item is, how it is constructed
    //set id
  }

  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home API_URL={API_URL}/>}/>
        <Route path="/matches" element={<Matches API_URL={API_URL}/>}/>
        <Route path="/events" element={<Events/>}/>
        <Route path="/profile" element={<Profile/>}/>
      </Routes>
    </div>
  );
}

export default App;
