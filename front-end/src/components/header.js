import React from 'react';
import '../css/header.css';
import "bootstrap/dist/css/bootstrap.min.css";
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <div>
            <h1 className="display-2">Welcome </h1>
            <Link className="navbar-brand" to="/login" id='signOut' >Sign out</Link>

            <nav className="navbar navbar-expand-lg bg-light">
                <div className="container-fluid">

                    <Link className="navbar-brand" to="/login" >Home</Link>

                    <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div className="navbar-nav">

                            <Link className="nav-link" to="/inventory/view">View Inventory</Link>
                            <Link className="nav-link" to="/inventory/add">Add Item</Link>
                            <Link className="nav-link" to="/inventory/delete">Delete Item</Link>
                            <Link className="nav-link" to="/inventory/update">Update Item</Link>

                            <Link className="nav-link" to="/employee/view">View Employees</Link>
                            <Link className="nav-link" to="/employee/add">Add Employee</Link>
                            <Link className="nav-link" to="/employee/delete">Fire Employee</Link>
                            <Link className="nav-link" to="/employee/update">Update Employee</Link>
                        </div>
                    </div>
                </div>
            </nav>

        </div>
    );
};

export default Header;