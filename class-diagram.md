# MotorPH Payroll System - Class Diagram

```mermaid
classDiagram
    %% Model Classes
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
        +Employee(employeeId, lastName, firstName, username, birthday, address, phoneNumber, status, position, department, supervisor, basicSalary, hourlyRate)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getLastName() String
        +setLastName(String)
        +getFirstName() String
        +setFirstName(String)
        +getUsername() String
        +setUsername(String)
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
        +getFullName() String
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
        +Payroll(payrollId, employeeId, periodStart, periodEnd, workingHours, sssContribution, philhealthContribution, pagibigContribution, withholdingTax, riceSubsidy, phoneAllowance, clothingAllowance)
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
        +getGrossIncome(double) double
        +getTotalDeductions() double
        +getTotalBenefits() double
        +getNetSalary(double) double
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

    %% DAO Classes
    class DatabaseConnection {
        -String URL$
        -Connection connection$
        -DatabaseConnection()
        +getConnection()$ Connection
        +closeConnection()$
        -testDatabaseConnection()$
    }

    class UserDAO {
        +UserDAO()
        +authenticateUser(String, String) User
        +createUser(User) boolean
        +deleteUser(String) boolean
    }

    class EmployeeDAO {
        +EmployeeDAO()
        +getAllEmployees() List~Employee~
        +getEmployeeById(String) Employee
        +getEmployeeByUsername(String) Employee
        +getEmployeeWithUserDetails(String) Employee
        +getAllEmployeesWithUserDetails() List~Employee~
        +createEmployee(Employee) boolean
        +updateEmployee(Employee) boolean
        +deleteEmployee(String) boolean
        -mapResultSetToEmployee(ResultSet) Employee
        -mapResultSetToEmployeeWithUserDetails(ResultSet) Employee
    }

    class LeaveRequestDAO {
        +LeaveRequestDAO()
        +getAllLeaveRequests() List~LeaveRequest~
        +getAllLeaveRequestsWithEmployeeDetails() List~LeaveRequestWithEmployee~
        +getLeaveRequestsByEmployeeId(String) List~LeaveRequest~
        +createLeaveRequest(LeaveRequest) boolean
        +updateLeaveRequestStatus(String, String) boolean
        -mapResultSetToLeaveRequest(ResultSet) LeaveRequest
        -mapResultSetToLeaveView(ResultSet) LeaveRequestWithEmployee
    }

    class LeaveRequestWithEmployee {
        -String id
        -String employeeId
        -String employeeName
        -String leaveType
        -LocalDate startDate
        -LocalDate endDate
        -String status
        +getId() String
        +setId(String)
        +getEmployeeId() String
        +setEmployeeId(String)
        +getEmployeeName() String
        +setEmployeeName(String)
        +getLeaveType() String
        +setLeaveType(String)
        +getStartDate() LocalDate
        +setStartDate(LocalDate)
        +getEndDate() LocalDate
        +setEndDate(LocalDate)
        +getStatus() String
        +setStatus(String)
    }

    class AttendanceDAO {
        +AttendanceDAO()
        +getAllAttendance() List~Attendance~
        +getAllAttendanceWithEmployeeDetails() List~AttendanceWithEmployee~
        +getAttendanceWithEmployeeDetailsByEmployeeId(String) List~AttendanceWithEmployee~
        +getTotalHoursWorked(String, LocalDate, LocalDate) double
        -mapResultSetToAttendance(ResultSet) Attendance
        -mapResultSetToAttendanceView(ResultSet) AttendanceWithEmployee
    }

    class AttendanceWithEmployee {
        -String employeeId
        -String lastName
        -String firstName
        -LocalDate date
        -LocalTime timeIn
        -LocalTime timeOut
        +getEmployeeId() String
        +setEmployeeId(String)
        +getLastName() String
        +setLastName(String)
        +getFirstName() String
        +setFirstName(String)
        +getDate() LocalDate
        +setDate(LocalDate)
        +getTimeIn() LocalTime
        +setTimeIn(LocalTime)
        +getTimeOut() LocalTime
        +setTimeOut(LocalTime)
        +getHoursWorked() double
        +getFullName() String
    }

    class PayrollDAO {
        +PayrollDAO()
        +getAllPayrolls() List~Payroll~
        +getPayrollsByEmployeeId(String) List~Payroll~
        +createPayroll(Payroll) boolean
        -mapResultSetToPayroll(ResultSet) Payroll
    }

    class BenefitsDAO {
        +BenefitsDAO()
        +getBenefitsByPosition(String) Benefits
    }

    %% Service Classes
    class AuthenticationService {
        -UserDAO userDAO
        +AuthenticationService()
        +authenticateUser(String, String) User
        +isValidRole(String) boolean
    }

    class EmployeeService {
        -EmployeeDAO employeeDAO
        -UserDAO userDAO
        +EmployeeService()
        +getAllEmployees() List~Employee~
        +getAllEmployeesWithUserDetails() List~Employee~
        +getEmployeeById(String) Employee
        +getEmployeeByUsername(String) Employee
        +getEmployeeWithUserDetails(String) Employee
        +createEmployee(Employee, String, String) boolean
        +updateEmployee(Employee) boolean
        +deleteEmployee(String) boolean
        +isValidEmployeeId(String) boolean
        +getEmployeeFullName(String) String
    }

    class LeaveRequestService {
        -LeaveRequestDAO leaveRequestDAO
        +LeaveRequestService()
        +getAllLeaveRequests() List~LeaveRequest~
        +getAllLeaveRequestsWithEmployeeDetails() List~LeaveRequestWithEmployee~
        +getLeaveRequestsByEmployeeId(String) List~LeaveRequest~
        +submitLeaveRequest(String, String, LocalDate, LocalDate) boolean
        +updateLeaveRequestStatus(String, String) boolean
        -isValidLeaveRequest(String, String, LocalDate, LocalDate) boolean
        -isValidStatus(String) boolean
        -generateLeaveId(String, LocalDate, LocalDate) String
    }

    class PayrollCalculationService {
        +PayrollCalculationService()
        +calculateSSSContribution(double) double
        +calculatePhilhealthContribution(double) double
        +calculatePagibigContribution(double) double
        +calculateWithholdingTax(double) double
        +calculateGrossIncome(double, double) double
        +calculateNetSalary(double, double, double) double
    }

    class PayrollService {
        -PayrollDAO payrollDAO
        -AttendanceDAO attendanceDAO
        -BenefitsDAO benefitsDAO
        -PayrollCalculationService calculationService
        -EmployeeService employeeService
        +PayrollService()
        +getAllPayrolls() List~Payroll~
        +getPayrollsByEmployeeId(String) List~Payroll~
        +calculatePayroll(String, LocalDate, LocalDate) Payroll
        +processPayroll(Payroll) boolean
        -generatePayrollId(String, LocalDate, LocalDate) String
    }

    class ReportService {
        +ReportService()
        +generatePayslip(String)
    }

    %% Controller Classes
    class LoginController {
        -AuthenticationService authService
        +LoginController()
        +handleLogin(String, String, JFrame)
    }

    %% GUI Classes
    class LoginForm {
        -JTextField txtUsername
        -JPasswordField txtPassword
        -JButton btnLogin
        -LoginController loginController
        +LoginForm()
        -initComponents()
        -btnLoginActionPerformed(ActionEvent)
        +main(String[])$
    }

    class HRManagerDB {
        -EmployeeService employeeService
        -LeaveRequestService leaveRequestService
        -AttendanceDAO attendanceDAO
        +HRManagerDB()
        -loadEmployeeData()
        -loadLeaveData()
        -loadAttendanceData()
        -clearEmployeeFields()
        +main(String[])$
    }

    class EmployeeDB {
        -String username
        -EmployeeService employeeService
        -LeaveRequestService leaveRequestService
        -PayrollService payrollService
        -ReportService reportService
        -Employee loggedInEmployee
        +EmployeeDB(String)
        -loadEmployeeDetails()
        -loadLeaveData()
        -loadPayrollData()
        +main(String[])$
    }

    class PayrollStaffDB {
        -EmployeeService employeeService
        -PayrollService payrollService
        -AttendanceDAO attendanceDAO
        +PayrollStaffDB()
        -loadEmployeeData()
        -loadPayrollData()
        -loadAttendanceData()
        -clearFields()
        +main(String[])$
    }

    %% Relationships
    User ||--|| Employee : "1:1 (employee_id = id)"
    Employee ||--o{ LeaveRequest : "1:many"
    Employee ||--o{ Attendance : "1:many"
    Employee ||--o{ Payroll : "1:many"
    Employee }o--|| Benefits : "many:1 (position)"

    %% DAO Dependencies
    UserDAO --> DatabaseConnection : uses
    EmployeeDAO --> DatabaseConnection : uses
    LeaveRequestDAO --> DatabaseConnection : uses
    AttendanceDAO --> DatabaseConnection : uses
    PayrollDAO --> DatabaseConnection : uses
    BenefitsDAO --> DatabaseConnection : uses

    %% DAO Inner Classes
    LeaveRequestDAO +-- LeaveRequestWithEmployee : inner class
    AttendanceDAO +-- AttendanceWithEmployee : inner class

    %% Service Dependencies
    AuthenticationService --> UserDAO : uses
    EmployeeService --> EmployeeDAO : uses
    EmployeeService --> UserDAO : uses
    LeaveRequestService --> LeaveRequestDAO : uses
    PayrollService --> PayrollDAO : uses
    PayrollService --> AttendanceDAO : uses
    PayrollService --> BenefitsDAO : uses
    PayrollService --> PayrollCalculationService : uses
    PayrollService --> EmployeeService : uses

    %% Controller Dependencies
    LoginController --> AuthenticationService : uses

    %% GUI Dependencies
    LoginForm --> LoginController : uses
    HRManagerDB --> EmployeeService : uses
    HRManagerDB --> LeaveRequestService : uses
    HRManagerDB --> AttendanceDAO : uses
    EmployeeDB --> EmployeeService : uses
    EmployeeDB --> LeaveRequestService : uses
    EmployeeDB --> PayrollService : uses
    EmployeeDB --> ReportService : uses
    PayrollStaffDB --> EmployeeService : uses
    PayrollStaffDB --> PayrollService : uses
    PayrollStaffDB --> AttendanceDAO : uses

    %% Model Usage
    UserDAO ..> User : creates/returns
    EmployeeDAO ..> Employee : creates/returns
    LeaveRequestDAO ..> LeaveRequest : creates/returns
    LeaveRequestDAO ..> LeaveRequestWithEmployee : creates/returns
    AttendanceDAO ..> Attendance : creates/returns
    AttendanceDAO ..> AttendanceWithEmployee : creates/returns
    PayrollDAO ..> Payroll : creates/returns
    BenefitsDAO ..> Benefits : creates/returns

    AuthenticationService ..> User : returns
    EmployeeService ..> Employee : returns
    LeaveRequestService ..> LeaveRequest : returns
    LeaveRequestService ..> LeaveRequestWithEmployee : returns
    PayrollService ..> Payroll : returns

    %% GUI Navigation
    LoginForm --> HRManagerDB : "HR Manager role"
    LoginForm --> EmployeeDB : "Employee role"
    LoginForm --> PayrollStaffDB : "Payroll Staff role"
```

