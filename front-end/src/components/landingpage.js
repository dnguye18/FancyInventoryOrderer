import React, { useEffect, useState } from "react";
import '../css/landingpage.css';
import "bootstrap/dist/css/bootstrap.min.css";
import EmployeeApi from '../api/EmployeeApi';
import {  useNavigate } from "react-router-dom";

const LandingPage = (props) => {
    const navigate = useNavigate()

    const[employeeList, setEmployeeList] = useState([])
    const[jwt, setJwt] = useState("");
    const[user, setUser] = useState({})

    useEffect( () => {
        console.log("Hello, this component was mounted!")
        EmployeeApi.getAll(setEmployeeList)
    }, [] )

async function authenticateLogin() {
    await fetch("http://localhost:8080/authenticate", {
        method: "POST",
        body: JSON.stringify({
            username: user.username,
            password: user.password
        }),
        headers: { "Content-Type": "application/json" }
    })
        .then( async response => {
            return await response.json()

        })
        .then( async data => {
            setJwt("Bearer " + await data.jwt);
        })

        .catch( error => console.log(error) ) 
}

const handleSubmit = (event) => {
    event.preventDefault()

    employeeList.forEach( (e) => {
        if (e.username == user.username) {
            //while(jwt == 'Bearer ') {
                //console.log("LOOPING")
                authenticateLogin();
            //}
            if( jwt !== ""  && jwt !== "Bearer undefined") {
                navigate("/logged_in")
            }

            console.log("JWT STATE")
            console.log(jwt)
        }
    })
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
                            <input type="text" class="form__field" placeholder="Username" name="username" id='username' value={user.username}onChange={ handleChange } required />
                            
                            <br></br>
                            <input type="password" class="form__field" placeholder="Password" name="password" id='password' value={user.password} onChange={ handleChange } required />

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