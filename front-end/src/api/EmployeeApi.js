// const BASE = "http://localhost:8080"  // use this if running locally
const BASE = "http://localhost:8080" // edit this with your AWS endpoint
const URI = BASE + "/api"

const EmployeeApi = {
    
    getAll: (setUserList) => {

        fetch(URI + "/user")
            .then( result => result.json() )
            .then( data => {
                setUserList(data)
            } )
            .catch( error => { console.log(error) } )
    },

    getUserById: (id, setUser) => {
        fetch(URI + "/user/" + id)
            .then(result => result.json())
            .then(data => {
                if(typeof data.username !== 'undefined') {
                    setUser({
                        id: data.id,
                        username: data.username,
                        first_name: data.first_name,
                        last_name: data.last_name,
                        phone: data.phone,
                        password: data.password,
                        role: "ROLE_USER",
                        items: data.items,
                        enabled: true
                    })
                } else {
                    alert("Username already exists!")
                }
            })
            .catch( error=> { console.log(error)})
    },

    getUserByUsername: (user, setUser) => {
        fetch(URI + "/user/username/" + user.username)
            .then(result => result.json())
            .then(data => {
                console.log("DATA")
                console.log(data)
                if(typeof data.id !== 'undefined') {
                    setUser(data)
                    //alert('SUCCESS!')
                    console.log("got here")
                } else {
                    alert("Username already exists!")
                }
            })
            .catch( error => {
                console.log(error)
            });
    },

    authenticate: (user, jwt, setJwt) => {
        fetch(BASE + "/authenticate", {
            method: "POST",
            body: JSON.stringify({
                username: user.username,
                password: user.password
            }),
            headers: { "Content-Type": "application/json" }
        })
            .then( response => {
                return response.json()
            })
            .then( data => {
                setJwt(...jwt, data.jwt)
                console.log(data)
            })

            .catch( error => console.log(error) ) 
    },

    add: (user) => {
        
        fetch(URI + "/user", {
            method: "POST",
            body: JSON.stringify(user),
            headers: { "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {
                console.log(data.id);
                if(typeof data.id !== 'undefined') {

                    console.log("CREATED USER:");
                    console.log(data);

                    alert(`USER CREATED \n` +
                        `------------------------\n` + 
                        `ID: ${data.id}\n` +
                        `First Name: ${data.first_name}\n` +
                        `Last Name: ${data.last_name}\n` +
                        `Email: ${data.username}\n` +
                        `Phone: ${data.phone}\n`
                    )
                }
                else {
                    alert("User can't be created, check that you are not using an email already in use by another user.")
                }

            } )
            .catch( error => { 
                console.log(error);
            } )

    },

    addOrder: (id, order, setOrder) => {
        fetch(URI + "/user/" + id + "/order/add", {
            method: 'PUT',
            body: JSON.stringify(order),
            headers: { "Content-Type": "application/json"}
        })
            .then( result => result.json() )
            .then( data => {
                if( typeof data.id != 'undefined' ) {
                    setOrder(order)
                } else {
                    alert("Order can't be added to user")
                }
            })
            .catch( error => { console.log(error) } )
    },

    update: (user, userList, setUserList) => {

        fetch(URI + "/user",  {
            method: 'PUT',
            body: JSON.stringify(user),
            headers: {  "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {

                if(typeof data.id !== 'undefined') {

                    console.log("UPDATED:");
                    console.log(data);

                    const newList = [...userList];

                    let index = -1;

                    for(let i = 0; i < newList.length; i++) {

                        if( newList[i].id === data.id ) {
                            index = i;
                            break;
                        }
                    }

                    newList.splice(index, 1, data)

                    setUserList(newList)
                }
                else {
                    alert("Error updating user, email choosen may already be in use by another user")
                }
                
            } )
            .catch(error => { console.log(error); })

    },

    delete: (id) => {

        fetch(URI + "/user/" + id, {
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

export default EmployeeApi;