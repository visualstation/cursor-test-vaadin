# Quick Start Guide

## 🚀 Running the Application

### Option 1: Using the run script
```bash
./run.sh
```

### Option 2: Using Maven directly
```bash
mvn spring-boot:run
```

## 🌐 Accessing the Application

Once the application is running:

1. **Open your browser** and navigate to: `http://localhost:8080`

2. **Login** with one of the default users:
   - **Username**: `admin` | **Password**: `admin`
   - **Username**: `user` | **Password**: `user`

3. You'll see the **Intervention Logs** page with all the sample data displayed in a table

## 📊 Using the Application

### Main Features

1. **View Logs**: All intervention logs are displayed in a sortable table
   - Click on column headers to sort by that column
   - The table displays:
     - Date & Time
     - Client ID
     - Username
     - Description
     - Duration (seconds)
     - Billed Duration (seconds)

2. **Filter Logs**: Use the search box at the top to filter by:
   - Username (e.g., "krs-quentin@ad")
   - Description (e.g., "Email", "Dépannage")

3. **Refresh**: Click the "Refresh" button to reload the data

4. **Logout**: Click the "Logout" button in the top-right corner

## 📦 What's Included

### Sample Data
The application comes pre-loaded with **80+ intervention logs** from KRS Logistics, spanning from April to October 2025.

### Technologies
- **Spring Boot 3.2.0**: Backend framework
- **Vaadin 24.3.0**: Modern web UI framework
- **Spring Security**: Authentication and authorization
- **H2 Database**: In-memory database (data resets on restart)
- **Java 17+**: Programming language

## 🛠️ Development

### Project Structure
```
src/main/java/com/example/logviewer/
├── entity/               # Database entities
│   ├── InterventionLog.java
│   └── User.java
├── repository/           # Data access layer
│   ├── InterventionLogRepository.java
│   └── UserRepository.java
├── service/              # Business logic
│   ├── InterventionLogService.java
│   └── UserDetailsServiceImpl.java
├── security/             # Security configuration
│   └── SecurityConfig.java
├── view/                 # Vaadin UI components
│   ├── LoginView.java
│   └── MainView.java
├── DataInitializer.java  # Sample data loader
└── LogViewerApplication.java  # Main application
```

### Building for Production
```bash
mvn clean package -Pproduction
```

This creates a JAR file in `target/` that can be run with:
```bash
java -jar target/intervention-log-viewer-1.0.0.jar
```

## 🔧 Configuration

Main configuration is in `src/main/resources/application.properties`:
- Server port: `8080`
- Database: H2 in-memory (data resets on restart)
- H2 Console: Available at `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:logdb`
  - Username: `sa`
  - Password: (empty)

## 📝 Customization

### Adding More Logs
Edit `DataInitializer.java` and add more log entries to the `logData` array.

### Changing Users
Edit `DataInitializer.java` to modify the default users or add new ones.

### Using a Different Database
Update `application.properties` to use PostgreSQL, MySQL, etc.:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/logdb
spring.datasource.username=youruser
spring.datasource.password=yourpassword
```

## 🐛 Troubleshooting

### Port 8080 already in use
Change the port in `application.properties`:
```properties
server.port=8081
```

### Build fails
Make sure you have Java 17+ and Maven installed:
```bash
java --version
mvn --version
```

## 📚 Additional Resources

- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Vaadin Documentation](https://vaadin.com/docs/latest)
- [Spring Security Documentation](https://docs.spring.io/spring-security/reference/)

## 💡 Next Steps

Consider adding:
- Export to CSV/Excel functionality
- Date range filtering
- User management interface
- Database persistence (PostgreSQL/MySQL)
- REST API endpoints
- Charts and statistics
