# MotorPH Payroll System - Class Diagram

## System Architecture Overview

The MotorPH Payroll System follows a layered architecture with clear separation of concerns:

```
┌─────────────────────────────────────────────────────────────────────────────────┐
│                                VIEW LAYER                                       │
├─────────────────────────────────────────────────────────────────────────────────┤
│  LoginForm          HRManagerDashboard    EmployeeDashboard    PayrollStaffDashboard │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                              CONTROLLER LAYER                                   │
├─────────────────────────────────────────────────────────────────────────────────┤
│                            LoginController                                      │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                               SERVICE LAYER                                     │
├─────────────────────────────────────────────────────────────────────────────────┤
│  AuthenticationService  EmployeeService  LeaveRequestService  PayrollService    │
│  PayrollCalculationService              ReportService                           │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                                 DAO LAYER                                       │
├─────────────────────────────────────────────────────────────────────────────────┤
│  DatabaseConnection  UserDAO  EmployeeDAO  LeaveRequestDAO  PayrollDAO         │
│  AttendanceDAO      BenefitsDAO                                                 │
└─────────────────────────────────────────────────────────────────────────────────┘
                                        │
                                        ▼
┌─────────────────────────────────────────────────────────────────────────────────┐
│                                MODEL LAYER                                      │
├─────────────────────────────────────────────────────────────────────────────────┤
│  User    Employee    LeaveRequest    Payroll    Attendance    Benefits          │
└─────────────────────────────────────────────────────────────────────────────────┘
```

## Detailed Class Diagrams

### Model Layer (Domain Objects)

```mermaid
classDiagram
    class User {
        -String id
        -String username
        -String password
        -String role
        +User()
        +User(id, username, password, role)
        +getId() String
        +setId(String)
        +getUsername() String
        +setUsername(String)
        +getPassword() String
        +setPassword(String)
        +getRole() String
        +setRole(String)
        +toString() String
    }

    class Employee {
        -String employeeId
        -String lastName
        -String firstName
        -String username
        -LocalDate birthday
        -String address
        -String phoneNumber
        -String status
        -String position
        -String department
        -String supervisor
        -double basicSalary
        -double hourlyRate
        +Employee()
        +Employee(employeeId, lastName, firstName, ...)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getLastName() String
        +setLastName(String)
        +getFirstName() String
        +setFirstName(String)
        +getFullName() String
        +getBirthday() LocalDate
        +setBirthday(LocalDate)
        +getAddress() String
        +setAddress(String)
        +getPhoneNumber() String
        +setPhoneNumber(String)
        +getStatus() String
        +setStatus(String)
        +getPosition() String
        +setPosition(String)
        +getDepartment() String
        +setDepartment(String)
        +getSupervisor() String
        +setSupervisor(String)
        +getBasicSalary() double
        +setBasicSalary(double)
        +getHourlyRate() double
        +setHourlyRate(double)
        +toString() String
    }

    class LeaveRequest {
        -String id
        -String employeeId
        -String leaveType
        -LocalDate startDate
        -LocalDate endDate
        -String status
        +LeaveRequest()
        +LeaveRequest(id, employeeId, leaveType, startDate, endDate, status)
        +getId() String
        +setId(String)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getLeaveType() String
        +setLeaveType(String)
        +getStartDate() LocalDate
        +setStartDate(LocalDate)
        +getEndDate() LocalDate
        +setEndDate(LocalDate)
        +getStatus() String
        +setStatus(String)
        +toString() String
    }

    class Payroll {
        -String payrollId
        -String employeeId
        -LocalDate periodStart
        -LocalDate periodEnd
        -double workingHours
        -double sssContribution
        -double philhealthContribution
        -double pagibigContribution
        -double withholdingTax
        -double riceSubsidy
        -double phoneAllowance
        -double clothingAllowance
        +Payroll()
        +Payroll(payrollId, employeeId, periodStart, ...)
        +getPayrollId() String
        +setPayrollId(String)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getPeriodStart() LocalDate
        +setPeriodStart(LocalDate)
        +getPeriodEnd() LocalDate
        +setPeriodEnd(LocalDate)
        +getWorkingHours() double
        +setWorkingHours(double)
        +getSssContribution() double
        +setSssContribution(double)
        +getPhilhealthContribution() double
        +setPhilhealthContribution(double)
        +getPagibigContribution() double
        +setPagibigContribution(double)
        +getWithholdingTax() double
        +setWithholdingTax(double)
        +getRiceSubsidy() double
        +setRiceSubsidy(double)
        +getPhoneAllowance() double
        +setPhoneAllowance(double)
        +getClothingAllowance() double
        +setClothingAllowance(double)
        +getGrossIncome(hourlyRate) double
        +getTotalDeductions() double
        +getTotalBenefits() double
        +getNetSalary(hourlyRate) double
        +toString() String
    }

    class Attendance {
        -String employeeId
        -LocalDate date
        -LocalTime timeIn
        -LocalTime timeOut
        +Attendance()
        +Attendance(employeeId, date, timeIn, timeOut)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getDate() LocalDate
        +setDate(LocalDate)
        +getTimeIn() LocalTime
        +setTimeIn(LocalTime)
        +getTimeOut() LocalTime
        +setTimeOut(LocalTime)
        +getHoursWorked() double
        +toString() String
    }

    class Benefits {
        -String position
        -double riceSubsidy
        -double phoneAllowance
        -double clothingAllowance
        +Benefits()
        +Benefits(position, riceSubsidy, phoneAllowance, clothingAllowance)
        +getPosition() String
        +setPosition(String)
        +getRiceSubsidy() double
        +setRiceSubsidy(double)
        +getPhoneAllowance() double
        +setPhoneAllowance(double)
        +getClothingAllowance() double
        +setClothingAllowance(double)
        +getTotalBenefits() double
        +toString() String
    }

    Employee ||--|| User : "has account"
    Employee ||--o{ LeaveRequest : "submits"
    Employee ||--o{ Payroll : "receives"
    Employee ||--o{ Attendance : "records"
    Employee }o--|| Benefits : "entitled to"
```

