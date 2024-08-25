import './App.css';
import Header from './Header';

function App() {
  return (
    <div className="App">
      <Navbar />
      <AuthProvider>
        <Routes>
          <Route path="/" element={<PrivateRoute><Home API_URL={API_URL} /></PrivateRoute>} />
          <Route path="/update-profile" element={<PrivateRoute><UpdateProfile /></PrivateRoute>} />
          <Route path="/matches" element={<Matches API_URL={API_URL} />} />
          <Route path="/events" element={<Events room={room} />} />
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
