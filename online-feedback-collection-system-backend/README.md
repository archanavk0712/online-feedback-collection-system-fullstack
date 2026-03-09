# Online Feedback Collection System – Backend

## Project Overview
The Online Feedback Collection System is a backend application built using Java Spring Boot.  
It allows users to submit feedback for products, while admins can manage products, review feedback, and update feedback status.

When an admin marks feedback as reviewed, an email notification is sent to the respective user.

##  Features
- User registration and login
- Admin login (separate from users)
- Product management (Add, Edit, Delete, View)
- Feedback submission by users
- Admin can:
  - View all feedback
  - Filter feedback
  - Mark feedback as reviewed
- Email notification sent when feedback is reviewed

## Tech Stack Used
- Framework: Java Spring Boot, Spring Data JPA
- Database: MySQL
- Server: Embedded Tomcat Server
- Build Tool: Maven

## Steps to Run Backend Code
1. Clone the backend repository
2. Import the project into Eclipse(Spring Tool Suite)
3. Configure MySQL database in application.properties
4. Run the project as Spring Boot Application
5. Embedded Tomcat server will start automatically

## API Testing
- APIs can be tested using:
  - Postman or
  - Swagger UI
