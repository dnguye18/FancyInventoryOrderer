import React from 'react';
import { Link } from 'react-router-dom';
import '../css/header.css';


const Header = () => {
    return(
        <div>
            <h1>Welcome To Fancy Inventory Orderer </h1>
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="dropdown">
                    <button class="dropbtn">Inventory</button>
                    <div class="dropdown-content">
                        <Link className="nav-link" to='/inventory/view'>View</Link>
                        <Link to="/">Add</Link>
                        <Link to="/">Delete</Link>
                        <Link to="/">Update</Link>
                    </div>
                </div>

                <div class="dropdown">
                    <button class="dropbtn">Employee</button>
                    <div class="dropdown-content">
                        <Link to="/">View</Link>
                        <Link to="/">Add</Link>
                        <Link to="/">Delete</Link>
                        <Link to="/">Update</Link>
                    </div>
                </div>

                <Link to="/" class="btn btn-info btn-lg">
                    <span class="glyphicon glyphicon-log-out">Log out</span>
                </Link>
            </nav>
        </div>
    )
}

export default Header