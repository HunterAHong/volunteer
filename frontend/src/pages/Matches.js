import React, { useEffect } from "react"
import { useState } from "react"
import { FaMinus, FaPlus } from 'react-icons/fa'
import { useAuth } from '../contexts/AuthContext'
import { Card } from "react-bootstrap"

export default function Matches({ API_URL }) {
  const [users, setUsers] = useState([])
  const { currentUser } = useAuth()
  const [matches, setMatches] = useState([])


  useEffect(() => {
    let ignore = false
    const fetchUsers = async () => {
      try {
        const response = await fetch(API_URL)
        const userList = await response.json()
        setUsers(userList)

        fetchMatches()

      } catch (err) {
        console.log(err.stack)
      }
    }

    if (!ignore) fetchUsers()
    return () => { ignore = true }
  }, [API_URL])

  const fetchMatches = async () => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/matches/" + currentUser.email)
      const currentMatches = await response.json()
      setMatches(currentMatches)
    } catch (err) {
      console.log(err.stack)
    }
  }

  const getUser = async (email) => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/users/" + email)
      const user = await response.json()
      return user
    } catch (err) {
      console.log(err.stack)
    }
  }

  const addMatch = async (email) => {
    console.log("email of match: " + email)
    // put match
    const response = fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: email
    })

    fetchMatches()
  }

  const removeMatch = async (email) => {
    console.log(email)
    const response = fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(email)
    })

    console.log(response)
    fetchMatches()

    console.log((await response).json)
  }

  return (
    <main>
      <h1>Matches</h1>
      <div>
        {users.map(user => (
          <div key={user.email}>{user.first} {user.last}
            <button onClick={() => addMatch(user.email)}
              title='Match'
              type='submit'
              aria-label='Enter Name'
            ><FaPlus /></button>
          </div>
        ))}

        <Card className="d-flex align-items-center justify-content-center">
          <Card.Body>
            <h5 className="card-title">
              Your Matched
            </h5>

            <p>
              {matches.map(match => (
                <div key={match.email}>{match.first} {match.last}
                  <button onClick={() => removeMatch(match.email)}
                    title='Match'
                    type='submit'
                    aria-label='Enter Name'
                  ><FaMinus /></button>
                </div>))}
            </p>
          </Card.Body>
        </Card>
      </div>

    </main >)

}