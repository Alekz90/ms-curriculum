# ms-curriculum

REST API for creating and managing curriculum vitae (resume) information. It provides user authentication and manages profiles, professional details, work experience, education, abilities, languages, certifications, links, images, addresses, and summaries.

## Technology

- Java 21
- Spring Boot 3.5
- Spring Security with JWT
- MongoDB
- Gradle
- OpenAPI / Swagger UI

## Prerequisites

- JDK 21
- MongoDB instance
- Docker (optional)

## Configuration

The application requires these environment variables:

| Variable | Description | Example |
| --- | --- | --- |
| `MONGO_URI` | MongoDB connection URI | `mongodb://localhost:27017` |
| `MONGO_DB` | MongoDB database name | `resume_db` |
| `ALLOWED_ORIGINS` | Comma-separated CORS origins | `http://localhost:4200,http://localhost:80` |

The server runs on port `8080` by default.

### PowerShell example

```powershell
$env:MONGO_URI = "mongodb://localhost:27017"
$env:MONGO_DB = "resume_db"
$env:ALLOWED_ORIGINS = "http://localhost:4200"
```

## Run locally

```powershell
.\gradlew.bat bootRun
```

To build the executable JAR:

```powershell
.\gradlew.bat bootJar
java -jar .\build\libs\ms-curriculum-0.0.1-SNAPSHOT.jar
```

## Run with Docker

```powershell
docker build -t ms-curriculum:1.0.0 .
docker run --rm -p 8080:8080 `
  -e MONGO_URI="mongodb://host.docker.internal:27017" `
  -e MONGO_DB="resume_db" `
  -e ALLOWED_ORIGINS="http://localhost:4200" `
  ms-curriculum:1.0.0
```

## API documentation

When the service is running, explore the interactive API documentation at:

```text
http://localhost:8080/swagger-ui/index.html
```

The OpenAPI specification is available at:

```text
http://localhost:8080/v3/api-docs
```

## API resources

| Area | Base path |
| --- | --- |
| Authentication | `/authentications/v1` |
| Verifications | `/verifications/v1` |
| Password recoveries | `/recoveries/v1` |
| Users | `/users` |
| Profiles | `/profiles/v1` |
| Professional details | `/professional-details/v1` |
| Addresses | `/address/v1` |
| Images | `/images/v1` |
| Education | `/educations/v1` |
| Experience | `/experiences/v1` |
| Abilities | `/abilities/v1` |
| Links | `/links/v1` |
| Languages | `/languages/v1` |
| Certifications | `/certifications/v1` |
| Summaries | `/summaries/v1` |

Registration, login, verification, and password-recovery endpoints are public. Other protected operations require a JWT obtained from the login endpoint and supplied in the `Authorization` header as a Bearer token:

```http
Authorization: Bearer <token>
```

## Tests

```powershell
.\gradlew.bat test
```

## License

This project is licensed under the [MIT License](LICENSE).