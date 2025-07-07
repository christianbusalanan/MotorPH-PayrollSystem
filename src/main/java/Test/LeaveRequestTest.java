package Test;

import com.motorph.model.LeaveRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

class LeaveRequestTest {
    
    private LeaveRequest leaveRequest;
    
    @BeforeEach
    void setUp() {
        leaveRequest = new LeaveRequest();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(leaveRequest);
        assertNull(leaveRequest.getId());
        assertNull(leaveRequest.getEmployeeId());
        assertNull(leaveRequest.getLeaveType());
        assertNull(leaveRequest.getStartDate());
        assertNull(leaveRequest.getEndDate());
        assertNull(leaveRequest.getStatus());
    }
    
    @Test
    void testParameterizedConstructor() {
        LocalDate start = LocalDate.of(2023, 6, 1);
        LocalDate end = LocalDate.of(2023, 6, 5);
        
        LeaveRequest lr = new LeaveRequest(
            "LEAVE001", "EMP001", "Vacation Leave", start, end, "Pending"
        );
        
        assertEquals("LEAVE001", lr.getId());
        assertEquals("EMP001", lr.getEmployeeId());
        assertEquals("Vacation Leave", lr.getLeaveType());
        assertEquals(start, lr.getStartDate());
        assertEquals(end, lr.getEndDate());
        assertEquals("Pending", lr.getStatus());
    }
    
    @Test
    void testSettersAndGetters() {
        LocalDate start = LocalDate.of(2023, 7, 10);
        LocalDate end = LocalDate.of(2023, 7, 12);
        
        leaveRequest.setId("LEAVE002");
        leaveRequest.setEmployeeId("EMP002");
        leaveRequest.setLeaveType("Sick Leave");
        leaveRequest.setStartDate(start);
        leaveRequest.setEndDate(end);
        leaveRequest.setStatus("Approved");
        
        assertEquals("LEAVE002", leaveRequest.getId());
        assertEquals("EMP002", leaveRequest.getEmployeeId());
        assertEquals("Sick Leave", leaveRequest.getLeaveType());
        assertEquals(start, leaveRequest.getStartDate());
        assertEquals(end, leaveRequest.getEndDate());
        assertEquals("Approved", leaveRequest.getStatus());
    }
}