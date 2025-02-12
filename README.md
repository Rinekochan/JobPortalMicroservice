# Job Portal Microservice

## A personal project using Spring Boot 3.0.0+ to create a job portal system with microservices.

The project have 6 microservices:
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

![Project Databases](https://github.com/user-attachments/assets/9669b749-d541-43d3-9d58-1f9d93c8995a)

![Project Services](https://github.com/user-attachments/assets/c1cc6a8c-7ca7-41c3-976f-e2e2f9829d81)

![Project-Dependencies](https://github.com/user-attachments/assets/fc2a7bf8-5146-4ba4-937c-e3e4766bd147)