### DAO Layer (Data Access Objects)

```mermaid
classDiagram
    class DatabaseConnection {
        -String URL$
        -Connection connection$
        -DatabaseConnection()
        +getConnection()$ Connection
        +closeConnection()$ void
    }

    class UserDAO {
        +authenticateUser(username, password) User
        +createUser(user) boolean
        +deleteUser(userId) boolean
    }

    class EmployeeDAO {
        +getAllEmployees() List~Employee~
        +getEmployeeById(employeeId) Employee
        +getEmployeeByUsername(username) Employee
        +createEmployee(employee) boolean
        +updateEmployee(employee) boolean
        +deleteEmployee(employeeId) boolean
        -mapResultSetToEmployee(rs) Employee
    }

    class LeaveRequestDAO {
        +getAllLeaveRequests() List~LeaveRequest~
        +getLeaveRequestsByEmployeeId(employeeId) List~LeaveRequest~
        +createLeaveRequest(leaveRequest) boolean
        +updateLeaveRequestStatus(leaveRequestId, status) boolean
        -mapResultSetToLeaveRequest(rs) LeaveRequest
    }

    class PayrollDAO {
        +getAllPayrolls() List~Payroll~
        +getPayrollsByEmployeeId(employeeId) List~Payroll~
        +createPayroll(payroll) boolean
        -mapResultSetToPayroll(rs) Payroll
    }

    class AttendanceDAO {
        +getAllAttendance() List~Attendance~
        +getTotalHoursWorked(employeeId, startDate, endDate) double
        -mapResultSetToAttendance(rs) Attendance
    }

    class BenefitsDAO {
        +getBenefitsByPosition(position) Benefits
    }

    UserDAO ..> DatabaseConnection : uses
    EmployeeDAO ..> DatabaseConnection : uses
    LeaveRequestDAO ..> DatabaseConnection : uses
    PayrollDAO ..> DatabaseConnection : uses
    AttendanceDAO ..> DatabaseConnection : uses
    BenefitsDAO ..> DatabaseConnection : uses
```

### Service Layer (Business Logic)

