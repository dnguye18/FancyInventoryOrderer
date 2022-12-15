import React from 'react';
import { Link } from 'react-router-dom';
import '../css/header.css';


const Header = () => {
    return(
        <div>
            <h1>Welcome To Fancy Inventory Orderer </h1>
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <div className="dropdown">
                    <button className="dropbtn">Inventory</button>
                    <div className="dropdown-content">
                        <Link className="nav-link" to='/inventory/view'>View</Link>
                        <Link className="nav-link" to='/inventory/add'>Add</Link>
                        <Link className="nav-link" to='/inventory/delete'>Delete</Link>
                        <Link className="nav-link" to='/inventory/update'>Update</Link>
                    </div>
                </div>

                <div className="dropdown">
                    <button className="dropbtn">Employee</button>
                    <div className="dropdown-content">
                        <Link className="nav-link" to='/employee/view'>View</Link>
                        <Link className="nav-link" to='/employee/add'>Add</Link>
                        <Link className="nav-link" to='/employee/delete'>Delete</Link>
                        <Link className="nav-link" to='/employee/update'>Update</Link>
                    </div>
                </div>

                <Link to="/">
                    <button className='signOut'>Log out</button>
                </Link>
            </nav>
        </div>
    )
}

export default Header