//import {useState} from 'react';
//import {FaTrashAlt} from 'react-icons/fa'

import { useEffect, useState } from "react"

const Content = ({users}) => {

    return (
      <main>
            {users.map(user => (
              <div key={user.id}>
                <p>Name: {user.first}</p>
                <p>Phone num: {user.phoneNumber} </p>
              </div>
            ))}
      </main>
    )
  }
  
  export default Content