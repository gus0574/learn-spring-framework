import {useParams, Link} from 'react-router-dom'

export default WelcomeComponent

function WelcomeComponent() {

   const params = useParams()
   console.log(params.username)
 
   const {username} = useParams()
   console.log(username)
 
   return(
     <div className="WelcomeComponent">
       <h1>Welcome to visit, {username}</h1>
       <div>Your Todos is <Link to='/listTodos'>Go Here</Link></div>
     </div>
   )
 }