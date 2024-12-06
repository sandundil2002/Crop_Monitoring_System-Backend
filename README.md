<a href="https://git.io/typing-svg"><img src="https://readme-typing-svg.herokuapp.com?font=Fira+Code&weight=600&size=50&pause=1000&center=true&vCenter=true&width=835&height=70&lines=Crop+Monitoring+System+API" alt="Typing SVG" /></a>

<p id="description">Welcome to the Crop Monitoring System Backend repository! This system is designed to manage and monitor crops fields staff vehicles equipment and logs efficiently. Built using Spring Boot it provides robust API endpoints with secure authentication and role-based access control.</p>

  
  
<h2>🧐 Features</h2>

Here're some of the project's best features:

*   Secure login with JWT-based authentication.
*   Role-based access control for MANAGER SCIENTIST and ADMINISTRATIVE users.
*   Two step verification with OTP
*   Fields: Manage field details including location size and associated crops or staff.
*   Crops: Add update and remove crops with seasonal and category information.
*   Staff: Manage staff information including field and vehicle assignments.
*   Vehicles & Equipment: Track assignments and availability of vehicles and equipment.
*   Logs: Record and monitor observational data for fields and crops.
*   Comprehensive Postman API template for testing all endpoints.

<h2>🛠️ Installation Steps:</h2>

<p>1. Prerequisites</p>

```
Java 17 or higher & Gradle 3.6+ & MySQL 8.0+ & Postman (optional for testing APIs).
```

<p>2. Clone the repository:</p>

```
git clone https://github.com/sandundil2002/Crop_Monitoring_System-Backend.git
```

<p>3. Configure the database Update the application.properties file in the src/main/resources directory:</p>

```
spring.datasource.url=jdbc:mysql://localhost:3306/crop_monitoring spring.datasource.username= spring.datasource.password= spring.jpa.hibernate.ddl-auto=update
```

<p>4. Build and run the application:</p>

<p>5. Access the application:</p>

```
http://localhost:8080
```

<h2>API Documentation</h2>

* You can view the detailed API documentation with example requests and responses <a href="https://documenter.getpostman.com/view/35384990/2sAYBbeUzN">here</a>.
  
<h2>💻 Built with</h2>

Technologies used in the project:

*   Java: Backend development.
*   Spring Boot: Framework for creating RESTful APIs.
*   Hibernate/JPA: Database management and object mapping.
*   MySQL: Relational database for storing entity data.
*   JWT (JSON Web Tokens): Authentication and authorization.
*   Lombok: Reduces boilerplate code.
*   Gradle: Dependency and build management.

<h2>🛡️ License:</h2>

This project is licensed under the MIT License.
