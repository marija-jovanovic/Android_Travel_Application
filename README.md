<h1 align = center>Android Travel Application</h1>
<div align="center"><img src="https://airmaxstienda.es/wp-content/uploads/2023/04/promote-travel-apps.png"></div>

 <b><h2 align = center>Introduction</h2></b>

Android application for scheduling travel rides.

The application works with two types of users: 1) travel companies and 2) travellers. 

<b>*The travel companies*</b> can schedule new travel rides, view details about their past as well as ongoing rides and view reviews for each of their rides.

<b>*The travellers*</b> can book new travel rides, view details about their past as well as currently booked rides, write reviews about past rides and view the travel route between the city of departure and city of arrival via Google Maps.

For the creation of this app the following stuff were used:
- SQLite database for storing the needed information
- RecyclerView for displaying the needed information
- Navigation between activities using intents
- Fragments for achieving different portrait/landscape view of a layout. When a travel company clicks on one of its own rides and rotates the screen in landscape mode, apart from displaying the ride details on the left, we will also be able to see all of the reviews for that paticular ride on the right side.
- Google maps for drawing a line which connects the two cities (for departure and arrival)

<h2 align = center>Installation</h2>

Firstly, it is required to have Android Studio installed.

Then, click on the <> Code button located at the upper right corner of this current repository. Copy the hyperlink located right under the HTTPS option.

Open Android Studio and go to File > New > Project from Version Control. You will notice that a new screen will be opened. At Version Control, opt for Git from the dropdown menu.

Then, paste the hyperlink in the field for the URL and choose your directory. At the end, press the Clone button.

For more detailed information I suggest visiting the following site https://www.geeksforgeeks.org/how-to-clone-android-project-from-github-in-android-studio/.

<h2 align = center>Detailed description of the application</h2>

The first screen that pops up when you first start the app is the SignUp screen. Here, you can create new account by providing an email, password and choosing a user role (travel company/traveller). If you already have an account, you can just click on the text which directs you to the LogIn page and provide your existing email and password combination.

After a successful login, the user will be redirected to the main menu which corresponds to its role. 

<h3 align = center>Functionalities of a travel company</h3>

The main screen has two images. By clicking the first image, the company can create a new travel schedule by providing some information (date, departing time, arrival time, max number of seats, price per person, city of departure, city of arrival, longitude - latitude pairs for both of the cities).

By clicking the second image, the company can view the details of all of its past as well as ongoing rides. If the company clicks on one of the rides, it will be redirected to a new screen where if the company rotates the phone in landscape mode it will be able to see all of the reviews for the selected ride at the right side. The different layout view for the same activity is achieved by using two fragments.

<h3 align = center>Functionalities of a traveller</h3>

The main screen has two images. By clicking the first image, the traveller can book a new ride only if there are still more seats left. Only those rides which still have not departed will be displayed as booking options.

By clicking the second image, the traveller can view the details of all of its past as well as ongoing rides. If the traveller clicks on one of the rides, it will be redirected to a new screen where the traveller can either write a review or view the travel route via google maps. The review can only be written if the ride has finished/arrived at the destination. When writing a review, the traveller can rate the ride via 1-5 star rating and it can also provide a short comment. Once a review is successfully sent, the traveller will get a notification for confirmation. On the other hand, if the traveller opts to check the route via google maps, the maps will actually display a line that directly connects the city of departure and city of arrival. If the map pin is clicked, the name of the clicked city will be displayed and the camera will position at the center of the pinned city. 

NOTE: If you would like to have the google maps activity work, you will need to provide your own Google Maps API key.
