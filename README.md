# MotorPH Payroll System - OOP Refactored

![Status](https://img.shields.io/badge/status-Refactored-brightgreen)  
**Refactored with proper OOP principles and clean architecture**

---

## Table of Contents
- [Project Description](#project-description)
- [Architecture Overview](#architecture-overview)
- [Package Structure](#package-structure)
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Design Patterns Used](#design-patterns-used)

---

## Project Description
The **MotorPH Payroll System** has been completely refactored to follow proper Object-Oriented Programming (OOP) principles and clean architecture patterns. The system now features proper separation of concerns, maintainable code structure, and follows industry best practices.

---

## Architecture Overview

The system follows a layered architecture pattern:

```
┌─────────────────┐
│   View Layer    │  ← User Interface (Swing GUI)
├─────────────────┤
│ Controller Layer│  ← Business Logic Controllers
├─────────────────┤
│ Service Layer   │  ← Business Logic Services
├─────────────────┤
│   DAO Layer     │  ← Data Access Objects
├─────────────────┤
│  Model Layer    │  ← Domain Models/Entities
└─────────────────┘
```

---

## Package Structure

```
src/main/java/com/motorph/
├── model/              # Domain models and entities
│   ├── Employee.java
│   ├── User.java
│   ├── LeaveRequest.java
│   ├── Payroll.java
│   ├── Attendance.java
│   └── Benefits.java
├── dao/                # Data Access Objects
│   ├── DatabaseConnection.java
│   ├── UserDAO.java
│   ├── EmployeeDAO.java
│   ├── LeaveRequestDAO.java
│   ├── PayrollDAO.java
│   ├── AttendanceDAO.java
│   └── BenefitsDAO.java
├── service/            # Business Logic Services
│   ├── AuthenticationService.java
│   ├── EmployeeService.java
│   ├── LeaveRequestService.java
│   ├── PayrollService.java
│   ├── PayrollCalculationService.java
│   └── ReportService.java
├── controller/         # Controllers
│   └── LoginController.java
└── view/              # User Interface
    ├── LoginForm.java
    ├── HRManagerDashboard.java
    ├── EmployeeDashboard.java
    └── PayrollStaffDashboard.java
```

---

## Features

### Core Functionality
- **Secure Authentication** - Role-based login system
- **Employee Management** - CRUD operations for employee records
- **Leave Management** - Submit and approve/reject leave requests
- **Payroll Processing** - Automated payroll calculations with deductions and benefits
- **Report Generation** - Generate payslips using JasperReports

### OOP Principles Applied
- **Encapsulation** - Private fields with public getters/setters
- **Inheritance** - Common base functionality where appropriate
- **Polymorphism** - Interface-based programming
- **Abstraction** - Clear separation between interface and implementation

### Design Patterns
- **DAO Pattern** - Data access abstraction
- **Service Layer Pattern** - Business logic separation
- **MVC Pattern** - Model-View-Controller architecture
- **Singleton Pattern** - Database connection management
- **Factory Pattern** - Object creation abstraction

---

## Tech Stack

- **Language:** Java 17+
- **GUI Framework:** Swing
- **Database:** SQLite
- **Build Tool:** Maven
- **Reporting:** JasperReports
- **Architecture:** Layered Architecture with MVC

---

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- SQLite database file (`MotorPH Payroll System.db`)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd motorph-payroll-system
   ```

2. **Build the project**
   ```bash
   mvn clean compile
   ```

3. **Run the application**
   ```bash
   mvn exec:java
   ```

---

## Usage

### Login Credentials
The system supports three user roles:
- **HR Manager** - Full access to employee and leave management
- **Payroll Staff** - Access to payroll processing and employee viewing
- **Employee** - Access to personal information, leave requests, and payslips

### Key Operations

#### HR Manager
- Create, update, and delete employee records
- Approve or reject leave requests
- View employee attendance records

#### Payroll Staff
- Calculate and process employee payroll
- Generate payslips
- View employee information

#### Employee
- View personal information
- Submit leave requests
- View payroll history and generate payslips

---

## Design Patterns Used

### 1. Data Access Object (DAO) Pattern
```java
public interface EmployeeDAO {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(String id);
    boolean createEmployee(Employee employee);
    boolean updateEmployee(Employee employee);
    boolean deleteEmployee(String id);
}
```

### 2. Service Layer Pattern
```java
public class EmployeeService {
    private final EmployeeDAO employeeDAO;
    
    public boolean createEmployee(Employee employee, String password, String role) {
        // Business logic here
    }
}
```

### 3. Model-View-Controller (MVC)
- **Models**: Domain entities (Employee, Payroll, etc.)
- **Views**: Swing GUI components
- **Controllers**: Handle user interactions and coordinate between views and services

### 4. Singleton Pattern
```java
public class DatabaseConnection {
    private static Connection connection = null;
    
    public static Connection getConnection() {
        // Singleton implementation
    }
}
```

---

## Benefits of the Refactored Architecture

### Maintainability
- **Single Responsibility**: Each class has one clear purpose
- **Loose Coupling**: Components are independent and easily replaceable
- **High Cohesion**: Related functionality is grouped together

### Testability
- **Dependency Injection**: Easy to mock dependencies for testing
- **Separation of Concerns**: Business logic separated from UI and data access
- **Interface-based Design**: Easy to create test doubles

### Scalability
- **Modular Design**: Easy to add new features without affecting existing code
- **Layered Architecture**: Clear boundaries between different concerns
- **Service-oriented**: Business logic can be easily exposed as web services

### Code Quality
- **DRY Principle**: No code duplication
- **SOLID Principles**: All five SOLID principles are followed
- **Clean Code**: Readable, self-documenting code with meaningful names

---

## Future Enhancements

- **Web Interface**: Convert Swing GUI to web-based interface
- **REST API**: Expose services as REST endpoints
- **Database Migration**: Support for multiple database systems
- **Unit Testing**: Comprehensive test coverage
- **Logging**: Structured logging with different log levels
- **Configuration Management**: Externalized configuration
- **Security Enhancements**: Password encryption, session management

---

This refactored version demonstrates professional software development practices and serves as an excellent example of how to properly structure a Java application using OOP principles and design patterns.