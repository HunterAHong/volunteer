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
  const [name, setName] = useState('')
  const [number, setNumber] = useState('')
  const [users, setUsers] = useState([])

  const addItem = (item) => {
    //think about what an item is, how it is constructed
    //set id
  }

  const phoneNumber = number
  const first = name
  // for obj passed into POST, needs fields to match obj in backend exactly
  const user = {phoneNumber, first};

  const fetchUsers = async () => {
    try {
      const response = await fetch(API_URL)
      //console.log(response)
      const userList = await response.json()
      console.log(userList)
      setUsers(userList)
    } catch (err) {
      console.log(err.stack)
    }
  }

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name) return
    if (!number) return
    console.log(name)
    console.log(number)
    console.log("User obj")
    console.log(user)
    console.log(JSON.stringify(user))
    try {
      fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
      })
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
    }
    // setting the state of the input box back to empty
    setName('')
    setNumber('')
    fetchUsers()
  }
  // form stuff end


  return (
    <div className="App">
      <Navbar/>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/matches" element={<Matches/>}/>
        <Route path="/events" element={<Events/>}/>
        <Route path="/profile" element={<Profile/>}/>
      </Routes>
      <UserForm 
        name = {name}
        setName = {setName}
        number = {number}
        setNumber = {setNumber}
        handleSubmit = {handleSubmit}
      />
      <Content 
        users = {users}
      />
    </div>
  );
}

export default App;
