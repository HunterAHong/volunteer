import React, { useEffect } from "react"
import { useState } from "react"
import { FaMinus, FaPlus } from 'react-icons/fa'
import { useAuth } from '../contexts/AuthContext'
import { Card } from "react-bootstrap"

export default function Matches({ API_URL }) {
  const [users, setUsers] = useState([])
  const { currentUser } = useAuth()
  const [matches, setMatches] = useState([])
  const [excludedEmails, setExcludedEmails] = useState([])


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

  useEffect(() => {
    //on matches state change, then changes excluded
    if (matches.length > 0) {
      for (let i = 0; i < matches.length; i++) {
        setExcludedEmails(excludedEmails => [...excludedEmails, matches[i].email])
      }
    }

  }, [matches])

  const getUser = async (email) => {
    try {
      const response = await fetch("http://localhost:8080/api/v1/users/" + email)
      const user = await response.json()
      return user
    } catch (err) {
      console.log(err.stack)
    }
  }

  const removeEmail = (emailToRemove) => {
    setExcludedEmails(prevExcludedEmails =>
      prevExcludedEmails.filter(email => email !== emailToRemove)
    )
  }

  const addMatch = async (email) => {
    // put match
    await fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: email
    })
    setExcludedEmails([...excludedEmails, email])
    fetchMatches()
  }

  const removeMatch = async (email) => {
    await fetch("http://localhost:8080/api/v1/matches/" + currentUser.email, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json',
      },
      body: email
    })
    removeEmail(email)
    fetchMatches()
  }

  const filteredUsers = users.filter(user => !excludedEmails.includes(user.email))

  return (
    <main>
      <h1>Matches</h1>
      <div>
        {filteredUsers.map(user => (
          <div key={user.email}>
            <img src={"https://firebasestorage.googleapis.com/v0/b/auth-development-15ccf.appspot.com/o/" + user.email + ".png?alt=media"}
              alt="Avatar" className="avatar" />
            {user.first} {user.last}
            <br />
            {user.state} {user.city}
            <br />
            {user.volunteer === true && <p>Volunteer</p>}
            {user.volunteer === false && <p>Organizer</p>}
            < button onClick={() => addMatch(user.email)}
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