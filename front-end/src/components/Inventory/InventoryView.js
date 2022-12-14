import React, { useEffect, useState } from 'react';
import InventoryApi from '../../api/InventoryApi';

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
    // productList -> state variable
    // setProductList -> function that changes the value of productList
    // useState( [] ) -> helps set up state, sets state as an empty array
    const [inventoryList, setInventoryList] = useState([])
    // useEffect -> function that allows us to access the component lifecycles (rerenders, mounts on the page, unmounted from the page)
    // useEffect( function, [] ) => function will get called when the component gets mounted
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getProducts(setInventoryList)
       
    }, [] )
    return (
        <div>
            <h1>Product View Page</h1>
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
    );
};

export default InventoryView;