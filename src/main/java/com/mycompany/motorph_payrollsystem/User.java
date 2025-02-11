/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.motorph_payrollsystem;

/**
 *
 * @author chris
 */
import java.time.LocalDate;
abstract class User {
    protected  String employee_id ;
    protected String last_name;
    protected String first_name;
    protected LocalDate birth_day;
    protected  String address;
    protected  String phone_number;
    protected  String sss;
    protected  String philhealth;
    protected  String tin;
    protected  String pagibig;
    protected  String status;
    protected  String department;
    protected  String position;
    protected  String supervisor;
    protected  int basic_salary ;
    protected  int rice_subsidy;
    protected  int phone_allowance;
    protected  int clothing_allowance;
    protected  int gross_semi_monthly_rate;
    protected  int hourly_rate;
    protected  String username;
    protected  String password;

    
    public User(String employee_id , String last_name,  String first_name , LocalDate birth_day, String address,String phone_number,String sss,String philhealth, String tin, String pagibig,  String status,String position, String department, String supervisor,int basic_salary, int rice_subsidy, int phone_allowance,int clothing_allowance,int gross_semi_monthly_rate,int hourly_rate, String username, String password) {
        this.employee_id= employee_id;
        this.last_name= last_name;
        this.first_name= first_name;
        this.birth_day= birth_day;
        this.address= address;
        this.phone_number= phone_number;
        this.sss= sss;
        this.philhealth= philhealth;
        this.tin= tin;
        this.pagibig= pagibig;
        this.status= status;
        this.position= position;
        this.department= department;
        this.supervisor= supervisor;
        this.basic_salary= basic_salary;
        this.rice_subsidy= rice_subsidy;
        this.phone_allowance= phone_allowance;
        this.clothing_allowance= clothing_allowance;
        this.gross_semi_monthly_rate= gross_semi_monthly_rate;
        this.hourly_rate= hourly_rate;
        this.username= username;
        this.password= password;
        
    }
    
    

    public abstract void viewEmployeeDetails();
}
    