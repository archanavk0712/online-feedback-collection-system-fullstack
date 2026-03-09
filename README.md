# Online Feedback Collection System

The Online Feedback Collection System is a full-stack web application designed to collect and manage feedback for products.
The system allows users to submit feedback for products while administrators can manage products, review feedback, and update feedback status.

This repository contains both the frontend and backend implementations of the system.

## Technology Stack

Backend

* Java
* Spring Boot
* Spring Data JPA
* MySQL
* Maven

Frontend

* React
* Axios
* CSS / React Bootstrap

Server

* Embedded Tomcat Server

## System Overview

The system is divided into two main components:

Frontend
Built using React, the frontend provides the user interface where users can register, log in, submit feedback, and view products. Administrators can also manage products and review submitted feedback.

Backend
Built using Spring Boot, the backend handles business logic, database operations, authentication, and email notifications.

The frontend communicates with the backend through REST APIs.

## Key Features

User Features

* User registration and login
* View list of products
* Submit feedback for products

Admin Features

* Admin login
* Add new products
* Edit product details
* Delete products
* View all feedback
* Filter feedback
* Mark feedback as reviewed

Notification Feature

* Email notification is sent to the user when their feedback is marked as reviewed by the admin.

## Project Structure

Online Feedback Collection System

backend
Spring Boot application responsible for API development, database interaction, and business logic.

frontend
React application responsible for the user interface and API integration.

## How the System Works

1. Users register or log in through the frontend application.
2. The frontend sends API requests to the backend.
3. The backend processes requests and stores data in the MySQL database.
4. Users can submit feedback for products.
5. Administrators can manage products and review submitted feedback.
6. When feedback is marked as reviewed, the system sends an email notification to the user.

## Running the Application

Backend Setup

1. Navigate to the backend folder.
2. Configure the MySQL database connection in application.properties.
3. Run the project as a Spring Boot application.
4. The backend server will start on http://localhost:8080.

Frontend Setup

1. Navigate to the frontend folder.
2. Install dependencies using npm install.
3. Run the application using npm run dev.
4. Open the application in the browser at http://localhost:5173.

Ensure that the backend server is running before starting the frontend application.

## API Testing

The backend APIs can be tested using:

* Postman
* Swagger UI
