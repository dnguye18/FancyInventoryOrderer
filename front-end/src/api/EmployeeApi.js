// const BASE = "http://localhost:8080"  // use this if running locally
const BASE = "http://localhost:8080" // edit this with your AWS endpoint
const URI = BASE + "/api"

const EmployeeApi = {
    
    getAll: (setEmployeeList) => {

        fetch(URI + "/user")
            .then( result => result.json() )
            .then( data => {
                setEmployeeList(data)
            } )
            .catch( error => { console.log(error) } )
    },

    add: (employee) => {
        
        fetch(URI + "/user", {
            method: "POST",
            body: JSON.stringify(employee),
            headers: { "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {
                console.log(data.id);
                if(typeof data.id !== 'undefined') {

                    console.log("CREATED EMPLOYEE:");
                    console.log(data);

                    alert(`EMPLOYEE CREATED \n` +
                        `------------------------\n` + 
                        `ID: ${data.id}\n` +
                        `First Name: ${data.first_name}\n` +
                        `Last Name: ${data.last_name}\n` +
                        `Email: ${data.username}\n` +
                        `Phone: ${data.phone}\n`
                    )
                }
                else {
                    alert("Employee can't be created, check that you are not using an email already in use by another employee.")
                }

            } )
            .catch( error => { 
                console.log(error);
            } )

    },

    update: (employee, employeeList, setEmployeeList) => {

        fetch(URI + "/user",  {
            method: 'PUT',
            body: JSON.stringify(employee),
            headers: {  "Content-Type": "application/json" }
        })
            .then( result => result.json() )
            .then( data => {

                if(typeof data.id !== 'undefined') {

                    console.log("UPDATED:");
                    console.log(data);

                    const newList = [...employeeList];

                    let index = -1;

                    for(let i = 0; i < newList.length; i++) {

                        if( newList[i].id === data.id ) {
                            index = i;
                            break;
                        }
                    }

                    newList.splice(index, 1, data)

                    setEmployeeList(newList)
                }
                else {
                    alert("Error updating employee, email choosen may already be in use by another employee")
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