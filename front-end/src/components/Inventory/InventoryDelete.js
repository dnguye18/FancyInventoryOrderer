import React, { useState ,useEffect } from "react";
import InventoryApi from '../../api/InventoryApi';
import Header from '../Header';
import "bootstrap/dist/css/bootstrap.min.css";



const InventoryDelete = () => {
    const[inventoryList, setInventoryList] = useState([])
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getAll(setInventoryList)
       
    }, [] )

    /*const handleSubmit = (event, id) => {

        InventoryApi.delete(id)


        event.preventDefault()
    }*/


    return(
        <div>
            <Header/>
            <h1>Getting Rid of an Item</h1>
            <table className='table'>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Remove</th>
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
                                        <td><button className="btn btn-danger" onClick={ InventoryApi.delete(i.id) } >
                                                Delete
                                        </button>
                                        </td>
                                    </tr>
                            )
                    }
                </tbody>
            </table>
        </div>
    )
}

export default InventoryDelete