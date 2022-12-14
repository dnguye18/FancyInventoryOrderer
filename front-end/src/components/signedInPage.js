import React from "react";
import { Routes, Route } from 'react-router-dom';
import Header from './Header';
import InventoryView from './Inventory/InventoryView';

const SignedInPage = () => {
    return(
        <div>
        <Header />
            <Routes>
                <Route path='/inventory/view' element={ <InventoryView /> } />
            </Routes>
        </div>
    )
}


export default SignedInPage