```mermaid
classDiagram
    class AuthenticationService {
        -UserDAO userDAO
        +AuthenticationService()
        +authenticateUser(username, password) User
        +isValidRole(role) boolean
    }

    class EmployeeService {
        -EmployeeDAO employeeDAO
        -UserDAO userDAO
        +EmployeeService()
        +getAllEmployees() List~Employee~
        +getEmployeeById(employeeId) Employee
        +getEmployeeByUsername(username) Employee
        +createEmployee(employee, password, role) boolean
        +updateEmployee(employee) boolean
        +deleteEmployee(employeeId) boolean
        +isValidEmployeeId(employeeId) boolean
        +getEmployeeFullName(employeeId) String
    }

    class LeaveRequestService {
        -LeaveRequestDAO leaveRequestDAO
        +LeaveRequestService()
        +getAllLeaveRequests() List~LeaveRequest~
        +getLeaveRequestsByEmployeeId(employeeId) List~LeaveRequest~
        +submitLeaveRequest(employeeId, leaveType, startDate, endDate) boolean
        +updateLeaveRequestStatus(leaveRequestId, status) boolean
        -isValidLeaveRequest(employeeId, leaveType, startDate, endDate) boolean
        -isValidStatus(status) boolean
        -generateLeaveId(employeeId, startDate, endDate) String
    }

    class PayrollCalculationService {
        +calculateSSSContribution(compensation) double
        +calculatePhilhealthContribution(salary) double
        +calculatePagibigContribution(monthlySalary) double
        +calculateWithholdingTax(monthlySalary) double
        +calculateGrossIncome(hourlyRate, hoursWorked) double
        +calculateNetSalary(grossIncome, totalDeductions, totalBenefits) double
    }

    class PayrollService {
        -PayrollDAO payrollDAO
        -AttendanceDAO attendanceDAO
        -BenefitsDAO benefitsDAO
        -PayrollCalculationService calculationService
        -EmployeeService employeeService
        +PayrollService()
        +getAllPayrolls() List~Payroll~
        +getPayrollsByEmployeeId(employeeId) List~Payroll~
        +calculatePayroll(employeeId, periodStart, periodEnd) Payroll
        +processPayroll(payroll) boolean
        -generatePayrollId(employeeId, periodStart, periodEnd) String
    }

    class ReportService {
        +generatePayslip(payrollId) void
    }

    AuthenticationService --> UserDAO
    EmployeeService --> EmployeeDAO
    EmployeeService --> UserDAO
    LeaveRequestService --> LeaveRequestDAO
    PayrollService --> PayrollDAO
    PayrollService --> AttendanceDAO
    PayrollService --> BenefitsDAO
    PayrollService --> PayrollCalculationService
    PayrollService --> EmployeeService
```

### Controller Layer

```mermaid
classDiagram
    class LoginController {
        -AuthenticationService authService
        +LoginController()
        +handleLogin(username, password, loginFrame) void
    }

    LoginController --> AuthenticationService
```

### View Layer (User Interface)

