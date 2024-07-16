import React from 'react'
import { FaPlus } from 'react-icons/fa'

const UserForm = ({ name, setName, number, setNumber, handleSubmit }) => {
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
