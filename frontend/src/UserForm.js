import React from 'react'
import { useState } from 'react'
import { FaPlus } from 'react-icons/fa'

const UserForm = ({ API_URL }) => {
  const [name, setName] = useState('')
  const [number, setNumber] = useState('')

  const phoneNumber = number
  const first = name
  // for obj passed into POST, needs fields to match obj in backend exactly
  const user = {phoneNumber, first};

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!name) return
    if (!number) return
    console.log(name)
    console.log(number)
    console.log("User obj")
    console.log(user)
    console.log(JSON.stringify(user))
    try {
      fetch(API_URL, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(user)
      })
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
    }
    // setting the state of the input box back to empty
    setName('')
    setNumber('')
  }
  
  return (
    // event is implicitly passed with onSubmit
    <form className='userForm' onSubmit={handleSubmit}>
        <label htmlFor='enterName'>Enter Name</label>
        <input
            autoFocus
            id='enterName'
            type='text'
            placeholder='Enter first name'
            required
            value={name}
            // to change state, on event it sets setNewItem to the value
            onChange={(e) => setName(e.target.value)}
        />
        <input
          autoFocus
          id='enterPhoneNumber'
          type='text'
          placeholder='Enter phone #'
          required
          value={number}
          onChange={(e) => setNumber(e.target.value)}
        />
        <button
            type='submit'
            aria-label='Enter Name'
        >
            <FaPlus/>
        </button>

    </form>
  )
}

export default UserForm
