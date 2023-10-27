import { useEffect, useState } from "react"
import { deleteTodoApi, retrieveAllTodosForUserApi } from "./api/TodoApiService"
import { useAuth } from "./security/AuthContext"
import { useNavigate } from "react-router-dom"

export default ListTodosComponent

function ListTodosComponent() {

  const today = new Date()
  const targetDate = new Date(today.getFullYear() + 12, today.getMonth(), today.getDay())

  //  const todos = [
  //    {id:1, description:'Study Kepping going', done:false, targetDate:targetDate},
  //    {id:2, description:'Learn AWS', done:false, targetDate:targetDate},
  //    {id:3, description:'Learn JSX', done:false, targetDate:targetDate}
  //  ]

  const [todos, setTodos] = useState([])

  const [message, setMessage] = useState()

  useEffect(
    () => refreshTodos(), []
  )

  const authContext = useAuth()

  const username = authContext.username

  const navigate = useNavigate()

  function refreshTodos() {
    retrieveAllTodosForUserApi(username)
      .then(
        (response) => { 
          // console.log(response.data) 
          setTodos(response.data)
        }
      )
      .catch(
        (error) => console.log(error)
      )
  }
  
  function deleteTodo(id) {
    console.log("delete number is " + id)
    deleteTodoApi(username, id)
    .then(
      () => {
        refreshTodos()
        setMessage(`${username}! Delete is Success For ID : ${id}`)
      }
    )
    .catch(
      (error) => console.log(error)
    )

  }
  
  function createTodo() {
    console.log("Create Todo...")

    navigate(`/todo/-1`)
  }

  function updateTodo(id) {
    console.log("update number is " + id)

    navigate(`/todo/${id}`)
  }

  return (
    <div className='ListTodosComponent'>
      <h1>List your Things</h1>
      {message && <div className="alert alert-warning">{message}</div>}
      <div>
        <table className='table'>
          <thead>
            <tr>
              <th>Id</th>
              <th>Description</th>
              <th>IsDone?</th>
              <th>Date</th>
              <th>Update</th>
              <th>Delete</th>
            </tr>
          </thead>
          <tbody>
            {
              todos.map(
                (todo) => (
                  <tr key={todo.id}>
                    <td>{todo.id}</td>
                    <td>{todo.description}</td>
                    <td>{todo.done.toString()}</td>
                    {/* <td>{todo.targetDate.toDateString()}</td> */}
                    <td>{todo.targetDate.toString()}</td>
                    <td> <button className="btn btn-success" onClick={ () => updateTodo(todo.id) }>Update</button> </td>
                    <td> <button className="btn btn-warning" onClick={ () => deleteTodo(todo.id) }>Delete</button> </td>
                  </tr>
                )
              )
            }
          </tbody>
        </table>
        <div className="btn btn-success m-4" onClick={() => createTodo()}>add Todo</div>
      </div>
    </div>
  )
}