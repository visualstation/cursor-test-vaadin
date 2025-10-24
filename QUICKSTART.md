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

### Navigation

The application has three main sections:

1. **Intervention Logs** (Home) - View all intervention logs
2. **Sessions** - View all customer sessions
3. **Customers** - View all customers

Use the navigation buttons at the top to switch between views.

### Main Features

#### Customers View (`/customers`)
- View all registered customers
- See customer details (code, name, email, phone, address)
- See how many sessions each customer has

#### Sessions View (`/sessions`)
- View all sessions across all customers
- See session details (ID, customer, start/end time, status, description)
- See how many intervention logs are in each session
- Filter by status (ACTIVE, COMPLETED, CANCELLED)

#### Intervention Logs View (Home `/`)
- View all intervention logs organized by customer and session
- Sortable columns for easy data analysis
- Filter by username or description
- All logs are linked to their respective sessions and customers

### Features per View

**Intervention Logs:**
- **Filter**: Search box to filter by username or description
- **Refresh**: Reload data from the database
- **Sort**: Click column headers to sort

**Sessions & Customers:**
- **View Details**: See all related information
- **Sort**: Click column headers to sort
- **Navigate**: Click navigation buttons to switch views

**All Views:**
- **Logout**: Available in the top-right corner

## 📦 What's Included

### Sample Data
The application comes pre-loaded with:
- **3 Customers**: KRS Logistics, Acme Corporation, TechCorp Solutions
- **7 Sessions**: Monthly IT support sessions for KRS Logistics (April-October 2025)
- **80+ Intervention Logs**: All linked to sessions, spanning from April to October 2025

### Data Model
- **Customers** have multiple **Sessions**
- **Sessions** belong to a **Customer** and contain multiple **Intervention Logs**
- **Intervention Logs** are linked to a **Session** (and indirectly to a Customer)

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

### Adding More Data

Edit `DataInitializer.java` to add:
- **Customers**: Add to `createSampleCustomers()` method
- **Sessions**: Add to `createSampleSessions()` method
- **Logs**: Add to the `logData` array in `loadSampleLogs()`

### Changing Users
Edit `DataInitializer.java` in the `run()` method to modify the default users or add new ones.

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