## Architecture Overview

### **Layered Architecture:**
1. **Presentation Layer (GUI)**: LoginForm, HRManagerDB, EmployeeDB, PayrollStaffDB
2. **Controller Layer**: LoginController
3. **Service Layer**: AuthenticationService, EmployeeService, LeaveRequestService, PayrollService, PayrollCalculationService, ReportService
4. **Data Access Layer (DAO)**: UserDAO, EmployeeDAO, LeaveRequestDAO, AttendanceDAO, PayrollDAO, BenefitsDAO
5. **Model Layer**: User, Employee, LeaveRequest, Attendance, Payroll, Benefits

### **Key Design Patterns:**
- **MVC Pattern**: Separation of concerns between GUI, Controllers, and Models
- **DAO Pattern**: Data access abstraction
- **Service Layer Pattern**: Business logic encapsulation
- **Singleton Pattern**: DatabaseConnection (static connection management)
- **Factory Pattern**: User role-based GUI creation in LoginController

### **Database Views Integration:**
- **AttendanceView**: Used by AttendanceDAO for joined employee-attendance data
- **LeaveView**: Used by LeaveRequestDAO for joined employee-leave data
- Eliminates complex JOIN queries in application code

### **Security & Authentication:**
- Normalized user authentication (User table separate from Employee table)
- Role-based access control (HR Manager, Employee, Payroll Staff)
- Secure login flow through AuthenticationService

This class diagram shows the complete architecture of your MotorPH Payroll System with all relationships, dependencies, and the clean separation of concerns across different layers.