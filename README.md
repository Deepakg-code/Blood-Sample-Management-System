# Blood Sample Management System

## Overview

The **Blood Sample Management System** is a backend REST API designed to manage blood banks efficiently. It allows hospitals and blood banks to record, update, and track blood samples, ensuring seamless availability and management of blood units. This system streamlines the process of searching and managing blood samples based on blood type and location.

## Table of Contents

- [Technologies](#technologies)
- [Features](#key-features)
- [Requirements](#requirements)
- [Installation](#installation)
- [Running the Project](#running-the-project)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [License](#license)

## Technologies

- Java 17
- Spring Boot 3.4.1
- Spring Security & JWT Authentication
- Hibernate & JPA
- MySQL Database
- Maven

## Key Features:
- **Blood Bank Management**: Register and manage multiple blood banks.
- **Blood Sample Tracking**: Store and update blood sample details based on type and availability.
- **Hospital Integration**: Hospitals can request blood samples from available banks.
- **Secure Authentication**: Role-based access control for hospitals and blood banks.
- **Search Functionality**: Find available blood samples based on location and type.

## Requirements

To run this project, ensure you have:
- Java 17 or later installed
- Maven 3.8.x or above
- MySQL Server installed and configured

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/Deepakg-code/Blood-Sample-Management-System.git
   cd Blood-Sample-Management-System
   ```

2. **Configure the database:**
   Update the `application.yml` file with your MySQL database details.

3. **Install dependencies:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

## Running the Project

Once started, the application runs at `http://localhost:8080` by default.

## API Endpoints

| Method | Endpoint                    | Description                          |
|--------|-----------------------------|--------------------------------------|
| POST   | `/register`                 | Register a new user                  |
| POST   | `/register/bloodbank`       | Register a new blood bank            |
| POST   | `/register/hospital`        | Register a new hospital              |
| POST   | `/login`                    | Authenticate a user                  |
| GET    | `/blood-samples`            | Fetch available blood samples        |
| GET    | `/blood-samples/{type}`     | Get blood samples by blood type      |
| POST   | `/request-blood`            | Request blood from a blood bank      |

## Testing

You can test the API using Postman or any API testing tool by sending requests to the provided endpoints.

## License

This project is open-source and available for learning and development purposes.
