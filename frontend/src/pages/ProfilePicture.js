import React, { useEffect, useState } from 'react'
import { useAuth, upload } from '../contexts/AuthContext'
import { auth } from '../firebase'


export default function ProfilePicture() {
    const currentUser = useAuth()
    const [loading, setLoading] = useState(false)
    const [photo, setPhoto] = useState(null)
    // temp placeholder
    const [photoURL, setPhotoURL] = useState("https://upload.wikimedia.org/wikipedia/commons/a/ac/Default_pfp.jpg")

    function handleChange(e) {
        if (e.target.files[0]) {
            setPhoto(e.target.files[0])
        }
    }

    function handleClick() {
        upload(photo, currentUser, setLoading)
    }


    useEffect(() => {
        console.log(auth.currentUser.photoURL)
        if (auth.currentUser?.photoURL) {
            setPhotoURL(auth.currentUser.photoURL)
            console.log(auth.currentUser.photoURL)
        }
    }, [currentUser])

    return (
        <div>
            <input type="file" onChange={handleChange} />
            <button disabled={loading || !photo} onClick={handleClick}>Upload file</button>
            <img src={photoURL} alt="Avatar" className="avatar" />
        </div>
    )
}
