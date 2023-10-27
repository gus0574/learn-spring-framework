import {Link} from 'react-router-dom'
import { useAuth } from './security/AuthContext'

export default HeaderComponent

function HeaderComponent() {
  const authContext = useAuth()
  const isAuthenticated = authContext.isAuthenticated

   return(
     <header className="border-bottom border-light border-5 mb-5 p-2">
       <div className="container">
         <div className="row">
           <nav className="navbar navbar-expand-lg">
             <a className="navbar-brand ms-2 fs-2 fw-bold text-black" target="_blank" rel="noreferrer noopener" href="https://github.com/gus0574">My Git</a>
             <div className="collapse navbar-collapse">
               <ul className="navbar-nav">
                 <li className="nav-item">
                   { isAuthenticated && <Link className="nav-link" to="/welcome/Lee">Home</Link> }
                 </li>
                 <li className="nav-item">
                   { isAuthenticated && <Link className="nav-link" to="/listTodos">Todos</Link> }
                 </li>
               </ul>
             </div>
             <ul className="navbar-nav">
               <li className="nav-item">
                { !isAuthenticated && <Link className="nav-link" to="/login">Login</Link> }
                </li>
               <li className="nav-item">
                { isAuthenticated && <Link className="nav-link" to="/logout" onClick={authContext.logout}>Logout</Link>}
               </li>
             </ul>
           </nav>
         </div>
       </div>
       <hr />
     </header>
   )
 }