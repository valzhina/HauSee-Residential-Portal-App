
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("request-form")
const requestContainer = document.getElementById("request-container")// By ID


const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/requests/"


async function getRequests(userId) {
    await fetch(`${baseUrl}manager/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createRequestCards(data))
        .catch(err => console.error(err))
}

async function handleStatusChange(requestId){
    await fetch(baseUrl + requestId, {
        method: "PUT",
        headers: headers
    })
        .catch(err => console.error(err))

    return getRequests(userId);
}

async function getRequestById(noteId){
    await fetch(baseUrl + requestId, {
        method: "GET",
        headers: headers
    })
        .then(res => res.json())
        .then(data => populateModal(data))
        .catch(err => console.error(err.message))
}

async function handleRequestEdit(requestId){
    let bodyObj = {
        id: requestId,
        body: RequestProblem.value
    }

    await fetch(baseUrl, {
        method: "PUT",
        body: JSON.stringify(bodyObj),
        headers: headers
    })
        .catch(err => console.error(err))

    return getRequests(userId);
}

const createRequestCards = (array) => {
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
                        <div class="d-flex justify-content-between">
                            <button class="btn btn-delete right" onclick="handleStatusChange(${obj.id})">Mark as Done</button>
                        </div>
                    </div>
                </div>
            `
            requestContainer.append(requestCard);
        }
    })
}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

const populateModal = (obj) =>{
    requestProblem.innerText = ''
    requestBody.innerText = obj.body
    updateRequestBtn.setAttribute('data-request-id', obj.id)
}

getRequests(userId);

submitForm.addEventListener("submit", handleSubmit)

updateRequestBtn.addEventListener("click", (e)=>{
    let requestId = e.target.getAttribute('data-request-id')
    handleRequestEdit(requestId);
})