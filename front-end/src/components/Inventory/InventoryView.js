import React, { useState ,useEffect } from "react";
import InventoryApi from '../../api/InventoryApi';
import Header from '../Header';

const productDummyList = [
    {
        "id": 1,
        "name": "phone",
        "price": 1100.99,
        "qty": 7
    },
    {
        "id": 2,
        "name": "mug",
        "price": 11.99,
        "qty": 23
    },
    {
        "id": 3,
        "name": "book",
        "price": 23.99,
        "qty": 12
    }
]

const InventoryView = () => {

    const[inventoryList, setInventoryList] = useState([])
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getAll(setInventoryList)
       
    }, [] )

    return(
        <div>
            <Header/>
            <h1>Inventory View Page</h1>
            <table className='table'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        inventoryList.map( i =>  
                                    <tr key={i.id}>
                                        <td>{i.id}</td>
                                        <td>{i.name}</td>
                                        <td>{i.price}</td>
                                        <td>{i.qty}</td>
                                    </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default InventoryView