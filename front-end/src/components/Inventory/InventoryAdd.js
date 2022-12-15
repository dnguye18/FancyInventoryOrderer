import React, { useState } from 'react';
import InventoryApi from '../../api/InventoryApi';

const InventoryAdd = () => {

    const [ id, setId ] = useState(0)
    const[ name, setName ] = useState("")
    const[ price, setPrice ] = useState(0)
    const[ qty, setQty ] = useState(0)

    const handleSubmit = (event) => { 

        const item = {
            "name": name,
            "price": price,
            "qty": qty
        }

        // make a POST request here to create the product
        InventoryApi.add(item, setId)

       


        // stop the page from refreshing/reloading when submitting the form
        event.preventDefault()
    }

    return (
        <div>
            <h1>Create Item Page</h1>

            <form onSubmit={ handleSubmit }>
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
                    Create
                </button>
            </form>
        </div>
    );
};

export default InventoryAdd;