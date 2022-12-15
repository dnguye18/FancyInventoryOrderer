import React, { useState } from 'react';
import EmployeeApi from '../api/EmployeeApi';
import '../css/createAccount.css';

const CreateAccount = () => {

        const[user, setUser] =
        useState({
            id: 0,
            username: "",
            first_name: "",
            last_name: "",
            phone: "",
            password: "",
            role: "ROLE_USER",
            items: [],
            enabled: true

        });
       



    const handleSubmit = (event) => {

        EmployeeApi.add(user)


        event.preventDefault()
    }

    const handleChange = (event) => {
        setUser({
            ...user,
            [event.target.name]: event.target.value
        })
    }

    
    
        return (
            <div className='body'>
                <h2  className="display-4">Create Account</h2>
                <img src= "https://support.ptc.com/images/cs/portal/2012b2b/Create_a_New_Account.png" alt='Create Page' style={{ width: "10% ", padding:"10px", display: "grid", margin: "auto"}} />

                <form onSubmit={ handleSubmit } >
                    <div className='mb-3'>
                        <input placeholder="Email" type="text" id="user-email" name="username" value={user.username} onChange={ handleChange }/>  
                    </div>
                    <div className='mb-3' style={{textAlign: "center"}}>
                        <input placeholder="First Name" type="text" id="user-first_name" name="first_name" value={user.first_name} onChange={ handleChange } />
                    </div>
                    <div className='mb-3' style={{textAlign: "center"}}>
                        <input placeholder="Last Name" type="text" id="user-last_name" name="last_name" value={user.last_name} onChange={ handleChange } />
                    </div>
                    <div className='mb-3' style={{textAlign: "center"}}>
                        <input placeholder="Mobile Phone Number (Optional)" id="user-phone" type="text" name="phone" value={user.phone} onChange={ handleChange } />
                    </div>
                    <div className='mb-3' style={{textAlign: "center"}}>
                        <input placeholder="Create Password" type="text" id="user-password" name="password" value={user.password} onChange={ handleChange }/>
                    </div>
                    <button  type="submit" className="btn btn-primary" value="Submit">
                        Create Account
                    </button> 
                    
                </form>  
            </div>
        );
    
};

export default CreateAccount;