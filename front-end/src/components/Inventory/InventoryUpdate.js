import React, { useState ,useEffect } from "react";
import InventoryApi from '../../api/InventoryApi';
import Header from '../Header';
import "bootstrap/dist/css/bootstrap.min.css";


const InventoryUpdate = () => {

    const [ id, setId ] = useState(0)
    const[ name, setName ] = useState("")
    const[ price, setPrice ] = useState(0)
    const[ qty, setQty ] = useState(0)
    const[ inventoryList, setInventoryList] = useState([])


    // Checks the item's id, name price and quantity
    const handleSubmit = (event) => { 

        const item = {
            "id" : id,
            "name": name,
            "price": price,
            "qty": qty
        }

        // make a POST request here to create the product
        InventoryApi.update(item, inventoryList, setInventoryList)


        // stop the page from refreshing/reloading when submitting the form
        event.preventDefault()
    }
    

    // Gets all of the inventory items from InventoryList
    useEffect( () => {
        console.log("Hello, this component was mounted!")
        InventoryApi.getAll(setInventoryList)
       
    }, [] )


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
            

            <form onSubmit={ handleSubmit }>
            <div className='mb-3'>
                    <label htmlFor='prod-id' className='form-label' >
                        ID
                    </label>
                    <input type="number"
                           className='form-control'
                           id='prod-id'
                           required
                           min="1"
                           step="1"
                           name="prod-id"
                           value={id}
                           onChange={ (event) => setId(event.target.value) } 
                           />
                </div>

                <div className='mb-3'>
                    <label htmlFor='prod-name' className='form-label' >
                        Item Name
                    </label>
                    <input type="text"
                           className='form-control'
                           id='prod-name'
                           required
                           name="prod-name"
                           value={name}
                           onChange={ (event) => { setName(event.target.value) } }
                        />
                </div>

                <div className='mb-3'>
                    <label htmlFor='prod-price' className='form-label' >
                        Price
                    </label>
                    <input type="number"
                           className='form-control'
                           id='prod-price'
                           required
                           min="0"
                           step="0.01" 
                           name="prod-price"
                           value={price}
                           onChange={ (event) => { setPrice(event.target.value) } }
                           />
                </div>

                <div className='mb-3'>
                    
                    <label htmlFor='prod-qty' className='form-label' >
                        Quantity
                    </label>
                    <input type="number"
                           className='form-control'
                           id='prod-qty'
                           required
                           min="1"
                           step="1"
                           name="prod-qty"
                           value={qty}
                           onChange={ (event) => setQty(event.target.value) } 
                           />
                </div>

                <button type="submit" className="btn btn-primary">
                    Update
                </button>
            </form>

        </div>
    )
}


export default InventoryUpdate