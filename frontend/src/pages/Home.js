import UserForm from "../UserForm";

export default function Home ({API_URL}) {
    return (
        <main>
            <h1>Home</h1>
            <UserForm API_URL={API_URL}/>
        </main>
    )
}