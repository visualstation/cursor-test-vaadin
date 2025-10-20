# Quick Start Guide

Get the Customer Management System running in 5 minutes!

## Prerequisites Check

Verify you have Java 17+:
```bash
java -version
```

If not installed, download from: https://adoptium.net/

## Step 1: Get the Code

If you're reading this, you already have the code! Skip to Step 2.

If cloning from repository:
```bash
git clone <repository-url>
cd vaadin-ldap-app
```

## Step 2: Run the Application

### Option A: Using Maven (if installed)
```bash
mvn spring-boot:run
```

### Option B: Using Your IDE

**IntelliJ IDEA:**
1. Open the project folder
2. Find `VaadinLdapApplication.java`
3. Right-click → Run

**VS Code:**
1. Open the project folder
2. Press F5 or click Run → Start Debugging

**Eclipse:**
1. Import as Maven project
2. Right-click project → Run As → Spring Boot App

### Option C: Build and Run JAR
```bash
mvn clean package
java -jar target/vaadin-ldap-app-1.0.0.jar
```

## Step 3: Access the Application

Open your browser and go to:
```
http://localhost:8080
```

## Step 4: Login

Use these demo credentials:

**Admin User:**
```
Username: admin
Password: admin123
```

**Regular User:**
```
Username: user
Password: user123
```

## Step 5: Explore Features

### Search Customers
1. You'll land on the search page automatically
2. Try searching for "john", "new york", or any customer detail
3. Click column headers to sort
4. Click the trash icon to delete a customer (with confirmation)

### Add Customer
1. Click "Add Customer" in the side menu
2. Fill in the form (First Name, Last Name, and Email are required)
3. Click "Save Customer"
4. You'll see a success notification
5. Go back to search to see your new customer

### Logout
1. Click "Logout" button in the top-right corner
2. You'll be redirected to the login page

## What's Running?

- **Web Server:** Spring Boot Tomcat on port 8080
- **Database:** H2 in-memory database
- **LDAP Server:** Embedded LDAP on port 8389
- **Frontend:** Vaadin 24 with reactive backend

## Sample Data

The application comes with 3 pre-loaded customers:
- John Doe - john.doe@example.com
- Jane Smith - jane.smith@example.com
- Bob Johnson - bob.johnson@example.com

## Stopping the Application

Press `Ctrl + C` in the terminal where the application is running.

## Troubleshooting

### Port 8080 already in use?
Run with a different port:
```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8081
```
Then access at `http://localhost:8081`

### Maven not found?
Install Maven: https://maven.apache.org/install.html
Or use your IDE to run the project.

### Application won't start?
1. Check Java version: `java -version` (should be 17+)
2. Check if port 8080 is free
3. Check console for error messages

### Can't login?
Make sure you're using the correct credentials:
- Username: `admin`
- Password: `admin123`

(lowercase, no spaces)

## Next Steps

Once you're comfortable with the basics:

1. **Read the README.md** for detailed documentation
2. **Check BUILD.md** for production build instructions
3. **Review PROJECT_SUMMARY.md** for technical details
4. **Customize** the application for your needs

## Configuration Files

Want to change settings?

- **Port:** `src/main/resources/application.properties`
- **LDAP Users:** `src/main/resources/ldap-test-server.ldif`
- **Database Schema:** `src/main/resources/schema.sql`
- **Dependencies:** `pom.xml`

## Development Mode

The application runs in development mode by default with:
- Auto-reload on code changes (Spring DevTools)
- Detailed logging
- H2 console available at `/h2-console`

## Production Deployment

For production use:
1. Configure external LDAP server
2. Use production database (PostgreSQL/MySQL)
3. Build with production profile: `mvn clean package -Pproduction`
4. Set `vaadin.productionMode=true` in application.properties

See BUILD.md for complete instructions.

## Getting Help

- Check the documentation files in this project
- Review the code comments
- Check Spring Boot docs: https://spring.io/projects/spring-boot
- Check Vaadin docs: https://vaadin.com/docs

## Architecture Overview

```
Browser (Any) 
    ↓
Vaadin UI (Java-based)
    ↓
Spring Boot Backend (Reactive)
    ↓
R2DBC (Reactive Database)
    ↓
H2 Database (In-Memory)

    +
    
LDAP Authentication (Embedded)
```

## Features at a Glance

✅ Beautiful login page  
✅ Customer search with real-time filtering  
✅ Add customers with validation  
✅ Delete customers with confirmation  
✅ Sortable data grid  
✅ Responsive design  
✅ Browser independent  
✅ Fully reactive backend  
✅ LDAP security  

---

**Congratulations! You're now running a modern, reactive customer management system!** 🎉

For more details, see [README.md](README.md)
