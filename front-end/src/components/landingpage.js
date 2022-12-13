import React from "react";
import '../css/landingpage.css';
import "bootstrap/dist/css/bootstrap.min.css";

const LandingPage = () => {
    return(
        <body>
            <div className="container">
                <p className="welcome">Fancy Inventory Orderer</p>
                <img src="https://www.hanaretail.com/wp-content/uploads/2022/03/2-2.png" alt="loginPic"/>
                <div className="loginContainer">
                    <br></br>
                    <input type="text" class="form__field" placeholder="Username" name="Username" id='Username' required />
                    
                    <br></br>
                    <input type="password" class="form__field" placeholder="Password" name="Password" id='Password' required />

                    <p></p>

                    <button className="signIn">
                        Sign in
                    </button>

                    <p>Dont have an account?</p>
                    <a href ="/create_profile">
                        <button className="create">Create Account</button>
                    </a>
                </div>
            </div>
        </body>
    )
}

export default LandingPage