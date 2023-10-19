export default ListTodosComponent

function ListTodosComponent() {

   const today = new Date()
   const targetDate = new Date(today.getFullYear()+12, today.getMonth(), today.getDay())
 
   const todos = [
     {id:1, description:'Study Kepping going', done:false, targetDate:targetDate},
     {id:2, description:'Learn AWS', done:false, targetDate:targetDate},
     {id:3, description:'Learn JSX', done:false, targetDate:targetDate}
   ]
 
   return(
     <div className='ListTodosComponent'>
       <h1>List your Things</h1>
       <div>
         <table className='table'>
           <thead>
             <tr>
               <td>Id</td>
               <td>Description</td>
               <td>IsDone?</td>
               <td>Date</td>
             </tr>
           </thead>
           <tbody>
             {
               todos.map(
                 todo => (
                   <tr key={todo.id}>
                     <td>{todo.id}</td>
                     <td>{todo.description}</td>
                     <td>{todo.done.toString()}</td>
                     <td>{todo.targetDate.toDateString()}</td>
                   </tr>
                 )
               )
             }
           </tbody>
         </table>
       </div>
     </div>
   )
 }