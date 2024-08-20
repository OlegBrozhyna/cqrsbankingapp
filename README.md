# Description

This project is a banking application built with Java 17 and Spring Boot.
It utilizes PostgreSQL as the database and follows the CQRS (Command Query Responsibility Segregation)
and Event Sourcing patterns. The application is secured with Spring Security,
using JWT for authentication and authorization. Additionally, Debezium is integrated to handle event processing.

### Technology :

* Java 17
* Spring Boot
* PostgreSQL
* Spring Security (JWT)
* Debezium

### Pattern :

* CQRS (Command Query Responsibility Segregation) separates operations into commands (for modifying system state) and
  queries (for reading data), allowing for optimized data handling and improved scalability.
* Event Sourcing stores the history of changes as a sequence of events instead of just saving the current state. This
  provides a complete audit trail of changes and the ability to recreate the system's state at any point in time.

### Key Features:

* Client Registration and Authentication: Users can register and log in securely.
* Bank Card Management: Create bank cards linked to a single account.
* Transaction Handling: Perform transactions between different cards.
* Balance Inquiry: View the balance of cards and accounts.




