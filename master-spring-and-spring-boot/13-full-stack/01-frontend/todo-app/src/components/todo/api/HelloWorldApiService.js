import {apiClient} from './apiClient'

// export function retrieveHelloWorldBean() {
//    return ( axios.get("http://localhost:8080/hello-world-bean") )
// }

export const retrieveHelloWorld = 
   // (username) => axios.get(`/hello-world/path-variable/${username}`)
   (username, token) => apiClient.get(`/hello-world/path-variable/${username}`, {
      headers: {
         Authorization: token
      }
   })

