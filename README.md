# Customer Management System

A modern, reactive Java Spring Boot application with Vaadin framework and LDAP authentication for managing customer data.

## Features

- ✅ **LDAP Authentication** - Secure login using LDAP directory services
- ✅ **Reactive Architecture** - Built with Spring WebFlux and R2DBC for reactive data access
- ✅ **Modern UI** - Beautiful, responsive Vaadin 24 interface
- ✅ **Customer Management** - Search, add, and delete customer records
- ✅ **Browser Independent** - Works seamlessly across all modern browsers
- ✅ **Embedded LDAP** - Pre-configured for development with test users

## Technology Stack

- **Java 17**
- **Spring Boot 3.1.5**
- **Vaadin 24.2.5** - Modern web UI framework
- **Spring Security** - With LDAP authentication
- **R2DBC** - Reactive database connectivity
- **H2 Database** - In-memory database for development
- **Maven** - Build and dependency management
- **Lombok** - Reduce boilerplate code

## Prerequisites

- Java 17 or higher
- Maven 3.6 or higher
- Modern web browser (Chrome, Firefox, Safari, Edge)

## Project Structure

```
vaadin-ldap-app/
├── src/
│   ├── main/
│   │   ├── java/com/example/vaadinapp/
│   │   │   ├── config/
│   │   │   │   └── SecurityConfig.java         # Security & LDAP configuration
│   │   │   ├── model/
│   │   │   │   └── Customer.java               # Customer entity
│   │   │   ├── repository/
│   │   │   │   └── CustomerRepository.java     # Reactive R2DBC repository
│   │   │   ├── service/
│   │   │   │   └── CustomerService.java        # Business logic layer
│   │   │   ├── views/
│   │   │   │   ├── LoginView.java              # Login page
│   │   │   │   ├── MainLayout.java             # Main layout with navigation
│   │   │   │   ├── SearchView.java             # Customer search page
│   │   │   │   └── AddCustomerView.java        # Add customer page
│   │   │   └── VaadinLdapApplication.java      # Main application class
│   │   └── resources/
│   │       ├── application.properties          # Application configuration
│   │       ├── ldap-test-server.ldif          # LDAP test data
│   │       └── schema.sql                      # Database schema & sample data
│   └── test/
├── pom.xml                                      # Maven configuration
└── README.md
```

## Quick Start

### 1. Clone and Build

```bash
git clone <repository-url>
cd vaadin-ldap-app
mvn clean install
```

### 2. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### 3. Login

The application comes with pre-configured test users in the embedded LDAP server:

**Admin User:**
- Username: `admin`
- Password: `admin123`

**Regular User:**
- Username: `user`
- Password: `user123`

## Application Features

### 1. Login Page
- Modern, clean login interface
- LDAP authentication
- Error handling for invalid credentials
- Responsive design

### 2. Customer Search
- Real-time search across all customer fields
- Sortable columns
- Delete functionality with confirmation
- Responsive grid layout

### 3. Add Customer
- Comprehensive form with validation
- Required field indicators
- Email validation
- Success/error notifications
- Clear and cancel options

### 4. Navigation
- Side drawer navigation
- User information display
- Logout functionality
- Breadcrumb navigation

## Configuration

### LDAP Configuration

The application uses an embedded LDAP server for development. For production, update `application.properties`:

```properties
# Comment out embedded LDAP settings
# spring.ldap.embedded.ldif=classpath:ldap-test-server.ldif
# spring.ldap.embedded.base-dn=dc=example,dc=com
# spring.ldap.embedded.port=8389

# Configure production LDAP
spring.ldap.urls=ldap://your-ldap-server:389
spring.ldap.base=dc=yourcompany,dc=com
spring.ldap.username=cn=admin,dc=yourcompany,dc=com
spring.ldap.password=your-admin-password
```

### Database Configuration

The application uses H2 in-memory database for development. For production, configure R2DBC with your database:

**PostgreSQL:**
```properties
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/customerdb
spring.r2dbc.username=postgres
spring.r2dbc.password=password
```

**MySQL:**
```properties
spring.r2dbc.url=r2dbc:mysql://localhost:3306/customerdb
spring.r2dbc.username=root
spring.r2dbc.password=password
```

Add the corresponding R2DBC driver dependency to `pom.xml`.

## Building for Production

### 1. Build Production Package

```bash
mvn clean package -Pproduction
```

### 2. Run Production Build

```bash
java -jar target/vaadin-ldap-app-1.0.0.jar
```

### 3. Configure Production Mode

Update `application.properties`:

```properties
vaadin.productionMode=true
```

## API Endpoints

While this is primarily a Vaadin application, the reactive backend can be exposed via REST APIs:

- `GET /api/customers` - Get all customers
- `GET /api/customers/{id}` - Get customer by ID
- `POST /api/customers` - Create new customer
- `PUT /api/customers/{id}` - Update customer
- `DELETE /api/customers/{id}` - Delete customer
- `GET /api/customers/search?term=xyz` - Search customers

## Development

### Hot Reload

The application uses Spring Boot DevTools for automatic restart on code changes.

### H2 Console

Access the H2 console at `http://localhost:8080/h2-console` (development only):
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (leave empty)

### Testing

Run tests:
```bash
mvn test
```

## Browser Compatibility

The application is fully compatible with:
- ✅ Google Chrome (latest)
- ✅ Mozilla Firefox (latest)
- ✅ Safari (latest)
- ✅ Microsoft Edge (latest)
- ✅ Opera (latest)

## Security Features

- LDAP-based authentication
- Session management
- CSRF protection
- XSS protection
- Secure password handling
- Role-based access control ready

## Troubleshooting

### Port Already in Use
If port 8080 is already in use, change it in `application.properties`:
```properties
server.port=8081
```

### LDAP Connection Issues
Check the LDAP configuration in `application.properties` and ensure the LDAP server is accessible.

### Database Issues
Verify R2DBC connection URL and credentials. Check database server is running.

## Future Enhancements

- [ ] Customer edit functionality
- [ ] Export to CSV/Excel
- [ ] Advanced filtering options
- [ ] Pagination for large datasets
- [ ] Customer categories/tags
- [ ] Audit logging
- [ ] Multi-language support
- [ ] Dark mode theme

## License

This project is licensed under the MIT License.

## Support

For issues and questions, please create an issue in the repository.

## Author

Created with ❤️ using Spring Boot and Vaadin

---

**Note:** This application uses embedded LDAP and H2 database for development purposes. For production deployment, configure external LDAP server and a production-grade database.
