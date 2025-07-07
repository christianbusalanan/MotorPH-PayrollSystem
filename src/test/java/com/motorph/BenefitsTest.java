package com.motorph;

import com.motorph.model.Benefits;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BenefitsTest {
    
    private Benefits benefits;
    
    @BeforeEach
    void setUp() {
        benefits = new Benefits();
    }
    
    @Test
    void testDefaultConstructor() {
        assertNotNull(benefits);
        assertNull(benefits.getPosition());
        assertEquals(0.0, benefits.getRiceSubsidy());
        assertEquals(0.0, benefits.getPhoneAllowance());
        assertEquals(0.0, benefits.getClothingAllowance());
    }
    
    @Test
    void testParameterizedConstructor() {
        Benefits b = new Benefits("Developer", 1500.0, 800.0, 1000.0);
        
        assertEquals("Developer", b.getPosition());
        assertEquals(1500.0, b.getRiceSubsidy());
        assertEquals(800.0, b.getPhoneAllowance());
        assertEquals(1000.0, b.getClothingAllowance());
    }
    
    @Test
    void testGetTotalBenefits() {
        benefits.setRiceSubsidy(1500.0);
        benefits.setPhoneAllowance(800.0);
        benefits.setClothingAllowance(1000.0);
        
        assertEquals(3300.0, benefits.getTotalBenefits(), 0.01);
    }
    
    @Test
    void testGetTotalBenefits_ZeroValues() {
        assertEquals(0.0, benefits.getTotalBenefits(), 0.01);
    }
    
    @Test
    void testSettersAndGetters() {
        benefits.setPosition("Manager");
        benefits.setRiceSubsidy(2000.0);
        benefits.setPhoneAllowance(1200.0);
        benefits.setClothingAllowance(1500.0);
        
        assertEquals("Manager", benefits.getPosition());
        assertEquals(2000.0, benefits.getRiceSubsidy());
        assertEquals(1200.0, benefits.getPhoneAllowance());
        assertEquals(1500.0, benefits.getClothingAllowance());
    }
}