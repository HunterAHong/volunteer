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

        const userResponse = await fetch(API_URL + "/palid2@outlook.com")
        const user = await userResponse.json()

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
      setMatches(await response.json())

    } catch (err) {
      console.log(err.stack)
    }
  }

  const addMatch = async (email) => {
    // put match
    const response = fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(email)
    })

    console.log((await response).type)

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

    fetchMatches()

    console.log((await response).json)
  }

  return (
    <main>
      <h1>Matches</h1>
      <div>
        {users.map(user => (
          <div key={user.id}>{user.first} {user.last}
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
              {matches.map(match => (<ul><li>{match}</li>
                <button onClick={() => removeMatch(match)}
                  title='Match'
                  type='submit'
                  aria-label='Enter Name'
                ><FaMinus /></button>
              </ul>))}
            </p>
          </Card.Body>
        </Card>
      </div>

    </main >)

}