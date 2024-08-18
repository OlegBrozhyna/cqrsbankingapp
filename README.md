# Description

This project is a banking application built with Java 17 and Spring Boot 3.2.x.
It utilizes PostgreSQL as the database and follows the CQRS (Command Query Responsibility Segregation)
and Event Sourcing patterns. The application is secured with Spring Security,
using JWT for authentication and authorization. Additionally, Debezium is integrated to handle event processing.

### Technology :

* Java 17
* Spring Boot
* PostgreSQL
* Spring Security (JWT)
* Debezium
* 
### Key Features:

* Client Registration and Authentication: Users can register and log in securely.
* Bank Card Management: Create bank cards linked to a single account.
* Transaction Handling: Perform transactions between different cards.
* Balance Inquiry: View the balance of cards and accounts.
* For further reference, please consider the following sections:



### Future Plans:

* CQRS & Event Sourcing Theory: Explanation of the CQRS pattern and event sourcing concepts.
* Application Development: Creation of events, aggregates, services, and controllers.
* Spring Security Setup: Securing the application with JWT.
* Debezium Configuration: Setting up Debezium for event handling.
* Project Refactoring: Extracting common code and creating a dedicated event handler application.
