package Test;

import com.motorph.model.Attendance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.time.LocalTime;

class AttendanceTest {
    
    private Attendance attendance;
    
    @BeforeEach
    void setUp() {
        attendance = new Attendance();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(attendance);
        assertNull(attendance.getEmployeeId());
        assertNull(attendance.getDate());
        assertNull(attendance.getTimeIn());
        assertNull(attendance.getTimeOut());
    }
    
    @Test
    void testParameterizedConstructor() {
        LocalDate date = LocalDate.of(2023, 1, 15);
        LocalTime timeIn = LocalTime.of(8, 0);
        LocalTime timeOut = LocalTime.of(17, 0);
        
        Attendance att = new Attendance("EMP001", date, timeIn, timeOut);
        
        assertEquals("EMP001", att.getEmployeeId());
        assertEquals(date, att.getDate());
        assertEquals(timeIn, att.getTimeIn());
        assertEquals(timeOut, att.getTimeOut());
    }
    
    @Test
    void testGetHoursWorked_ValidTimes() {
        attendance.setTimeIn(LocalTime.of(8, 0));
        attendance.setTimeOut(LocalTime.of(17, 0));
        
        assertEquals(9.0, attendance.getHoursWorked(), 0.01);
    }
    
    @Test
    void testGetHoursWorked_PartialHours() {
        attendance.setTimeIn(LocalTime.of(8, 30));
        attendance.setTimeOut(LocalTime.of(17, 15));
        
        assertEquals(8.75, attendance.getHoursWorked(), 0.01);
    }
    
    @Test
    void testGetHoursWorked_NullTimes() {
        attendance.setTimeIn(null);
        attendance.setTimeOut(LocalTime.of(17, 0));
        
        assertEquals(0.0, attendance.getHoursWorked(), 0.01);
    }
    
    @Test
    void testGetHoursWorked_SameTimes() {
        LocalTime time = LocalTime.of(9, 0);
        attendance.setTimeIn(time);
        attendance.setTimeOut(time);
        
        assertEquals(0.0, attendance.getHoursWorked(), 0.01);
    }
}