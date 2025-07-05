# MotorPH Payroll System - Simplified Class Diagram

```mermaid
classDiagram
    %% Core Model Classes
    class User {
        -String id
        -String username
        -String password
        -String role
        +authenticateUser() User
    }

    class Employee {
        -String employeeId
        -String firstName
        -String lastName
        -String position
        -double basicSalary
        -double hourlyRate
        +getFullName() String
        +calculateHourlyRate() double
    }

    class LeaveRequest {
        -String id
        -String employeeId
        -String leaveType
        -LocalDate startDate
        -LocalDate endDate
        -String status
    }

    class Payroll {
        -String payrollId
        -String employeeId
        -LocalDate periodStart
        -LocalDate periodEnd
        -double workingHours
        -double grossIncome
        -double totalDeductions
        -double netSalary
        +calculateNetSalary() double
    }

    class Attendance {
        -String employeeId
        -LocalDate date
        -LocalTime timeIn
        -LocalTime timeOut
        +getHoursWorked() double
    }

    %% Key Service Classes
    class AuthenticationService {
        -UserDAO userDAO
        +authenticateUser(String, String) User
    }

    class EmployeeService {
        -EmployeeDAO employeeDAO
        -UserDAO userDAO
        +createEmployee(Employee, String, String) boolean
        +getAllEmployees() List~Employee~
        +updateEmployee(Employee) boolean
        +deleteEmployee(String) boolean
    }

    class PayrollService {
        -PayrollDAO payrollDAO
        -AttendanceDAO attendanceDAO
        +calculatePayroll(String, LocalDate, LocalDate) Payroll
        +processPayroll(Payroll) boolean
    }

    class LeaveRequestService {
        -LeaveRequestDAO leaveRequestDAO
        +submitLeaveRequest(String, String, LocalDate, LocalDate) boolean
        +updateLeaveRequestStatus(String, String) boolean
    }

    %% Key DAO Classes
    class UserDAO {
        +authenticateUser(String, String) User
        +createUser(User) boolean
    }

    class EmployeeDAO {
        +getAllEmployees() List~Employee~
        +createEmployee(Employee) boolean
        +updateEmployee(Employee) boolean
    }

    class PayrollDAO {
        +getAllPayrolls() List~Payroll~
        +createPayroll(Payroll) boolean
    }

    %% Main GUI Classes
    class LoginForm {
        -LoginController loginController
        +handleLogin() void
    }

    class HRManagerDB {
        -EmployeeService employeeService
        -LeaveRequestService leaveRequestService
        +loadEmployeeData() void
        +manageEmployees() void
    }

    class EmployeeDB {
        -EmployeeService employeeService
        -PayrollService payrollService
        +viewPayroll() void
        +requestLeave() void
    }

    class PayrollStaffDB {
        -PayrollService payrollService
        +processPayroll() void
        +calculateSalary() void
    }

    class LoginController {
        -AuthenticationService authService
        +handleLogin(String, String, JFrame) void
    }

    %% Core Relationships
    User ||--|| Employee : "1:1"
    Employee ||--o{ LeaveRequest : "1:many"
    Employee ||--o{ Attendance : "1:many"
    Employee ||--o{ Payroll : "1:many"

    %% Service Dependencies
    AuthenticationService --> UserDAO : uses
    EmployeeService --> EmployeeDAO : uses
    EmployeeService --> UserDAO : uses
    PayrollService --> PayrollDAO : uses
    LeaveRequestService --> LeaveRequestDAO : uses

    %% Controller Dependencies
    LoginController --> AuthenticationService : uses

    %% GUI Dependencies
    LoginForm --> LoginController : uses
    HRManagerDB --> EmployeeService : uses
    HRManagerDB --> LeaveRequestService : uses
    EmployeeDB --> EmployeeService : uses
    EmployeeDB --> PayrollService : uses
    PayrollStaffDB --> PayrollService : uses

    %% GUI Navigation
    LoginForm --> HRManagerDB : "HR Manager"
    LoginForm --> EmployeeDB : "Employee"
    LoginForm --> PayrollStaffDB : "Payroll Staff"

    %% Model Usage
    UserDAO ..> User : creates
    EmployeeDAO ..> Employee : creates
    PayrollDAO ..> Payroll : creates
```

## Key Design Patterns Shown

### **1. Layered Architecture**
- **Presentation Layer**: GUI classes (LoginForm, HRManagerDB, etc.)
- **Controller Layer**: LoginController
- **Service Layer**: Business logic services
- **Data Access Layer**: DAO classes
- **Model Layer**: Entity classes

### **2. MVC Pattern**
- **Model**: User, Employee, Payroll, etc.
- **View**: GUI forms
- **Controller**: LoginController

### **3. DAO Pattern**
- Abstracts database operations
- Clean separation between business logic and data access

### **4. Service Layer Pattern**
- Encapsulates business logic
- Coordinates between DAOs and controllers

### **5. Normalized Database Design**
- User and Employee tables are separate (1:1 relationship)
- Eliminates data redundancy
- Maintains referential integrity

## Architecture Benefits

✅ **Maintainable**: Clear separation of concerns  
✅ **Scalable**: Easy to add new features  
✅ **Testable**: Each layer can be tested independently  
✅ **Secure**: Authentication separated from employee data  
✅ **Flexible**: Database views eliminate complex JOIN queries