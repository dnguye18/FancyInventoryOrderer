import React, { useState ,useEffect } from "react";
import InventoryApi from '../../api/InventoryApi';
import Header from '../Header';
import "bootstrap/dist/css/bootstrap.min.css";



const InventoryDelete = () => {

    const [ id, setId ] = useState(0)
    const[inventoryList, setInventoryList] = useState([])
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getAll(setInventoryList)
       
    }, [] )

    const handleChange = (event) => {
        setId({
            ...id,
            [event.target.id]: event.target.value
        })
    }

    const handleSubmit = (event) => {

        InventoryApi.delete(document.getElementById("id").value)
        if( typeof document.getElementById("id").value !== 'undefined' ) {
            alert('ITEM Deleted!')
        } 


        event.preventDefault()
    }


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
        
            <form onSubmit={ handleSubmit } >
                <div className='mb-3'>
                    <input placeholder="What Item ID to Delete?" type="text" name="id" id="id" onChange={ handleChange }/>
                </div>
            <button className="btn btn-danger" onClick={handleSubmit} >
                Delete
            </button>
            </form>
        </div>
    )
}

export default InventoryDelete