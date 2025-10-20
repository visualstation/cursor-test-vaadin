# Build Instructions

This document provides detailed instructions for building and running the Customer Management System.

## Prerequisites

Ensure you have the following installed:

1. **Java 17 or higher**
   ```bash
   java -version
   ```

2. **Maven 3.6 or higher** (Optional - can use IDE)
   ```bash
   mvn -version
   ```

## Build Methods

### Method 1: Using Maven (Recommended)

```bash
# Clean and build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Build for production
mvn clean package -Pproduction
```

### Method 2: Using IDE (IntelliJ IDEA / Eclipse / VS Code)

#### IntelliJ IDEA:
1. Open the project folder in IntelliJ IDEA
2. Wait for Maven to import dependencies
3. Right-click on `VaadinLdapApplication.java`
4. Select "Run 'VaadinLdapApplication'"

#### Eclipse:
1. Import as "Existing Maven Project"
2. Right-click project → Run As → Spring Boot App

#### VS Code:
1. Install "Extension Pack for Java" and "Spring Boot Extension Pack"
2. Open the project folder
3. Press F5 or use Run → Start Debugging

### Method 3: Using Docker (Optional)

Create a `Dockerfile` in the project root:

```dockerfile
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -Pproduction

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Build and run:
```bash
docker build -t vaadin-ldap-app .
docker run -p 8080:8080 vaadin-ldap-app
```

## Troubleshooting Build Issues

### Issue: Dependencies not downloading
**Solution:** Check internet connection and Maven settings. Try:
```bash
mvn clean install -U
```

### Issue: Compilation errors
**Solution:** Ensure Java 17 is being used:
```bash
export JAVA_HOME=/path/to/java17
mvn clean compile
```

### Issue: Vaadin frontend build fails
**Solution:** Ensure Node.js is installed (optional, Vaadin can work without it):
```bash
mvn clean install -DskipTests
```

### Issue: Out of memory during build
**Solution:** Increase Maven memory:
```bash
export MAVEN_OPTS="-Xmx1024m"
mvn clean install
```

## Verifying the Build

After successful build, you should see:
```
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: XX s
```

The compiled JAR will be located at:
```
target/vaadin-ldap-app-1.0.0.jar
```

## Running the Application

### Development Mode:
```bash
mvn spring-boot:run
```
Access at: http://localhost:8080

### Production Mode:
```bash
java -jar target/vaadin-ldap-app-1.0.0.jar
```

### With Custom Port:
```bash
java -jar target/vaadin-ldap-app-1.0.0.jar --server.port=9090
```

## Performance Optimization

### Production Build Optimizations:
1. Enable production mode in `application.properties`
2. Use production profile: `mvn clean package -Pproduction`
3. Vaadin will minify and bundle frontend resources
4. Use external database instead of H2

### JVM Tuning:
```bash
java -Xms512m -Xmx2048m -jar target/vaadin-ldap-app-1.0.0.jar
```

## Next Steps

Once built successfully:
1. Read the [README.md](README.md) for usage instructions
2. Login with demo credentials (admin/admin123)
3. Explore the customer management features
4. Configure production LDAP and database settings
