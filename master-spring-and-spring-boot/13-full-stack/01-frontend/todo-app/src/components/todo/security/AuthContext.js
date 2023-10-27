import { createContext, useContext, useState } from "react"
import { executeBasicAuthenticationService } from "../api/HelloWorldApiService"
import { apiClient, executeJwtAuthenticationService } from "../api/AuthenticationApiService.js"

export default AuthProvider

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

function AuthProvider({ children }) {

   const [isAuthenticated, setAuthenticated] = useState(false)

   const [username, setUsername] = useState(null)

   const [token, setToken] = useState(null)

   // async function login(username, password) {
   //    const basicToken = 'Basic ' + window.btoa(username + ':' + password)

   //    try {
   //       const response = await executeBasicAuthenticationService(basicToken)
   //       if (response.status === 200) {
   //          setAuthenticated(true)
   //          setUsername(username)
   //          setToken(basicToken)

   //          apiClient.interceptors.request.use(
   //             (config) => {
   //                config.headers.Authorization = basicToken
   //                console.log("intercepting and adding Token")
   //                return (config)
   //             }
   //          )

   //          return (true)
   //       } else {
   //          logout()
   //          return (false)
   //       }
   //    } catch (error) {
   //       logout()
   //       return (false)
   //    }
   // }

   async function login(username, password) {
      try {
         const response = await executeJwtAuthenticationService(username, password)

         if (response.status === 200) {

            const jwtToken = 'Bearer ' + response.data.token

            setAuthenticated(true)
            setUsername(username)
            setToken(jwtToken)
            
            apiClient.interceptors.request.use(
               (config) => {
                  config.headers.Authorization = jwtToken
                  console.log("intercepting and adding jwtToken")
                  return (config)
               }
            )
            
            return (true)
         } else {
            logout()
            return (false)
         }
      } catch (error) {
         logout()
         return (false)
      }
   }

   function logout() {
      setAuthenticated(false)
      setUsername(null)
      setToken(null)
   }

   //value로 객체{number, authenticate, setAuthenticate}를 넘긴다 
   return (
      <AuthContext.Provider value={{ isAuthenticated, username, token, login, logout }}>
         {children}
      </AuthContext.Provider>
   )
}