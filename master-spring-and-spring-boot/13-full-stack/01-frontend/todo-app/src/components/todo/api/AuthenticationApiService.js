import axios from "axios";

export const apiClient = axios.create(
   {
      baseURL: 'http://localhost:8080'
   }
)

export const executeBasicAuthenticationService
    = (token) => apiClient.get(`/basicauth`
    ,{
        headers: {
            Authorization: token
        }
    }
    )

export const executeJwtAuthenticationService =
   (username, password) => apiClient.post(`/authenticate`, {
      username, password
   })
