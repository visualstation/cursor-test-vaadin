# Changelog

## Version 2.0 - Customer and Session Management

### New Features

#### Data Model Enhancement
- **Customer Entity**: Added customer management with contact information
  - Customer Code (unique identifier)
  - Name, Email, Phone, Address
  - One-to-many relationship with Sessions

- **Session Entity**: Added session management for organizing interventions
  - Session ID (unique identifier)
  - Belongs to a Customer
  - Start/End time tracking
  - Status tracking (ACTIVE, COMPLETED, CANCELLED)
  - Description
  - One-to-many relationship with Intervention Logs

- **Updated InterventionLog Entity**: Now linked to Sessions
  - Many-to-one relationship with Session
  - Maintains backward compatibility with existing fields

#### New Views
- **CustomerListView** (`/customers`): View and manage all customers
- **SessionListView** (`/sessions`): View and manage all sessions
- **Enhanced MainView**: Now displays customer and session information in the logs table

#### Navigation
- Added navigation buttons to all views
- Easy switching between Customers, Sessions, and Intervention Logs

#### Repositories & Services
- `CustomerRepository` and `CustomerService`: Customer data access
- `SessionRepository` and `SessionService`: Session data access
- Enhanced query capabilities for filtering by customer, status, etc.

#### Sample Data
- 3 sample customers (KRS Logistics, Acme Corp, TechCorp)
- 7 sample sessions for KRS Logistics (one per month from April-October 2025)
- All 80+ existing intervention logs now properly linked to sessions

### Technical Improvements
- Full JPA relationships with cascade operations
- Lazy loading for optimized performance
- Clean separation of concerns with dedicated services

### Migration Notes
- The database schema has changed significantly
- H2 database will auto-recreate on application startup
- All data is re-initialized from `DataInitializer.java`

---

## Version 1.0 - Initial Release

### Features
- Basic intervention log management
- User authentication with Spring Security
- Vaadin UI with sortable table
- Filter functionality
- H2 in-memory database
- 80+ sample intervention logs
