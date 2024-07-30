import React, { useEffect } from "react"
import { useState } from "react"
import { FaPlus } from 'react-icons/fa'
import { useAuth } from '../contexts/AuthContext'
import { Card } from "react-bootstrap"

export default function Matches({ API_URL }) {
  const [users, setUsers] = useState([])
  const { currentUser } = useAuth()
  const [matches, setMatches] = useState()


  useEffect(() => {
    let ignore = false
    const fetchUsers = async () => {
      try {
        const response = await fetch(API_URL)
        const userList = await response.json()
        console.log(currentUser.email)
        setUsers(userList)

        const userResponse = await fetch(API_URL + "/palid2@outlook.com")
        const user = await userResponse.json()
        console.log(user)

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
      console.log(currentUser.email)
      const response = await fetch("http://localhost:8080/api/v1/matches/" + currentUser.email)
      console.log(response)
      console.log(response.headers)
      setMatches(await response.json())
      console.log("This is matches" + matches)
    } catch (err) {
      console.log(err.stack)
    }
  }

  const addMatch = async (email) => {
    console.log(email)
    // put match
    const response = fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(email)
    })

    console.log(response)
    console.log((await response).type)

    fetchMatches()
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
              {matches}
            </p>
            {/* {matches.map(matches => (
              <div key={matches.id}> {matches.email}</div>
            ))} */}
          </Card.Body>
        </Card>
      </div>

    </main>)

}