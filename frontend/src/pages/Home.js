import UserForm from "../UserForm";
import React, { useState, Switch } from "react"
import { Card, Button, Alert, Form } from 'react-bootstrap'
import { useAuth } from "../contexts/AuthContext";
import { Link, useNavigate } from "react-router-dom";

export default function Home({ API_URL }) {
    const [error, setError] = useState("")
    const { currentUser, logout } = useAuth()
    const navigate = useNavigate()
    const [isVolunteer, setIsVolunteer] = useState(true)

    async function handleLogout() {
        setError('')

        try {
            await logout()
            navigate('/login')
        } catch {
            setError('Failed to log out')
        }
    }

    async function getUser() {
        //fetch user based on email
        //translate from json to obj
        try {
            const userResponse = await fetch("http://localhost:8080/api/v1/users/" + currentUser.email)
            const user = await userResponse.json()
            return user
        } catch (err) {
            console.log(err.stack)
        }
    }

    async function switchChange() {
        const user = await getUser()
        user.volunteer = !isVolunteer
        setIsVolunteer(!isVolunteer)

        console.log(JSON.stringify(user))

        await fetch("http://localhost:8080/api/v1/users/" + currentUser.email, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        })

        console.log("switched")
    }

    return (
        <main>
            <h1>Home</h1>
            <Form>
                <Form.Check onClick={switchChange}
                    enabled="true"
                    type="switch"
                    id="volunteerSwitch"
                    label="Organizer Mode"
                />
            </Form>

            <div className="w-100 text-center mt-2">
                <Button variant="link" onClick={handleLogout}>
                    Log Out
                </Button>
            </div>
        </main>
    )
}