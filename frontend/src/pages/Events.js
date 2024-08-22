import { useEffect, useState } from "react"
import { addDoc, collection, onSnapshot, query, serverTimestamp, where } from "firebase/firestore"
import { auth, db } from "../firebase"
import "../css/chat.css"

export default function Events(props) {
    const { room } = props
    const [newMessage, setNewMessage] = useState("")
    const [messages, setMessages] = useState([])
    const messagesRef = collection(db, "messages")

    useEffect(() => {
        const queryMessages = query(messagesRef, where("room", "==", room))
        const unsuscribe = onSnapshot(queryMessages, (snapshot) => {
            let messages = []
            snapshot.forEach((doc) => {
                messages.push({ ...doc.data(), id: doc.id })
            })
            setMessages(messages)
        })

        return () => unsuscribe()
    }, [])

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
            <div className="header">
                <h1> Welcome to: {room}</h1>
            </div>
            <div className="messages">
                {messages.map((message) => (
                    <h1> {message.text} </h1>))}
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