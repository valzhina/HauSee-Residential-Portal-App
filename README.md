# Residential Portal App
Hausee Housee is a web application that is built with JavaScript , HTML 
on a front end and Java/ Spring Boot on the backend, with a Heroku database 
that is supported with Hibernate tool (Object Relational Mapper or ORM) 
in conjunction with Spring Data JPA

![Alt text](/src/main/resources/static/gifs/Housee_App_gif_Wecome_page.gif "Welcome")

:desktop_computer:  [Link to video_TBD](https://www.youtube.com/watch?v=Zw-IGMnDgfY_TBD)

## Contents

- [Features](https://github.com/valzhina/HauSee-Residential-Portal-App#features)
- [Technologies and Stack](https://github.com/valzhina/HauSee-Residential-Portal-App#technologies-and-stack)
- [Cloudinary API Integration](https://github.com/valzhina/HauSee-Residential-Portal-App#cloudinary-api-integration)
- [Twilio API Integration](https://github.com/valzhina/HauSee-Residential-Portal-App#twilio-api-integration)

## Features

### User Registration and Log-In:
The app offers a comprehensive user registration and log-in system that caters 
to two distinct groups: building managers and residents. This ensures 
a personalized experience for each user, granting them access to the relevant 
functionalities of the app.

#### For Building Managers:
![Alt text](/src/main/resources/static/gifs/Housee_App_gif_managerLogIn.gif "Manager Log-in")
Upon registration, building managers gain access to their dedicated dashboard, 
allowing them to manage properties, track occupancy, handle maintenance 
requests, and oversee financial aspects. The log-in process is seamless, 
requiring their unique credentials for secure access.


#### For Residents:
![Alt text](/src/main/resources/static/gifs/Housee_App_gif_userLogIn.gif "User Log-in")
Residents undergo a separate registration process, granting them entry to their 
personalized profile and information about their rented address or unit. 
Through their log-in credentials, residents can conveniently make rent payments,
submit maintenance requests, engage with the community, and access important 
property-related updates.


### Unique Access to Property:
Upon successful log-in, both building managers and residents are seamlessly 
connected to the specific properties they are associated with.

#### For Building Managers:
![Alt text](/src/main/resources/static/gifs/Housee_App_gif_managerDashboard.gif "HomePages")
Building managers are granted access to a tailored dashboard that provides 
an overview of their managed properties. This dashboard includes a comprehensive
display of resident requests, showcasing the current status of these requests, 
along with key metrics. The occupancy status, financial information, and 
communication tools are readily accessible.

#### For Residents:
![Alt text](/src/main/resources/static/gifs/Housee_App_gif_managerLogInHousee_App_gif_userDashboard.gif "HomePages")
Residents are directly linked to information about their rented space, whether 
it's an individual address or a unit within an apartment complex. This 
connection empowers residents to effortlessly report issues and engage with 
property managers through efficient features.

## Interactive Issue Management Feature

![Alt text](/src/main/resources/static/gifs/Housee_App_gif_request.gif "Issue Management")

This innovative feature empowers residents to effortlessly communicate 
concerns and problems to property managers. Through the intuitive interface, 
residents can provide detailed explanations of the issues they're facing. 
What sets this feature apart is the ability to enhance their reports with 
visual evidence – photos or videos – to provide a clear understanding of the 
problem.

Residents can indicate their availability, allowing property managers to 
schedule maintenance or repairs at a convenient time. This two-way interaction 
streamlines the issue resolution process and ensures that resident preferences 
are taken into account.

Each reported issue initiates a dedicated record within the system, capturing 
the description, media attachments, and availability details. These records are 
then made visible on the manager's dashboard, presenting a comprehensive 
overview of pending tasks. This approach ensures that managers have all the 
information they need to prioritize and address issues effectively.

## Manager Notification Feature
To ensure timely attention to new requests, the system automatically triggers 
a notification to property managers when residents submit new tickets. This 
notification promptly informs managers that there are new issues requiring 
their oversight and resolution.

![Alt text](/src/main/resources/static/gifs/Housee_App_gif_textmessage.gif "Manager Notification")

## Ticket Workflow

After the "Interactive Issue Management" feature generates tickets, they are 
displayed on the residential dashboard under the section labeled "Open 
Requests." This section provides an organized view of ongoing issues reported 
by residents.


### Residential Dashboard Feature:
Upon logging in, residents are presented with a dedicated dashboard that 
includes a section for "Open Requests." In this section, each reported issue 
is represented as a distinct item or entry, displaying relevant details like 
the description of the problem, attached media, and availability preferences.

![Alt text](/src/main/resources/static/gifs/Housee_App_gif_newPastrequests.gif "Residential Dashboard")

## Manager Dashboard Feature
Typically, after residents report issues, property managers are responsible 
for overseeing the resolution process. Once a reported issue has been 
resolved, property managers can perform actions to signify its completion.

![Alt text](/src/main/resources/static/gifs/Housee_App_gif_managerDashboard.gif "Manager Dashboard")


## Technologies and Stack
The visual design of this site is implemented using Bootstrap, HTML and CSS.
JavaScript and AJAX requests are used for interactivity.

**Backend:** Java, Spring Boot Framework, Maven <br />
**Frontend:** Javascript, React, HTML5, CSS3 <br />
**Databases:** Heroku
**APIs:** Cloudinary, Twilio API

## Cloudinary API Integration:
Behind the scenes, the Spring Boot back-end, in conjunction with the 
Cloudinary API, ensures a smooth process for image storage and retrieval.

### Issue Submission and Image Handling: 
When a resident submits an issue report through the app, the related data, 
including description, media attachments, and availability preferences, is 
transmitted to the Spring Boot application.

### Cloudinary Integration: 
Media attachments, such as photos and videos, are seamlessly uploaded to 
Cloudinary using its API. Cloudinary provides a reliable and efficient way to 
store and manage these media files securely in the cloud.

### Database Storage: 
The Spring Boot application, utilizing Spring Data JPA, securely stores all 
issue-related data, including Cloudinary's generated URLs for the media files, 
in the database. Each report is assigned a unique identifier for easy reference.

### Manager's Dashboard: 
On the manager's dashboard, the Spring Boot application retrieves the stored 
issue reports from the database, including the Cloudinary URLs for media 
attachments.

### Cloudinary Integration for Display: 
To display images and videos on the manager's dashboard, the Cloudinary URLs 
are utilized. These URLs act as direct pointers to the media stored in 
Cloudinary. This approach ensures efficient and seamless display of visuals.

### Issue Management:
Managers can access issue details, media attachments, and availability 
preferences on the dashboard. They can take informed actions, prioritize 
tasks, and schedule maintenance based on the provided information.

## Ticket Creation and Twilio API Integration:

Event Trigger:
When a resident submits a new issue through the "Interactive Issue Management" 
feature, a backend event is triggered to create a new ticket in the system.

Ticket Data Extraction:
The backend extracts essential ticket information, including the issue 
description, location, and relevant details from the submitted data.

Twilio API Initialization:
Once the ticket data is gathered, the backend prepares to make an API call to 
the Twilio service. This involves setting up the necessary API credentials and 
endpoints provided by Twilio.

API Call:
The backend initiates an HTTP POST request to the Twilio REST API. This request 
includes the necessary data to send an SMS notification to the designated property 
manager.

SMS Notification Content:
The data sent to the Twilio API will include the recipient's phone number, the 
message content (which can include ticket details like description and location), 
and any other required parameters.

Twilio API Handling:
Twilio's API processes the incoming request from your backend. It handles the 
SMS notification generation and delivery to the specified manager's phone 
number.

Manager Notification:
Twilio's service sends an SMS message to the designated manager's phone number,
alerting them about the newly created ticket. The SMS message can include 
relevant details extracted from the ticket.

Manager Action:
Upon receiving the SMS notification, the manager can review the issue and take 
appropriate action through the property management dashboard.