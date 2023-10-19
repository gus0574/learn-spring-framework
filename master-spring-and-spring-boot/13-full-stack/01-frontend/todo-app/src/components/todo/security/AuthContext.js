import { createContext, useContext, useState } from "react"

export default AuthProvider

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

function AuthProvider( {children}) {

   const [isAuthenticated, setAuthenticated] = useState(false)

   function login(username, password) {
      if(username==='Lee' && password=='dummy'){
         setAuthenticated(true)
         return (true)
        } else {
         setAuthenticated(false)
         return (false)
        }
   }

   function logout() {
      setAuthenticated(false)
    }

   //value로 객체{number, authenticate, setAuthenticate}를 넘긴다 
   return(
      <AuthContext.Provider value= { {isAuthenticated, login, logout} }> 
         {children}
      </AuthContext.Provider>
   )
}