import React,  { useState ,useEffect }  from 'react';
import EmployeeApi from '../../api/EmployeeApi';
import Header from '../Header';

const EmployeeView = () => {



    const[employeeList, setEmployeeList] = useState([])
    
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        EmployeeApi.getAll(setEmployeeList)
       
    }, [] )



    return (
        <div>
            <Header />
            <h1>Employee View Page</h1>
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
        </div>
    );
};

export default EmployeeView;