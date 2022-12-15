import React, { useState ,useEffect } from "react";
import EmployeeApi from '../../api/EmployeeApi';
import Header from '../Header';

const EmployeeView = () => {

    const[employeeList, setEmployeeList] = useState([])
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        EmployeeApi.getAll(setEmployeeList)
       
    }, [] )

    return(
        <div>
            <Header/>
            <h1>Viewing All Employees</h1>
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
                        employeeList.map( i =>  
                                    <tr key={i.id}>
                                        <td>{i.id}</td>
                                        <td>{i.first_name}</td>
                                        <td>{i.last_name}</td>
                                        <td>{i.phoneNum}</td>
                                    </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default EmployeeView
