import React, { useEffect } from "react"
import { useState } from "react"
import { FaPlus } from 'react-icons/fa'

export default function Matches ({ API_URL }) {
    const [users, setUsers] = useState([])

    useEffect (() => {
        let ignore = false
        console.log(API_URL)
        const fetchUsers = async () => {
            try {
              const response = await fetch(API_URL)
              const userList = await response.json()
              console.log(userList)
              setUsers(userList)
            } catch (err) {
              console.log(err.stack)
            }
          }

        if (!ignore) fetchUsers()
        return () => { ignore = true}
    }, [API_URL])

    const addMatch = (phone) => {
      console.log(phone)
    }

    return (
    <main>
        <h1>Matches</h1>
        <div>
            {users.map(user => (
                <div key={user.id}>{user.first} {user.last}
                  <button onClick={() => addMatch(user.phoneNumber)}
                    title='Match'
                    type='submit'
                    aria-label='Enter Name'
                  ><FaPlus/></button>
                </div>
                
            ))}
        </div>


    </main>)
    
}