package com.motorph.model;

import java.time.LocalDate;

/**
 * Abstract base class for all persons in the system
 * Demonstrates Abstraction and Inheritance
 */
public abstract class Person {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected LocalDate birthday;
    protected String address;
    protected String phoneNumber;
    
    // Default constructor
    public Person() {}
    
    // Parameterized constructor
    public Person(String id, String firstName, String lastName, LocalDate birthday, 
                  String address, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    
    // Abstract methods - must be implemented by subclasses
    public abstract String getRole();
    public abstract String getDisplayInfo();
    
    // Concrete methods - shared by all persons
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getAge() {
        if (birthday != null) {
            return LocalDate.now().getYear() - birthday.getYear();
        }
        return 0;
    }
    
    // Encapsulation - private fields with public getters/setters
    public String getId() { return id; }
    public void setId(String id) { 
        if (id != null && !id.trim().isEmpty()) {
            this.id = id.trim();
        }
    }
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { 
        if (firstName != null && !firstName.trim().isEmpty()) {
            this.firstName = firstName.trim();
        }
    }
    
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { 
        if (lastName != null && !lastName.trim().isEmpty()) {
            this.lastName = lastName.trim();
        }
    }
    
    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id='" + id + '\'' +
                ", name='" + getFullName() + '\'' +
                ", role='" + getRole() + '\'' +
                '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person person = (Person) obj;
        return id != null ? id.equals(person.id) : person.id == null;
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}