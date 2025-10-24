# Intervention Log Viewer

A Spring Boot application with Vaadin that manages customers, sessions, and intervention logs with authentication.

## Features

- **Authentication**: Secure login system with Spring Security
- **Database**: H2 in-memory database with full relational model
- **Customer Management**: Track customers with contact information
- **Session Management**: Organize interventions into sessions per customer
- **Log Management**: View and filter intervention logs linked to sessions
- **Modern UI**: Vaadin-based web interface with clean, responsive design
- **Multi-View Navigation**: Separate views for customers, sessions, and logs

## Technologies Used

- **Spring Boot 3.2.0**: Application framework
- **Vaadin 24.3.0**: Modern web UI framework
- **Spring Security**: Authentication and authorization
- **Spring Data JPA**: Database access
- **H2 Database**: In-memory database
- **Lombok**: Reduce boilerplate code
- **Java 17**: Programming language

## Data Model

The application uses a relational data model:

- **Customer** → has many **Sessions**
- **Session** → belongs to **Customer**, has many **InterventionLogs**
- **InterventionLog** → belongs to **Session**

This allows you to:
- Track multiple customers
- Organize work into sessions per customer
- Link all intervention logs to specific sessions

## Project Structure

```
src/main/java/com/example/logviewer/
├── entity/               # JPA entities (Customer, Session, InterventionLog, User)
├── repository/           # Spring Data repositories
├── service/              # Business logic layer
├── security/             # Security configuration
├── view/                 # Vaadin UI views
│   ├── LoginView.java           # Login page
│   ├── MainView.java            # Intervention logs table
│   ├── SessionListView.java     # Sessions table
│   └── CustomerListView.java    # Customers table
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

### Navigation

The application has three main views accessible via navigation buttons:
1. **Intervention Logs** - Main page showing all intervention logs
2. **Sessions** - List of all customer sessions
3. **Customers** - List of all customers

### Customers View

Displays all customers with:
- **Customer Code**: Unique identifier
- **Name**: Customer company name
- **Email**: Contact email
- **Phone**: Contact phone number
- **Address**: Customer location
- **Sessions**: Number of sessions for this customer

### Sessions View

Shows all sessions with:
- **Session ID**: Unique session identifier
- **Customer**: Associated customer name
- **Start Time**: When the session began
- **End Time**: When the session ended (or "N/A" for active sessions)
- **Status**: ACTIVE, COMPLETED, or CANCELLED
- **Description**: Session description
- **Logs**: Number of intervention logs in this session

### Intervention Logs View

The main view displays all intervention logs with:
- **Customer**: Customer name (from linked session)
- **Session ID**: Associated session identifier
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

The application comes pre-loaded with:
- **3 customers** (KRS Logistics, Acme Corporation, TechCorp Solutions)
- **7 sessions** for KRS Logistics (April through October 2025)
- **80+ intervention logs** linked to these sessions

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
