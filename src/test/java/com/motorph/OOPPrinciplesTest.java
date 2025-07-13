package com.motorph;

import com.motorph.factory.EmployeeFactory;
import com.motorph.interfaces.Manageable;
import com.motorph.interfaces.Payable;
import com.motorph.model.Employee;
import com.motorph.model.Manager;
import com.motorph.model.Person;
import com.motorph.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Test class demonstrating all OOP principles implementation
 */
class OOPPrinciplesTest {
    
    private Employee employee;
    private Manager manager;
    private User user;
    
    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setId("EMP001");
        employee.setFirstName("John");
        employee.setLastName("Doe");
        employee.setPosition("Developer");
        employee.setBasicSalary(50000.0);
        employee.setHourlyRate(312.5);
        
        manager = new Manager();
        manager.setId("MGR001");
        manager.setFirstName("Jane");
        manager.setLastName("Smith");
        manager.setPosition("IT Manager");
        manager.setBasicSalary(80000.0);
        manager.setHourlyRate(500.0);
        
        user = new User("USR001", "johndoe", "password123", "Employee");
    }
    
    @Test
    void testInheritance() {
        // Manager IS-A Employee IS-A Person
        assertTrue(manager instanceof Manager);
        assertTrue(manager instanceof Employee);
        assertTrue(manager instanceof Person);
        
        // Employee IS-A Person
        assertTrue(employee instanceof Employee);
        assertTrue(employee instanceof Person);
        
        // Inheritance of methods
        assertEquals("John Doe", employee.getFullName()); // Inherited from Person
        assertEquals("Jane Smith", manager.getFullName()); // Inherited from Person
    }
    
    @Test
    void testPolymorphism_MethodOverriding() {
        // Method overriding - same method, different behavior
        assertEquals("Employee", employee.getRole());
        assertEquals("Manager", manager.getRole());
        
        // Display info is overridden in each class
        assertTrue(employee.getDisplayInfo().contains("Developer"));
        assertTrue(manager.getDisplayInfo().contains("Manager"));
        assertTrue(manager.getDisplayInfo().contains("subordinates"));
    }
    
    @Test
    void testPolymorphism_MethodOverloading() {
        // Method overloading - same method name, different parameters
        double salary1 = employee.calculateMonthlySalary();
        double salary2 = employee.calculateMonthlySalary(160);
        double salary3 = employee.calculateMonthlySalary(160, 468.75);
        
        assertTrue(salary1 > 0);
        assertTrue(salary2 > 0);
        assertTrue(salary3 > salary2); // With overtime
        
        // Manager overloading
        double managerSalary1 = manager.calculateMonthlySalary();
        double managerSalary2 = manager.calculateMonthlySalary(true); // With bonus
        
        assertTrue(managerSalary2 > managerSalary1); // With bonus should be higher
    }
    
    @Test
    void testPolymorphism_InterfaceImplementation() {
        // Polymorphism through interfaces
        Payable payableEmployee = employee;
        Payable payableManager = manager;
        
        assertTrue(payableEmployee.calculateGrossPay(null, null) > 0);
        assertTrue(payableManager.calculateGrossPay(null, null) > payableEmployee.calculateGrossPay(null, null));
        
        // Manager implements Manageable
        assertTrue(manager instanceof Manageable);
        Manageable manageableManager = manager;
        assertTrue(manageableManager.canManage(employee));
    }
    
    @Test
    void testAbstraction() {
        // Cannot instantiate abstract Person class
        // Person person = new Person(); // This would cause compilation error
        
        // But can use Person reference for polymorphism
        Person personEmployee = employee;
        Person personManager = manager;
        
        assertEquals("Employee", personEmployee.getRole());
        assertEquals("Manager", personManager.getRole());
        
        // Abstract methods must be implemented
        assertNotNull(personEmployee.getDisplayInfo());
        assertNotNull(personManager.getDisplayInfo());
    }
    
    @Test
    void testEncapsulation() {
        // Private fields are not directly accessible
        // employee.id = "test"; // This would cause compilation error
        
        // Must use getters and setters
        employee.setId("EMP002");
        assertEquals("EMP002", employee.getId());
        
        // Validation in setters
        employee.setBasicSalary(-1000); // Should not set negative salary
        assertNotEquals(-1000, employee.getBasicSalary());
        
        // Data validation
        user.setPassword("123"); // Too short, should not be set
        assertNotEquals("123", user.getPassword());
    }
    
    @Test
    void testComposition() {
        // Employee HAS-A list of leave requests
        assertNotNull(employee.getLeaveRequests());
        
        // Manager HAS-A list of subordinates
        assertNotNull(manager.getSubordinates());
        
        // Manager can manage employees
        manager.addSubordinate(employee);
        assertEquals(1, manager.getTeamSize());
        assertTrue(manager.getSubordinates().contains(employee));
    }
    
    @Test
    void testFactoryPattern() {
        // Factory creates appropriate employee type
        Employee regularEmployee = EmployeeFactory.createEmployee("Developer");
        Employee managerEmployee = EmployeeFactory.createEmployee("IT Manager");
        
        assertTrue(regularEmployee instanceof Employee);
        assertFalse(regularEmployee instanceof Manager);
        
        assertTrue(managerEmployee instanceof Manager);
        assertTrue(managerEmployee instanceof Employee); // Manager IS-A Employee
    }
    
    @Test
    void testInterfaceSegregation() {
        // Employee implements only Payable
        assertTrue(employee instanceof Payable);
        assertFalse(employee instanceof Manageable);
        
        // Manager implements both Payable and Manageable
        assertTrue(manager instanceof Payable);
        assertTrue(manager instanceof Manageable);
        
        // User implements Authenticatable
        assertTrue(user instanceof com.motorph.interfaces.Authenticatable);
    }
    
    @Test
    void testDependencyInversion() {
        // High-level modules depend on abstractions (interfaces)
        Payable payable = employee; // Depends on Payable interface
        Manageable manageable = manager; // Depends on Manageable interface
        
        // Can work with any implementation
        assertTrue(payable.getHourlyRate() > 0);
        assertTrue(manageable.getSubordinates() != null);
    }
    
    @Test
    void testSingleResponsibility() {
        // Each class has single responsibility
        // Employee - manages employee data
        // Manager - manages employee data + team management
        // User - manages authentication
        // Payroll - manages payroll calculations
        
        assertTrue(employee.getFullName() != null); // Employee responsibility
        assertTrue(manager.canManage(employee)); // Manager responsibility
        assertTrue(user.hasRole("Employee")); // User responsibility
    }
    
    @Test
    void testOpenClosedPrinciple() {
        // Classes are open for extension (Manager extends Employee)
        // but closed for modification (don't need to modify Employee for Manager features)
        
        Employee emp = employee;
        Employee mgr = manager;
        
        // Both work with Employee interface
        assertTrue(emp.calculateMonthlySalary() > 0);
        assertTrue(mgr.calculateMonthlySalary() > 0);
        
        // Manager extends functionality without modifying Employee
        assertTrue(mgr.calculateMonthlySalary() > emp.calculateMonthlySalary());
    }
    
    @Test
    void testLiskovSubstitution() {
        // Manager can be substituted wherever Employee is expected
        Employee[] employees = {employee, manager};
        
        for (Employee emp : employees) {
            // All Employee methods work for both Employee and Manager
            assertNotNull(emp.getFullName());
            assertNotNull(emp.getRole());
            assertTrue(emp.calculateMonthlySalary() > 0);
        }
        
        // Polymorphic behavior works correctly
        assertEquals("Employee", employees[0].getRole());
        assertEquals("Manager", employees[1].getRole());
    }
}