import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
    return (
        <header>
            <h1 className="display-2">Welcome To Fancy Inventory Orderer</h1>

            <nav className='navbar navbar-expand-lg bg-light'>
                <div className='container-fluid'>
                    
                    <Link className='navbar-brand' to='/'>Home</Link>

                    <button className='navbar-toggler' 
                            type='button' 
                            data-bs-toggle='collapse' data-bs-target='#navbarNavAltMarkup' aria-controls='navbarNavAltMarkup' aria-expanded='false' 
                            aria-label='Toggle navigation' >
                        <span className='navbar-toggler-icon'></span>
                    </button>
                    
                    <div className='collapse navbar-collapse'
                         id='navbarNavAltMarkup'>
                        <div className='navbar-nav'>

                            <Link className='nav-link' to='/view'>View</Link>

                            <Link className='nav-link' to='/add'>Add</Link>

                        </div>
                    </div>

                </div>
            </nav>

            <div style={{height: "1.5rem"}}></div>

        </header>
    );
};

export default Header;