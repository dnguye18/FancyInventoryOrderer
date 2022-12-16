import React, {useState, useEffect} from 'react';
import EmployeeApi from '../../api/EmployeeApi'
import Header from '../Header';


const EmployeeDelete = () => {

    const [ id, setId ] = useState(0)
    const[employeeList, setEmployeeList] = useState([])
    

    // Retrieves all employees in EmployeeList
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        EmployeeApi.getAll(setEmployeeList)

    }, [] )

    // Sets the id to what user has inputted
    const handleChange = (event) => {
        setId({
            ...id,
            [event.target.id]: event.target.value
        })
    }

    // Deletes the Employee by their Id
    const handleSubmit = (event) => {

        EmployeeApi.delete(document.getElementById("id").value)
        alert("Employee has been deleted")

        event.preventDefault()
    }
    
    
    return (
        <div className='body'>
            <Header/>
            <h2 className="display-4">Remove an Employee</h2>
            <table className='table'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone Number</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        employeeList.map( e =>  
                            <tr key={e.id}>
                                <td>{e.id}</td>
                                <td>{e.first_name}</td>
                                <td>{e.last_name}</td>
                                <td>{e.phone}</td>
                            </tr>
                        )
                    }
                </tbody>
            </table> 
            <form onSubmit={ handleSubmit } >
                <div className='mb-3'>
                    <input placeholder="What Item ID to Delete?" type="text" name="id" id="id" onChange={ handleChange }/>
                </div>
                <button type="delete" className='btn btn-danger' value="Delete" onClick={handleSubmit}>Delete Employee</button>
            </form>
        </div>
            
            
        
    );
    
};

export default EmployeeDelete;