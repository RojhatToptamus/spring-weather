# spring-weather
What is Spring Weather Project About?
In the project weather information are obtained by parsing the JSON data using free version weather api(OpenWeatherMap).
<br/>
How It Works
There are two types of users in the project. These; Admin and User are user types. When the program is opened, users log-in from the user login screen which is developed with Spring Boot Security. After successful login, the user is directed to the "/ home" address with his / her weather information.
<br/>
The users in the program have authority according to the user types. Admin user type; While it has the privileges of creating, deleting and updating users, the User type has the authority to add, delete cities and view weather conditions.
<br/>
Methods in The Project
/home: user profile page,on this page users check the weather forecast of the cities that they own.
/admin/getUser/{id}:<br/>
/addCity/{city}:<br/>
/deleteCity/{city}:<br/>
/admin/createUser:<br/>
/admin/updateUsername/{id}<br/>
/admin/updateUserRole/{id}<br/>
/admin/deleteUser/{id}<br/>
/weather/{city}<br/>
<br/>
to see how the project works: https://youtu.be/LCG_7I7n8UU
