import { useNavigate, useParams } from "react-router-dom"
import { createTodoApi, retrieveTodoApi, updateTodoApi } from "./api/TodoApiService"
import { useEffect, useState } from "react"
import { useAuth } from "./security/AuthContext"
import { Formik, Form, Field, ErrorMessage } from 'formik'
import moment from 'moment'


export default TodoComponent

function TodoComponent() {
   const params = useParams()
   const id = params.id

   const authContext = useAuth()
   const username = authContext.username

   const [description, setDescription] = useState('')
   const [targetDate, setTargetDate] = useState('')

   const navigate = useNavigate()

   useEffect(
      () => {retrieveTodo()},
      [id]
   )

   function retrieveTodo() {
      if(id !== -1) {
      retrieveTodoApi(username, id)
         .then((response) => {
            console.log(response)
            setDescription(response.data.description)
            setTargetDate(response.data.targetDate)
         })
         .catch((error) => console.log(error))
      }
   }

   function onSubmit(values) {
      const todo = {
         id: id,
         username: username,
         description: values.description,
         targetDate: values.targetDate,
         done: false
      }

      if(id===-1) {
         createTodoApi(username, todo)
         .then(
            (response) => { navigate('/listTodos') }
         )
         .catch(error => console.log(error))
      } else {
         updateTodoApi(username, id, todo)
         .then(response => {
            navigate('/listTodos')
         })
         .catch( error => console.log(error) )
      }
   }

   function onValidate(values) {
      let errors = {}

      if (values.description.length < 5) {
         errors.description = 'Description length should be at least 5'
      }

      if (values.targetDate === null || values.targetDate === '' || !moment(values.targetDate.isValid)) {
         errors.targetDate = 'Date must be not null'
      }

      return errors
   }

   return (
      <div className="container">
         <h1>Enter Todo Info</h1>
         <div>
            <Formik initialValues={{ description, targetDate }}
               enableReinitialize={true} onSubmit={onSubmit}
               validate={onValidate} validateOnChange={false} validateOnBlur={false}>
               {
                  (props) => (
                     <Form>
                        <fieldset className="form-group">
                           <label>Description</label>
                           <ErrorMessage name="description" component="div" className="alert alert-warning" />
                           <Field type="text" className="form-control" name="description"></Field>
                        </fieldset>
                        <fieldset className="form-group">
                           <label>Target Date</label>
                           <ErrorMessage name="targetDate" component="div" className="alert alert-warning" />
                           <Field type="date" className="form-control" name="targetDate"></Field>
                        </fieldset>
                        <div>
                           <button type="submit" className="btn btn-success m-5">Confirm</button>
                        </div>
                     </Form>
                  )
               }
            </Formik>
         </div>
      </div>
   )
}