```mermaid
classDiagram
    class LoginForm {
        -JTextField txtUsername
        -JPasswordField txtPassword
        -JButton btnLogin
        -LoginController loginController
        +LoginForm()
        -initComponents() void
        -btnLoginActionPerformed(evt) void
        +main(args) void$
    }

    class HRManagerDashboard {
        -EmployeeService employeeService
        -LeaveRequestService leaveRequestService
        -JPanel parentPanel
        -JTable employeeTable
        -JTable leaveTable
        -JTextField txtEmployeeId, txtFirstName, txtLastName, ...
        -JComboBox comboPosition, comboStatus, comboRole
        +HRManagerDashboard()
        -initComponents() void
        -createSidebar() JPanel
        -createEmployeeListPanel() JPanel
        -createEmployeeDetailsPanel() JPanel
        -createLeaveRequestPanel() JPanel
        -showPanel(panelName) void
        -loadEmployeeData() void
        -loadLeaveData() void
        -createEmployeeActionPerformed(evt) void
        -updateEmployeeActionPerformed(evt) void
        -deleteEmployeeActionPerformed(evt) void
        -updateLeaveActionPerformed(evt) void
        -logoutActionPerformed(evt) void
        -clearEmployeeFields() void
        +main(args) void$
    }

    class EmployeeDashboard {
        -String username
        -EmployeeService employeeService
        -LeaveRequestService leaveRequestService
        -PayrollService payrollService
        -ReportService reportService
        -Employee loggedInEmployee
        -JPanel parentPanel
        -JTable leaveTable
        -JTable payrollTable
        -JTextField txtStartDate, txtEndDate
        -JComboBox comboLeaveType
        -JLabel lblPayrollId
        -JLabel lblEmployeeId, lblFullName, ...
        +EmployeeDashboard(username)
        -initComponents() void
        -createSidebar() JPanel
        -createEmployeeDetailsPanel() JPanel
        -createLeaveRequestPanel() JPanel
        -createPayrollPanel() JPanel
        -showPanel(panelName) void
        -loadEmployeeDetails() void
        -loadLeaveData() void
        -loadPayrollData() void
        -submitLeaveActionPerformed(evt) void
        -payrollTableMouseClicked(evt) void
        -generatePayslipActionPerformed(evt) void
        -logoutActionPerformed(evt) void
        +main(args) void$
    }

    class PayrollStaffDashboard {
        -EmployeeService employeeService
        -PayrollService payrollService
        -JPanel parentPanel
        -JTable employeeTable
        -JTable payrollTable
        -JTextField txtEmployeeId, txtPeriodStart, txtPeriodEnd
        -JLabel lblRate, lblHoursWorked, lblGrossSalary, ...
        +PayrollStaffDashboard()
        -initComponents() void
        -createSidebar() JPanel
        -createEmployeeListPanel() JPanel
        -createPayrollProcessPanel() JPanel
        -showPanel(panelName) void
        -loadEmployeeData() void
        -loadPayrollData() void
        -calculatePayrollActionPerformed(evt) void
        -processPayrollActionPerformed(evt) void
        -clearFields() void
        -logoutActionPerformed(evt) void
        +main(args) void$
    }

    LoginForm --> LoginController
    HRManagerDashboard --> EmployeeService
    HRManagerDashboard --> LeaveRequestService
    EmployeeDashboard --> EmployeeService
    EmployeeDashboard --> LeaveRequestService
    EmployeeDashboard --> PayrollService
    EmployeeDashboard --> ReportService
    PayrollStaffDashboard --> EmployeeService
    PayrollStaffDashboard --> PayrollService
```

## Design Patterns Used

### 1. Singleton Pattern
- **DatabaseConnection**: Ensures only one database connection instance

### 2. Data Access Object (DAO) Pattern
- **UserDAO, EmployeeDAO, LeaveRequestDAO, PayrollDAO, AttendanceDAO, BenefitsDAO**: Encapsulate database access logic

### 3. Service Layer Pattern
- **AuthenticationService, EmployeeService, LeaveRequestService, PayrollService, etc.**: Contain business logic

### 4. Model-View-Controller (MVC) Pattern
- **Models**: Domain entities (Employee, User, Payroll, etc.)
- **Views**: GUI components (LoginForm, HRManagerDashboard, etc.)
- **Controllers**: Handle user interactions (LoginController)

### 5. Dependency Injection
- Services depend on DAOs through constructor injection
- Controllers depend on services

## Key Relationships

1. **User ↔ Employee**: One-to-one relationship (each employee has a user account)
2. **Employee ↔ LeaveRequest**: One-to-many (employee can have multiple leave requests)
3. **Employee ↔ Payroll**: One-to-many (employee can have multiple payroll records)
4. **Employee ↔ Attendance**: One-to-many (employee has multiple attendance records)
5. **Employee ↔ Benefits**: Many-to-one (employees with same position share benefits)

## Benefits of This Architecture

### 1. **Separation of Concerns**
- Each layer has a specific responsibility
- Changes in one layer don't affect others

### 2. **Maintainability**
- Code is organized and easy to understand
- Easy to locate and fix bugs

### 3. **Testability**
- Each component can be tested independently
- Easy to mock dependencies

### 4. **Scalability**
- Easy to add new features
- Can be extended to web-based or microservices architecture

### 5. **Reusability**
- Services can be reused across different views
- DAOs can be reused across different services

This class diagram represents a well-structured, maintainable, and scalable payroll system that follows industry best practices and OOP principles.