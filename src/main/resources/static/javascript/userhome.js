
//Cookie
const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];

//DOM Elements
const submitForm = document.getElementById("request-form")
const requestContainer = document.getElementById("request-container")// By ID

//Modal Elements
let requestBody = document.getElementById(`request-body`)
let updateRequestBtn = document.getElementById('update-request-button')

const headers = {
    'Content-Type': 'application/json'
}

const baseUrl = "http://localhost:8080/api/v1/requests/"

const handleSubmit = async (e) => {
    e.preventDefault()
    let bodyObj = {
        body: document.getElementById("request-input").value
    }
    await addRequest(bodyObj);
    document.getElementById("request-input").value = ''
}

async function addRequest(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        return getRequests(userId);
    }
}

async function getRequests(userId) {
    await fetch(`${baseUrl}user/${userId}`, {
        method: "GET",
        headers: headers
    })
        .then(response => response.json())
        .then(data => createNoteCards(data))
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
    RequestContainer.innerHTML = ''
    array.forEach(obj => {
        let RequestCard = document.createElement("div")
        requestCard.classList.add("m-2")
        requestCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getRequestById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#request-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        requestContainer.append(requestCard);
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