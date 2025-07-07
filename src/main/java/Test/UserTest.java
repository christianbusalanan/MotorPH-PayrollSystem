package Test;

import com.motorph.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    
    private User user;
    
    @BeforeEach
    void setUp() {
        user = new User();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(user);
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getRole());
    }
    
    @Test
    void testParameterizedConstructor() {
        User u = new User("EMP001", "johndoe", "password123", "Employee");
        
        assertEquals("EMP001", u.getId());
        assertEquals("johndoe", u.getUsername());
        assertEquals("password123", u.getPassword());
        assertEquals("Employee", u.getRole());
    }
    
    @Test
    void testSettersAndGetters() {
        user.setId("EMP002");
        user.setUsername("janedoe");
        user.setPassword("securepass");
        user.setRole("HR Manager");
        
        assertEquals("EMP002", user.getId());
        assertEquals("janedoe", user.getUsername());
        assertEquals("securepass", user.getPassword());
        assertEquals("HR Manager", user.getRole());
    }
    
    @Test
    void testToString() {
        user.setId("EMP003");
        user.setUsername("testuser");
        user.setRole("Payroll Staff");
        
        String expected = "User{id='EMP003', username='testuser', role='Payroll Staff'}";
        assertEquals(expected, user.toString());
    }
}