import React, { useState } from "react";
import '../css/landingpage.css';
import "bootstrap/dist/css/bootstrap.min.css";
import EmployeeApi from '../api/EmployeeApi';

const LandingPage = () => {
    const[user, setUser] =
    useState({
        id: 0,
        username: "",
        first_name: "",
        last_name: "",
        phone: "",
        password: "",
        role: "ROLE_USER",
        orders: [],
        enabled: true

    });
   



const handleSubmit = (event) => {

    EmployeeApi.getUser(user)
    //Use EmployeeApi to get the password
    //EmployeeApi.getPassword(password)


    event.preventDefault()
}

const handleChange = (event) => {
    setUser({
        ...user,
        [event.target.name]: event.target.value
    })

    //set Password here
}

    return(
<<<<<<< HEAD
        
            <div className="container">
                <p className="welcome">Fancy Inventory Orderer</p>
                <img src="https://www.hanaretail.com/wp-content/uploads/2022/03/2-2.png" alt="loginPic"/>
                <div className="loginContainer">
                    <br></br>
                    <input type="text" className="form__field" placeholder="Username" name="Username" id='Username' required />
                    
                    <br></br>
                    <input type="password" className="form__field" placeholder="Password" name="Password" id='Password' required />
=======
            <div className="container">
                <p className="welcome">Fancy Inventory Orderer</p>
                <img src="https://www.hanaretail.com/wp-content/uploads/2022/03/2-2.png" alt="loginPic"/>
                    <div className="loginContainer">
                        <form onSubmit={ handleSubmit } >
>>>>>>> vincent-front-end

                            <br></br>
                            <input type="text" class="form__field" placeholder="Username" name="Username" id='Username' onChange={ handleChange } required />
                            
                            <br></br>
                            <input type="password" class="form__field" placeholder="Password" name="Password" id='Password' onChange={ handleChange } required />

                            <p></p>

                            
                            <button type="submit" className="signIn" value="Submit">
                                Sign in
                            </button>
                        </form>
                        </div>
                        <div className="loginContainer">
                            <p>Dont have an account?</p>
                            <a href ="/create_profile">
                                <button className="create">Create Account</button>
                            </a>
                        </div>
            </div>
<<<<<<< HEAD
        
=======
>>>>>>> vincent-front-end
    )
}

export default LandingPage