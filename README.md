# Demo API

A serverless Spring Boot REST API deployed on AWS Lambda with API Gateway.

## Tech Stack

| Component | Technology |
|-----------|------------|
| Language | Java 17 |
| Framework | Spring Boot 2.7.16 |
| Build Tool | Maven 3.8.7 |
| Cloud | AWS Lambda + API Gateway |
| IaC | AWS SAM (CloudFormation) |
| CI/CD | GitHub Actions |

## Project Structure

```
demoApi/
├── src/main/java/com/example/demo/
│   ├── DemoApplication.java          # Spring Boot entry point
│   ├── StreamLambdaHandler.java      # AWS Lambda handler
│   ├── api/
│   │   ├── controller/
│   │   │   └── UserController.java   # REST endpoints
│   │   └── model/
│   │       └── User.java             # User entity
│   └── service/
│       └── UserService.java          # Business logic
├── .github/workflows/
│   ├── maven.yml                     # Build & test pipeline
│   └── deploy-lambda.yml             # Deployment pipeline
├── template.yaml                     # SAM template
├── samconfig.toml                    # SAM configuration
└── pom.xml                           # Maven configuration
```

## API Endpoints

### Get User by ID

```
GET /user/{id}
```

**Headers:**
| Header | Required | Description |
|--------|----------|-------------|
| X-API-Key | Yes | API key for authentication |

**Response:**
```json
{
  "id": 1,
  "name": "Lore",
  "age": 34
}
```

**Example:**
```bash
curl -X GET "https://<api-gateway-url>/Prod/user/1" \
  -H "X-API-Key: your-secret-api-key"
```

## Getting Started

### Prerequisites

- Java 17
- Maven 3.8+
- AWS CLI (for deployment)
- AWS SAM CLI (for deployment)

### Local Development

1. **Clone the repository:**
   ```bash
   git clone https://github.com/lmarc02/demoApi.git
   cd demoApi
   ```

2. **Build the project:**
   ```bash
   ./mvnw clean package
   ```

3. **Run locally with embedded Tomcat:**
   ```bash
   ./mvnw spring-boot:run -Plocal
   ```

4. **Test the API:**
   ```bash
   curl -X GET "http://localhost:8080/user/1" \
     -H "X-API-Key: your-secret-api-key"
   ```

### Running Tests

```bash
./mvnw clean test
```

## Deployment

### Automated Deployment (CI/CD)

Pushing to `master` triggers automatic deployment via GitHub Actions:

1. Code is checked out
2. Maven builds the shaded JAR
3. AWS credentials are configured from GitHub Secrets
4. SAM builds and deploys to AWS Lambda

### Required GitHub Secrets

| Secret | Description |
|--------|-------------|
| `AWS_ACCESS_KEY_ID` | AWS access key |
| `AWS_SECRET_ACCESS_KEY` | AWS secret key |
| `AWS_REGION` | AWS region (e.g., `us-east-1`) |

### Manual Deployment

1. **Configure AWS credentials:**
   ```bash
   aws configure
   ```

2. **Build the application:**
   ```bash
   ./mvnw clean package -DskipTests
   ```

3. **Build with SAM:**
   ```bash
   sam build
   ```

4. **Deploy to AWS:**
   ```bash
   sam deploy --guided
   ```

## AWS Infrastructure

The application deploys the following resources:

- **Lambda Function:** `demo-api`
  - Runtime: Java 17
  - Memory: 512 MB
  - Timeout: 30 seconds

- **API Gateway:** REST API with proxy integration
  - Routes all HTTP methods to Lambda

## Configuration

### Maven Profiles

| Profile | Description |
|---------|-------------|
| (default) | Lambda deployment (no Tomcat) |
| `local` | Local development with embedded Tomcat |

### Lambda Configuration

Edit `template.yaml` to modify:
- Memory allocation
- Timeout settings
- Environment variables

## License

This project is for demonstration purposes.
