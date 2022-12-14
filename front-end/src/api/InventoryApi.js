const BASE = "http://0.0.0.0:8080" // edit this with your AWS endpoint
const URI = BASE + "/api"

const InventoryApi = {
    
    getAll: (setInventoryList) => {

        fetch(URI + "/inventory")
            .then( result => result.json() )
            .then( data => {
                setInventoryList(data)
            } )
            .catch( error => { console.log(error) } )
    },

    add: (inventory) => {
        
        fetch(URI + "/add/inventory", {
            method: "POST",
            body: JSON.stringify(inventory),
            headers: { "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {

                if(typeof data.id !== 'undefined') {

                    console.log("CREATED INVENTORY:");
                    console.log(data);
                    
                    alert(`INVENTORY CREATED \n` +
                        `------------------------\n` + 
                        `ID: ${data.id}\n` +
                        `Item Name: ${data.name}\n` +
                        `Price: ${data.price}\n` +
                        `Quantity: ${data.quantity}\n` 
                    )
                }
                else {
                    alert("Inventory can't be created, check that you are not using an email already in use by another inventory.")
                }

            } )
            .catch( error => { 
                console.log(error);
            } )

    },

    update: (inventory, inventoryList, setInventoryList) => {

        fetch(URI + "/update/inventory",  {
            method: 'PUT',
            body: JSON.stringify(inventory),
            headers: {  "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {

                if(typeof data.id !== 'undefined') {

                    console.log("UPDATED:");
                    console.log(data);

                    const newList = [...inventoryList];

                    let index = -1;

                    for(let i = 0; i < newList.length; i++) {

                        if( newList[i].id === data.id ) {
                            index = i;
                            break;
                        }
                    }

                    newList.splice(index, 1, data)

                    setInventoryList(newList)
                }
                else {
                    alert("Error updating inventory, email choosen may already be in use by another inventory")
                }
                
            } )
            .catch(error => { console.log(error); })

    },

    delete: (id) => {

        fetch(URI + "/delete/inventory/" + id, {
            method: "DELETE"
        })
            .then( result => result.json() )
            .then( data => {
                console.log("DELETED:");
                console.log(data);
            } )
            .catch(error => { console.log(error); })

    }
}

export default InventoryApi;