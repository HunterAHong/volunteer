import React, { useRef, useState } from 'react'
import { Form, Button, Card, Container, Alert } from 'react-bootstrap'
import { useAuth } from '../contexts/AuthContext'
import { Link, useNavigate } from 'react-router-dom'

export default function UpdateProfile() {
    const emailRef = useRef()
    const passwordRef = useRef()
    const passwordConfirmRef = useRef()
    const firstRef = useRef()
    const lastRef = useRef()
    const cityRef = useRef()
    const stateRef = useRef()
    const { currentUser, updateEmail, updatePassword } = useAuth()
    const [error, setError] = useState('')
    const [loading, setLoading] = useState(false)
    const navigate = useNavigate()

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

    async function updateUser(email, first, last, city, state) {
        const user = await getUser(email)
        if (first) {
            user.first = first
        }
        if (last) {
            user.last = last
        }
        if (city) {
            user.city = city
        }
        if (state) {
            user.state = state
        }

        // PUT user
        await fetch("http://localhost:8080/api/v1/users/" + email, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user)
        })
    }

    function handleSubmit(e) {
        e.preventDefault()

        if (passwordRef.current.value !== passwordConfirmRef.current.value) {
            return setError('Passwords do not match')
        }

        const promises = []
        setLoading(true)
        setError("")

        if (emailRef.current.value !== currentUser.email) {
            promises.push(updateEmail(emailRef.current.value))
        }
        if (passwordRef.current.value) {
            promises.push(updatePassword(passwordRef.current.value))
        }
        if (firstRef.current.value || lastRef.current.value || cityRef.current.value
            || stateRef.current.value) {
            promises.push(updateUser(currentUser.email, firstRef.current.value, lastRef.current.value,
                cityRef.current.value, stateRef.current.value))
        }

        Promise.all(promises).then(() => {
            navigate('/')
        }).catch(() => {
            setError('Failed to update account')
        }).finally(() => {
            setLoading(false)
        })
    }

    return (
        <Container
            className='d-flex align-items-center justify-content-center'
            style={{ minHeight: "100vh" }}
        >
            <div className='w-100' style={{ maxWidth: '400px' }}>
                <Card>
                    <Card.Body>
                        <h2 className="text-center mb-4">Update Profile</h2>
                        {error && <Alert variant='danger'>{error}</Alert>}
                        <Form onSubmit={handleSubmit}>
                            <Form.Group id="email">
                                <Form.Label>Email</Form.Label>
                                <Form.Control type="email" ref={emailRef} required
                                    defaultValue={currentUser.email} />
                            </Form.Group>
                            <Form.Group id="first">
                                <Form.Label>First Name</Form.Label>
                                <Form.Control ref={firstRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Form.Group id="last">
                                <Form.Label>Last Name</Form.Label>
                                <Form.Control ref={lastRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Form.Group id="city">
                                <Form.Label>City</Form.Label>
                                <Form.Control ref={cityRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Form.Group id="state">
                                <Form.Label>State</Form.Label>
                                <Form.Control ref={stateRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Form.Group id="password">
                                <Form.Label>Password</Form.Label>
                                <Form.Control type="password" ref={passwordRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Form.Group id="password-confirm">
                                <Form.Label>Password Confirmation</Form.Label>
                                <Form.Control type="password" ref={passwordConfirmRef}
                                    placeholder='Leave blank to keep the same' />
                            </Form.Group>
                            <Button disabled={loading} className="w-100" type="submit">
                                Update
                            </Button>
                        </Form>
                    </Card.Body>
                </Card>
                <div className='w-100 text-center mt-2'>
                    <Link to="/">Cancel</Link>
                </div>

            </div>
        </Container>
    )
}
