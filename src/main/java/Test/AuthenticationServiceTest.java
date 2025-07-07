package Test;

import com.motorph.dao.UserDAO;
import com.motorph.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceTest {
    
    @Mock
    private UserDAO userDAO;
    
    private AuthenticationService authService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthenticationService();
    }
    
    @Test
    void testIsValidRole_ValidRoles() {
        assertTrue(authService.isValidRole("HR Manager"));
        assertTrue(authService.isValidRole("Employee"));
        assertTrue(authService.isValidRole("Payroll Staff"));
    }
    
    @Test
    void testIsValidRole_InvalidRoles() {
        assertFalse(authService.isValidRole("Admin"));
        assertFalse(authService.isValidRole("Manager"));
        assertFalse(authService.isValidRole(null));
        assertFalse(authService.isValidRole(""));
    }
    
    @Test
    void testAuthenticateUser_EmptyCredentials() {
        assertNull(authService.authenticateUser("", "password"));
        assertNull(authService.authenticateUser("username", ""));
        assertNull(authService.authenticateUser(null, "password"));
        assertNull(authService.authenticateUser("username", null));
        assertNull(authService.authenticateUser("  ", "password"));
        assertNull(authService.authenticateUser("username", "  "));
    }
}