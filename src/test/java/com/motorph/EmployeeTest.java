package com.motorph;

import com.motorph.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class EmployeeTest {
    
    private Employee employee;
    
    @BeforeEach
    void setUp() {
        employee = new Employee();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(employee);
        assertNull(employee.getEmployeeId());
        assertNull(employee.getFirstName());
        assertNull(employee.getLastName());
    }
    
    @Test
    void testParameterizedConstructor() {
        LocalDate birthday = LocalDate.of(1990, 5, 15);
        Employee emp = new Employee(
            "EMP001", "Doe", "John", "johndoe", 
            birthday, "123 Main St", "555-1234", "Regular",
            "Developer", "IT", "Jane Smith", 50000.0, 312.5
        );
        
        assertEquals("EMP001", emp.getEmployeeId());
        assertEquals("Doe", emp.getLastName());
        assertEquals("John", emp.getFirstName());
        assertEquals("johndoe", emp.getUsername());
        assertEquals(birthday, emp.getBirthday());
        assertEquals("123 Main St", emp.getAddress());
        assertEquals("555-1234", emp.getPhoneNumber());
        assertEquals("Regular", emp.getStatus());
        assertEquals("Developer", emp.getPosition());
        assertEquals("IT", emp.getDepartment());
        assertEquals("Jane Smith", emp.getSupervisor());
        assertEquals(50000.0, emp.getBasicSalary());
        assertEquals(312.5, emp.getHourlyRate());
    }
    
    @Test
    void testGetFullName() {
        employee.setFirstName("John");
        employee.setLastName("Doe");
        assertEquals("John Doe", employee.getFullName());
    }
    
    @Test
    void testGetFullName_WithNullValues() {
        employee.setFirstName(null);
        employee.setLastName("Doe");
        assertEquals("null Doe", employee.getFullName());
    }
    
    @Test
    void testSettersAndGetters() {
        employee.setEmployeeId("EMP123");
        employee.setFirstName("Alice");
        employee.setLastName("Johnson");
        employee.setBasicSalary(60000.0);
        employee.setHourlyRate(375.0);
        
        assertEquals("EMP123", employee.getEmployeeId());
        assertEquals("Alice", employee.getFirstName());
        assertEquals("Johnson", employee.getLastName());
        assertEquals(60000.0, employee.getBasicSalary());
        assertEquals(375.0, employee.getHourlyRate());
    }
}