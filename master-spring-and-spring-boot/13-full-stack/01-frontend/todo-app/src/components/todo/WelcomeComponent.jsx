import { useParams, Link } from 'react-router-dom'
import { useState } from 'react'
import { retrieveHelloWorld } from './api/HelloWorldApiService'
import { useAuth } from './security/AuthContext'

export default WelcomeComponent

function WelcomeComponent() {
  //  const params = useParams()
  //  console.log(params.username)

  const { username } = useParams()
  // console.log(username)

  const [message, setMessage] = useState(null)

  const authContext = useAuth()

  function callHelloWorldRestApi(username) {
    console.log("called")

    retrieveHelloWorld(username, authContext.token)
      .then((response) => {successfulResponse(response)})
      .catch((error) => {errorResponse(error)})
      .finally(console.log('cleanUp'))
  }

  function successfulResponse(response) {
    // console.log(response)
    setMessage(response.data.message)
  }

  function errorResponse(error) {
    console.log(error)
  }

  return (
    <div className="WelcomeComponent">
      <h1>Welcome to visit, {username}</h1>
      <div>Your Todos is <Link to='/listTodos'>Go Here</Link></div>
      <div>
        <button className="btn btn-success m-5" onClick={() => callHelloWorldRestApi(username)}>call HelloWorld</button>
      </div>
      <div className="text-info">{message}</div>
    </div>
  )
}