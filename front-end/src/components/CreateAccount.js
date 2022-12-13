import React, { useState } from 'react';
import '../css/createAccount.css';

const CreateAccount = () => {

        const[email, setEmail] = useState("")
        const[first_name, setFirst_name] = useState("")
        const[last_name, setLast_name] = useState("")
        const[phone_number, setPhone_number] = useState("")
        const[password, setPassword] = useState("")



    const handleSubmit = (event) => {
        const acc = {
            "email": email,
            "first_name": first_name,
            "last_name": last_name,
            "phone_number": phone_number,
            "password": password
        }


        event.preventDefault()
    }

    
    
        return (
            <div className='body'>
                <h2  className="display-4">Create Account</h2>
                <img src= "https://support.ptc.com/images/cs/portal/2012b2b/Create_a_New_Account.png" alt='Create Page' style={{ width: "10% ", padding:"10px", display: "grid", margin: "auto"}} />

                <form onSubmit={ handleSubmit } >
                    <div className='mb-3'>
                        <input placeholder="Email" type="text" name="email" value={email} onChange={ (event) => { setEmail(event.target.value) } }/>  
                    </div>
                    <div className='mb-3'>
                        <input placeholder="First Name" type="text" name="first_name" value={first_name} onChange={ (event) => { setFirst_name(event.target.value) } } />
                    </div>
                    <div className='mb-3'>
                        <input placeholder="Last Name" type="text" name="last_name" value={last_name} onChange={ (event) => { setLast_name(event.target.value) } } />
                    </div>
                    <div className='mb-3'>
                        <input placeholder="Mobile Phone Number (Optional)" type="text" phone_number="phone_number" value={phone_number} onChange={ (event) => { setPhone_number(event.target.value) } } />
                    </div>
                    <div className='mb-3'>
                        <input placeholder="Create Password" type="text" password="password" value={password} onChange={ (event) => { setPassword(event.target.value) } }/>
                    </div>
                    <button type="submit" className="button" value="Submit">
                        Create Account
                    </button> 
                </form>  
            </div>
        );
    
};

export default CreateAccount;