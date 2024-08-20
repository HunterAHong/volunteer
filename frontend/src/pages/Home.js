import UserForm from "../UserForm";
import React, { useState, useEffect, Switch } from "react"
import { Card, Button, Alert, Form } from 'react-bootstrap'
import { useAuth } from "../contexts/AuthContext";
import { Link, useNavigate } from "react-router-dom";
import { getScopedCssBaselineUtilityClass } from "@mui/material";

export default function Home({ API_URL }) {
    const [error, setError] = useState("")
    const { currentUser, logout } = useAuth()
    const navigate = useNavigate()
    const [isVolunteer, setIsVolunteer] = useState(true)
    const [isChecked, setIsChecked] = useState(false)

    useEffect(() => {
        const fetchInitialSwitchState = async () => {
            const user = await getUser()
            console.log("Initial user state: " + user.volunteer)
            setIsVolunteer(user.volunteer)
            setIsChecked(!user.volunteer)
        }

        fetchInitialSwitchState()
        console.log("checked " + isChecked)
    }, [])

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
        console.log(!isVolunteer)
        console.log(user.volunteer)
        setIsVolunteer(!isVolunteer)
        setIsChecked(!isChecked)
        console.log("switched su[[psedi;sy")
        await fetch("http://localhost:8080/api/v1/users/" + currentUser.email, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        })
    }

    return (
        <main>
            <h1>Home</h1>
            <Form>
                <Form.Check
                    onChange={switchChange}
                    enabled="true"
                    type="switch"
                    id="volunteerSwitch"
                    checked={isChecked}
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