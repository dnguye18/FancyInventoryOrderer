const BASE = "http://localhost:8080" // edit this with your AWS endpoint
const URI = BASE + "/api"

const InventoryApi = {
    
    getAll:(setInventoryList) => {
        fetch(URI + "/item")
            .then(result => result.json())
            .then(data => {
                setInventoryList(data)
            })
            .catch(error => {console.log(error)})
    },

    add: (item,setId) =>{
        fetch(URI + "/item",{
            method: "POST",
            body: JSON.stringify(item),
            headers: {"Content-Type": "application/json"}
        })
            .then(result => result.json())
            .then(data => {
                console.log(data.id)
                if(typeof data.id !== 'undefined'){
                    console.log("ITEM CREATED");
                    console.log(data);

                    alert(`ITEM CREATED \n` +
                    `------------------------\n` + 
                    `ID: ${data.id}\n` +
                    `Item Name: ${data.name}\n` +
                    `Price: ${data.price}\n` +
                    `Quantity: ${data.qty}\n`
                    )
                }
                else{
                    alert("Item can't be created, check that an item isn't already created with that name.")
                }
            })
            .catch(error => {
                console.log(error);
            })
    },

    update:(item, InventoryList, setInventoryList) => {
        fetch(URI + "/item", {
            method: 'PUT',
            body: JSON.stringify(item),
            headers: { "Content-Type": "application/json"}
        })
            .then(result => result.json())
            .then(data => {
                if(typeof data.id !== 'undefined'){
                    
                    console.log("UPDATED");
                    console.log(data);

                    const newList = [...InventoryList];

                    let index = -1;

                    for(let i = 0; i < newList.length; i++){
                        if( newList[i].id === data.id){
                            index = i;
                            break;
                        }
                    }
                    newList.splice(index,1,data)

                    setInventoryList(newList)
            }else{
                alert("Error updating item")
            }
        
        })
        .catch(error => { console.log(error); })
    },

    delete: (id) => {
        fetch(URI + "/item/"+ id , {
            method: "DELETE"
        })
        .then(result => result.json())
        .then(data => {
            console.log("DELETED:");
            console.log(data);
        })
        .catch(error => {console.log(error);})
    }
}


export default InventoryApi;