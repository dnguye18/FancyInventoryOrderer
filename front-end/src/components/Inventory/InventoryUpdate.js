import React, { useState ,useEffect } from "react";
import InventoryApi from '../../api/InventoryApi';
import Header from '../Header';
import "bootstrap/dist/css/bootstrap.min.css";
const InventoryUpdate = () => {

    const[item, setItem] =
    useState({
        id: 0,
        name: "",
        price: "",
        qty: "",
    });

    const[inventoryList, setInventoryList] = useState([])
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getAll(setInventoryList)
       
    }, [] )

    const handleChange = (event) => {
    
    }

    return(
        <div>
            <Header/>
            <h1>Update an Existing Item</h1>
            <table className='table'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Update</th>
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
                                        <button className="btn btn-primary" onChange={handleChange}>
                                            Update
                                        </button>
                                    </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    )
}


export default InventoryUpdate