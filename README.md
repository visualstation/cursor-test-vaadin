# Intervention Log Viewer

A Spring Boot application with Vaadin that displays intervention logs in a table format with authentication.

## Features

- **Authentication**: Secure login system with Spring Security
- **Database**: H2 in-memory database for storing users and intervention logs
- **Modern UI**: Vaadin-based web interface with a clean, responsive design
- **Log Management**: View and filter intervention logs by username or description

## Technologies Used

- **Spring Boot 3.2.0**: Application framework
- **Vaadin 24.3.0**: Modern web UI framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database access
- **H2 Database**: In-memory database
- **Lombok**: Reduce boilerplate code
- **Java 17**: Programming language

## Project Structure

```
src/main/java/com/example/logviewer/
├── entity/               # JPA entities (InterventionLog, User)
├── repository/           # Spring Data repositories
├── service/              # Business logic layer
├── security/             # Security configuration
├── view/                 # Vaadin UI views
├── DataInitializer.java  # Sample data loader
└── LogViewerApplication.java  # Main application class
```

## Quick Start

### Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

### Running the Application

1. Build the project:
```bash
mvn clean install
```

2. Run the application:
```bash
mvn spring-boot:run
```

3. Access the application:
   - Open your browser and navigate to: `http://localhost:8080`
   - Login with one of the default users:
     - Username: `admin` / Password: `admin`
     - Username: `user` / Password: `user`

### H2 Database Console (Optional)

You can access the H2 database console for debugging:
- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:logdb`
- Username: `sa`
- Password: (leave empty)

## Features Details

### Intervention Log Table

The main view displays all intervention logs with the following columns:
- **Date & Time**: When the intervention occurred
- **Client ID**: Unique client identifier
- **Username**: User who performed the intervention
- **Description**: Details of the intervention
- **Duration (s)**: Actual duration in seconds
- **Billed (s)**: Billed duration in seconds

### Filtering

Use the search box to filter logs by:
- Username (e.g., "krs-quentin@ad")
- Description (e.g., "Email", "Dépannage")

The filter is case-insensitive and searches both fields simultaneously.

### Sample Data

The application comes pre-loaded with 80+ intervention logs from KRS Logistics spanning from April to October 2025.

## Configuration

Main configuration file: `src/main/resources/application.properties`

Key settings:
- Server port: `8080`
- Database: H2 in-memory
- JPA auto-DDL: `create-drop` (recreates database on each restart)

## Security

The application uses Spring Security with:
- Form-based authentication
- BCrypt password encoding
- Role-based access control (ROLE_ADMIN, ROLE_USER)
- Vaadin security integration

## Building for Production

To build a production-ready JAR file:

```bash
mvn clean package -Pproduction
```

The JAR file will be created in the `target/` directory and can be run with:

```bash
java -jar target/intervention-log-viewer-1.0.0.jar
```

## Future Enhancements

Potential improvements:
- Export logs to CSV/Excel
- Advanced filtering (date range, duration range)
- Add/Edit/Delete log entries
- User management interface
- PostgreSQL/MySQL support for production
- Statistics and charts
- Multi-language support

## License

This project is open source and available under the MIT License.
