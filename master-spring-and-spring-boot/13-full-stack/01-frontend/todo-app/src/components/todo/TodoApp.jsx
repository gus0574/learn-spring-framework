import {BrowserRouter, Routes, Route, Navigate} from 'react-router-dom'
import './TodoApp.css'
import HeaderComponent from './HeaderComponent'
import FooterComponent from './FooterComponent'
import WelcomeComponent from './WelcomeComponent'
import ListTodosComponent from './ListTodosComponent'
import LoginComponent from './LoginComponent'
import LogoutComponent from './LogoutComponent'
import ErrorComponent from './ErrorComponent'
import AuthProvider, { useAuth } from './security/AuthContext'

function AuthenticatedRoute( {children} ) {
  const authContext = useAuth()

  if(authContext.isAuthenticated) {
    return children
  }
  return <Navigate to="/"></Navigate>
}

export default function TodoApp() {
  return(
    <div className="TodoApp">
      
      <AuthProvider>
        <BrowserRouter>
          <HeaderComponent />

          <Routes>
            <Route path='/' element={<LoginComponent></LoginComponent>} />
            <Route path='/welcome/:username' element={
              <AuthenticatedRoute>
                <WelcomeComponent/>
              </AuthenticatedRoute> } />
            <Route path='/listTodos' element={ <AuthenticatedRoute> <ListTodosComponent /> </AuthenticatedRoute> } />
            <Route path='/login' element={<LoginComponent></LoginComponent>} />
            <Route path='/logout' element={<LogoutComponent />} />
            <Route path='*' element={<ErrorComponent />} />
          </Routes>

          <FooterComponent />
        </BrowserRouter>
      </AuthProvider>
      
    </div>
  )
}