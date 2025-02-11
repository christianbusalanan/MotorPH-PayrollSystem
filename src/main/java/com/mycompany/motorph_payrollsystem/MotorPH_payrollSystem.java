/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.motorph_payrollsystem;

/**
 *
 * @author chris
 */
import java.time.LocalDate;
public class MotorPH_payrollSystem {

    public static void main(String[] args) {
        Employee christian = new Employee("69", "Busalanan",  "Christian" , LocalDate.of(2000,02,24), "homeless", "09293780115", "000", "000",  "000",  "000",   "Intern", "data analyst", "Data Science" ,"Jennifer", 4000, 4000, 4000, 4000, 4000, 4000,  "chanchan",  "awitawards" );
        christian.viewEmployeeDetails();
       
       
        HR cj= new HR("69", "Oraya",  "Christtelle" , LocalDate.of(2000,02,9), "homeless", "09293780115", "000", "000",  "000",  "000",   "Intern", "data analyst", "Human Resoruce" ,"Jennifer", 4000, 4000, 4000, 4000, 4000, 4000,  "cj",  "cj123" );
        cj.viewEmployeeDetails();
        
        PayrollStaff joshua= new PayrollStaff("100", "Ricohermozo",  "Joshua" , LocalDate.of(2000,02,9), "homeless", "09293780115", "000", "000",  "000",  "000",   "Intern" ,"Jennifer", 4000, 4000, 4000, 4000, 4000, 4000,  "Josh",  "josh123");
        joshua.viewEmployeeDetails();
        
    }
}
