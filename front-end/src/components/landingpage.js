import React, { useState, useEffect } from "react";
import '../css/landingpage.css';
import "bootstrap/dist/css/bootstrap.min.css";
import EmployeeApi from '../api/EmployeeApi';
import { useNavigate } from "react-router-dom";

const LandingPage = () => {
    const navigate = useNavigate();

    const[user, setUser] =
        useState({
            id: null,
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
    
    console.log(user)
    EmployeeApi.getUserByUsername(user, setUser).finally(() => {
        navigate("/logged_in")
      })

    //event.preventDefault()
}

const handleChange = (event) => {
    setUser({
        ...user,
        [event.target.name]: event.target.value
    })
}

    return(
            <div className="container">
                <p className="welcome">Fancy Inventory Orderer</p>
                <img src="https://www.hanaretail.com/wp-content/uploads/2022/03/2-2.png" alt="loginPic"/>
                    <div className="loginContainer">
                        <form onSubmit={ handleSubmit } >

                            <br></br>
                            <input type="text" className="form__field" placeholder="Username" name="username" id='Username' value={user.username} onChange={ handleChange } required />
                            
                            <br></br>
                            <input type="password" className="form__field" placeholder="Password" name="password" id='Password' value={user.password} onChange={ handleChange } required />

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
    )
}

export default LandingPage