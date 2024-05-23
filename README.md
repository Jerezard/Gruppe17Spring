### Spring Boot Backend

This is a Spring Boot backend application that provides Restful APIs for a car rental front end website.

## Getting Started

# 1.Clone the repository:
    git clone https://github.com/your-username/spring-boot-backend.git



# 2.Download the local database or connect to a database via 'application.properties' file:
    Update the database connection detail in 'src/main/resources/application.properties'
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your-username
    spring.datasource.password=your-password


# 3.Build the project using Maven:
     mvn clean install



# 4.Run the application: 
    Run SpringGroup17Application.java or mvn spring-boot:run




# 5.The Backend server will start running on 'http//localhost:8080'

# Project Structure
- 'src/main/java/com/example/demo' - Main source code.
-  'src/main/resources' - Configuration files and templates.


## Contact 
For any questions or suggestions contact us at:
- Jeremy - jeremys@ntnu.stud.no
- Oscar - oscarzahl@ntnu.stud.no

# Additional Information
- Ensure that your Mysql server is running and accessible before running the application.
- You might have to adjust firewall setting to allow the application to connect to the database
