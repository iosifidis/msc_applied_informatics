## Documentation for Student Management API

This project is a basic Spring Boot API for managing student data. It includes endpoints to add, retrieve, and list students.

### Student Class
The `Student` class represents a student with the following attributes:
- **name**: The student's name.
- **age**: The student's age.
- **location**: The student's location.

Each attribute has a corresponding getter method to enable retrieval by the framework.

### HelloController Class

The `HelloController` handles HTTP requests related to student management. Available endpoints:

- **askName**: Returns a greeting message for a given name.
  - **URL**: `GET /askName/{name}`
  - **Path Variable**: `name` (String) – the name to greet.

- **addStudent**: Adds a new student to the list based on query parameters.
  - **URL**: `GET /addstudent`
  - **Query Parameters**:
    - `name` (String): The student's name.
    - `age` (int): The student's age.
    - `location` (String): The student's location.

- **allStudents**: Retrieves the list of all students in JSON format.
  - **URL**: `GET /allStudents`

### HelloService Class

The `HelloService` class contains the business logic and handles data management for the student records.

- **getStudent**: Creates a new `Student` object based on provided parameters.
- **addStudent**: Adds a `Student` object to the list.
- **getAllStudents**: Returns the list of all students.

## Getting Started

To run this project locally:   
1. Download zip file and extract.   
2. Inseert the project to eclipse.   
3. Run the project.    
4. Access the API at `http://localhost:8080`.

## Examples

- **Greeting a user**:  
  ```http://localhost:8080/askName/Stathis```

- **Adding a student**:  
  ```http://localhost:8080/addstudent?name=Jane&age=22&location=Athens```

- **Retrieving all students**:  
  ```http://localhost:8080/allStudents```
