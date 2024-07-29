# mp0727

# Tool Rental Application

This is a prototype Spring Boot application for a tool rental service. The application provides REST APIs for managing
tool rentals and stores data in a MongoDB database. The application assumes infinite availability for customers.

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Specification Notes](#specification-notes)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Default Data](#default-data)
- [Running Tests](#running-tests)
- [Contributing](#contributing)
- [License](#license)

## Features

- Manage tools and rental agreements.
- Determine business days and holidays for rental periods.
- Tool Checkout: Check out tools with rental days and discounts.
- Charge Calculation: Calculate pre-discount and final charges based on rental duration and discounts.
- Rental Agreement Generation: Create rental agreements with detailed information.

## Requirements

- Java 21 or higher
- Docker and Docker Compose
- MongoDB (will be loaded from docker)

## Technologies

- **Java 21**: Programming language used for application development.(Required)
- **Spring Boot 3.3.2**: Framework for building the RESTful API.
- **MongoDB**: NoSQL database for storing tool and rental information.
- **Gradle**: Build tool for managing dependencies and building the project.(Gradle wrapper "./gradlew" should avoid the
  need to install )
- **Docker**: Containerization platform used with `org.springframework.boot:spring-boot-docker-compose` for Docker
  integration.

## Specification Notes

The class com.homedepot.toolrental.utils.HolidayUtilTest exercises all date and holiday calculation logic to ensure
accuracy and robustness.
com.homedepot.toolrental.service.RentalServiceTest exercises test scenarios 1 thru 6 along with demo-ing
printRentalAgreement

## Setup

### Clone the Repository

```sh
git clone https://github.com/matthew-b-payne/mp0727.git
cd mp0727

1. **Clone the repository**

    ```
    git clone https://github.com/matthew-b-payne/mp0727.git
    cd mp0727
    ```

2. **Build the project(will exercise tests in build.  run ./gradlew test to just run tests)**

    ```
    ./gradlew build
    ```

3. **Run the application**

    ```
    ./gradlew bootRun
    ```

4. **Access the application**

    The application will be running on `http://localhost:8080`.

### API Documentation

The OpenAPI reference can be found at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html). 
This provides interactive documentation for all available API endpoints.  
The api's are fairly generic rest api into the repositorys of (tools, rentals, users).  
By default there are 5 tools loaded at startup: 
- **URL**: `/tools/tools?page=0&size=20
- **Method**: `GET`
 **Response**:

    ```json
[
  {
    "toolCode": "CHNS",
    "toolType": "Chainsaw",
    "brand": "Stihl",
    "dailyCharge": 1.49,
    "weekdayCharge": true,
    "weekendCharge": false,
    "holidayCharge": true
  },
  {
    "toolCode": "LADW",
    "toolType": "Ladder",
    "brand": "Werner",
    "dailyCharge": 1.99,
    "weekdayCharge": true,
    "weekendCharge": true,
    "holidayCharge": false
  },
  {
    "toolCode": "JAKD",
    "toolType": "Jackhammer",
    "brand": "DeWalt",
    "dailyCharge": 2.99,
    "weekdayCharge": true,
    "weekendCharge": false,
    "holidayCharge": false
  },
  {
    "toolCode": "JAKR",
    "toolType": "Jackhammer",
    "brand": "Ridgid",
    "dailyCharge": 2.99,
    "weekdayCharge": true,
    "weekendCharge": false,
    "holidayCharge": false
  },
  {
    "toolCode": "string",
    "toolType": "string",
    "brand": "string",
    "dailyCharge": 0,
    "weekdayCharge": true,
    "weekendCharge": true,
    "holidayCharge": true
  }
]
    ```

