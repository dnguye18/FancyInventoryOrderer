import React, {useState, useEffect} from 'react';
import EmployeeApi from '../../api/EmployeeApi'
import Header from '../Header';


const EmployeeDelete = () => {

    const[employeeList, setEmployeeList] = useState([])
    
    const[employee] = useState({
        
    })

    useEffect( () => {
        console.log("Hello, this component was mounted!")
        EmployeeApi.getAll(setEmployeeList)

    }, [] )

    const handleSubmit = (event) => {

        EmployeeApi.delete()
        alert("Employee has been deleted")

        event.preventDefault()
    }
    
    
    return (
        <div className='body'>
            <Header/>
            <h2 className="display-4">Delete Page</h2>
            <table className='table'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Phone Number</th>
                        <th>Delete Employee</th>
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
                                <td><button type="delete" className='btn btn-danger' value="Delete" onClick={handleSubmit}>Delete Employee</button></td>
                            </tr>
                        )
                    }
                </tbody>
            </table> 
        </div>
            
            
        
    );
    
};

export default EmployeeDelete;