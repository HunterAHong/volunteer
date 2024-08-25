import { useEffect, useState } from "react"
import { addDoc, collection, onSnapshot, orderBy, query, serverTimestamp, where } from "firebase/firestore"
import { auth, db } from "../firebase"
import { useAuth } from '../contexts/AuthContext'
import "../css/chat.css"

export default function Events(props) {
    //const { room } = props
    const { currentUser } = useAuth()
    const [room, setRoom] = useState(currentUser.email)
    const [user, setUser] = useState(null)
    const [isVolunteer, setIsVolunteer] = useState(null)
    const [newMessage, setNewMessage] = useState("")
    const [messages, setMessages] = useState([])
    const messagesRef = collection(db, "messages")

    useEffect(() => {
        const getUser = async () => {
            try {
                const response = await fetch("http://localhost:8080/api/v1/users/" + currentUser.email)
                const user = await response.json()
                setUser(user)
            } catch (err) {
                console.log(err.stack)
            }
        }

        getUser()
    }, [currentUser.email])

    useEffect(() => {
        if (user) {
            if (user.volunteer) {
                console.log("user is volunteer")
                setIsVolunteer(true)
                if (user.matches) {
                    setRoom(user.matches[0].email)
                }
            } else {
                console.log("user is organizer")
                setIsVolunteer(false)
                setRoom(currentUser.email)
            }
        }
    }, [user, currentUser.email])

    useEffect(() => {
        const queryMessages = query(
            messagesRef,
            where("room", "==", room),
            orderBy("createdAt")
        )
        const unsuscribe = onSnapshot(queryMessages, (snapshot) => {
            let messages = []
            snapshot.forEach((doc) => {
                messages.push({ ...doc.data(), id: doc.id })
            })
            setMessages(messages)
        })

        return () => unsuscribe()
    }, [messagesRef, room])

    const handleSubmit = async (e) => {
        e.preventDefault()
        if (newMessage === "") return

        await addDoc(messagesRef, {
            text: newMessage,
            createdAt: serverTimestamp(),
            user: auth.currentUser.displayName,
            room
        })

        setNewMessage("")
    }

    return (
        <div className="chat-app">
            <div>
                {isVolunteer ? <h2>Volunteer mode</h2> : <h2>Organizer mode</h2>}
            </div>
            <div className="header">
                <h1> Welcome to: {room}</h1>
            </div>
            <div className="messages">
                {messages.map((message) => (
                    <div className="message" key={message.id}>
                        <span className="user">{message.user}</span>
                        {message.text}
                    </div>))}
            </div>
            <form onSubmit={handleSubmit} className="new-message-form">
                <input
                    className="new-message-input"
                    placeholder="Type your message here..."
                    onChange={(e) => setNewMessage(e.target.value)}
                    value={newMessage}
                />

                <button type="submit" className="send-button">
                    Send
                </button>
            </form>
        </div>
    )
}