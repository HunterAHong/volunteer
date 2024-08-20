import Navbar from "./components/Navbar";
import Signup from './components/Signup';
import Home from "./pages/Home";
import Matches from "./pages/Matches";
import Events from "./pages/Events";
import Profile from "./pages/Profile";
import Login from './pages/Login';
import ForgotPassword from "./pages/ForgotPassword";
import UpdateProfile from "./pages/UpdateProfile";

import { Route, Routes } from "react-router-dom";
import { AuthProvider } from './contexts/AuthContext';
import PrivateRoute from './components/PrivateRoute';

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
          <Route path="/" element={<PrivateRoute><Home API_URL={API_URL} /></PrivateRoute>} />
          <Route path="/update-profile" element={<PrivateRoute><UpdateProfile /></PrivateRoute>} />
          <Route path="/matches" element={<Matches API_URL={API_URL} />} />
          <Route path="/events" element={<Events />} />
          <Route path="/profile" element={<Profile API_URL={API_URL} />} />
          <Route path="/signup" element={<Signup />} />
          <Route path="/login" element={<Login />} />
          <Route path="/forgot-password" element={<ForgotPassword />} />

        </Routes>
      </AuthProvider>
    </div>
  );
}

export default App;
