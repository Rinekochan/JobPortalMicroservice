# Job Portal Microservice

## A project using Spring Boot to create a microservice system.

The project have 6 microservices
- User - MongoDB
- Candidate - MongoDB
- Employer, Company - MySQL
- Job - MongoDB
- Application - MongoDB

This project is using Spring Boot 3+, Spring Cloud Eureka, Config, and Gateway server. It also uses 3rd party mail service (Brevo) to send application's notification to the candidate through email.

This project can be deployed with docker:
```bash
cd docker-compose
docker compose up -d
```
