import React, { useState } from "react"
import { Card, Button, Alert } from 'react-bootstrap'
import { useAuth } from "../contexts/AuthContext";
import { Link, useNavigate } from "react-router-dom";
import ProfilePicture from "./ProfilePicture";

export default function Profile() {
    const navigate = useNavigate()
    const { currentUser, logout } = useAuth()
    const [error, setError] = useState("")

    async function handleLogout() {
        setError('')

        try {
            await logout()
            navigate('/login')
        } catch {
            setError('Failed to log out')
        }
    }

    return (
        <main>
            <Card>
                <Card.Body>
                    <h2 className="text-center mb-4">Profile</h2>
                    {error && <Alert variant='danger'>{error}</Alert>}
                    <strong>Email:</strong> {currentUser.email}
                    <Link to="/update-profile" className="btn btn-primary w-100 mt-3>">
                        Update Profile
                    </Link>
                    <ProfilePicture />
                </Card.Body>
            </Card>
            <div className="w-100 text-center mt-2">
                <Button variant="link" onClick={handleLogout}>
                    Log Out
                </Button>
            </div>
        </main>
    )
}