//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const requestContainer = document.getElementById("requests-container")// By ID

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/requests/"

async function getRequests(userId) {
//    console.log("started get requests")
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createRequestCards(data))
        .catch(err => console.error(err))
}

async function handleDelete(requestId){
    await fetch(baseUrl + requestId, {
        method: "DELETE",
        headers: headers
    })
        .catch(err => console.error(err))

    return getRequests(userId);
}

const createRequestCards = (array) => {
//    console.log("Started create request cards")
    requestContainer.innerHTML = ''
    array.forEach(obj => {
        if(obj.status=="open") {
            let requestCard = document.createElement("div")
            requestCard.classList.add("col")
            requestCard.innerHTML = `
                <div class="card d-flex" style="width: 18rem; height: flex;">
                    <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                        <p class="card-text"><b>Unit #:</b> ${obj.unit}</p>
                        <p class="card-text"><b>Problem:</b> ${obj.problem}</p>
                        <p class="card-text"><b>Category:</b> ${obj.category}</p>
                        <p class="card-text"><b>Description:</b> ${obj.description}</p>
                        <img class="card-img-bottom" src="${obj.imgUrl}">
                        <div class="d-flex justify-content-end">
                          <button class="btn btn-delete right" onclick="handleDelete(${obj.id})">Delete</button>
                        </div>
                    </div>
                </div>
            `
            requestContainer.append(requestCard)
        }
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

getRequests(userId);