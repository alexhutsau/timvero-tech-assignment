# Loan Schedule API - HELP.md

## Overview
This Spring Boot application calculates a loan schedule using annuity payments. It provides a REST API that returns a payment schedule based on the loan amount, interest rate, and loan term.

## How to Use

### 1. Start the Application
Ensure you have Java and Gradle installed, then run the application:
```sh
./gradlew bootRun
```
The application will start on `http://localhost:8080`.

Alternatively, if using Maven, run:
```sh
mvn spring-boot:run
```

### 2. API Endpoint
**GET** `/loan/schedule`

#### Query Parameters:
- `amount` (double) - Loan amount
- `rate` (double) - Annual interest rate (percentage)
- `term` (int) - Loan term in months

#### Example Request:
```sh
curl -X GET "http://localhost:8080/loan/schedule?amount=10000&rate=5&term=12" -H "Accept: application/json"
```

### 3. Response Format
The API returns a JSON array with details of each payment:
```json
[
  {
    "month": 1,
    "annuityPayment": 856.07,
    "principal": 814.07,
    "interest": 42.00,
    "balance": 9185.93
  },
  ...
]
```

### 4. Explanation of Response Fields
- `month` - Payment number
- `annuityPayment` - Fixed monthly payment
- `principal` - Amount reducing the loan
- `interest` - Interest for the month
- `balance` - Remaining balance after payment

## Troubleshooting
- If the application fails to start, check Java and Gradle installation.
- Ensure port `8080` is available or change it in `application.properties`.
