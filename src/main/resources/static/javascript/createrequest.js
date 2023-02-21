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

const handleSubmit = async (e) => {
    e.preventDefault()
    let today = new Date();

    const imageUrl = await uploadImage();

    let bodyObj = {
        unit: document.getElementById("unit").value,
        problem: document.getElementById("problem").value,
        category: document.getElementById("category").value,
        description: document.getElementById("description").value,
        imgUrl: imageUrl,
        rDate: today.getDate(),
        rTime: Date.now(),
        status: "open"
    }

    await addRequest(bodyObj);

}

async function addRequest(obj) {
    const response = await fetch(`${baseUrl}user/${userId}`, {
        method: "POST",
        body: JSON.stringify(obj),
        headers: headers
    })
        .catch(err => console.error(err.message))
    if (response.status == 200) {
        await sendMessage();
        window.location.replace("http://localhost:8080/openrequests.html")
    }
}

async function uploadImage() {

    const cPreset = CLOUDINARY_KEY;
    let cUrl = "https://api.cloudinary.com/v1_1/CLOUDINARY_UID";

    let imageBody = new FormData();
    imageBody.append('file', document.getElementById("my_file").files[0]);
    imageBody.append('upload_preset', cPreset);

    const response = await axios({
        url: cUrl,
        method: 'POST',
        data: imageBody
    }).catch(err => console.error(err.message));

    if (response.status == 200) {
        return response.data.secure_url;
    }
}

async function sendMessage() {
//    console.log('send message')
    const turl = "https://api.twilio.com/2010-04-01/Accounts/TWILIO_USERNAME/Messages.json";
    unit = document.getElementById("unit").value;
    category = document.getElementById("category").value;

    const response = await axios.post(
        turl,
        new URLSearchParams({
            'MessagingServiceSid': TWILIO_ID,
            'To': PHONE_NUMBER,
            'Body': `There is a new service request from unit: ${unit} in category ${category}`
        }),
        {
            auth: {
                username: TWILIO_USERNAME,
                password: TWILIO_PASSWORD
            }
        }
    );
//    console.log(response);

}

function handleLogout(){
    let c = document.cookie.split(";");
    for(let i in c){
        document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu, 01 Jan 1970 00:00:00 GMT"
    }
}

submitForm.addEventListener("submit", handleSubmit)
