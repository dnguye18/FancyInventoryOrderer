import React from "react";
import { Route,Routes } from "react-router";
import Header from '../components/Header';
import InventoryView from '../components/Inventory/InventoryView';

const signedInPage = () => {
    return(
        <div>
            <Header/>
            <Routes>
                <Route path='/inventory/view' element={<InventoryView />} />
            </Routes>

        </div>
    )
}


export default signedInPage
