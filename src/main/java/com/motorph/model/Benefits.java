package com.motorph.model;

public class Benefits {
    private String position;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;

    // Default constructor
    public Benefits() {}

    // Constructor with all fields
    public Benefits(String position, double riceSubsidy, double phoneAllowance, double clothingAllowance) {
        this.position = position;
        this.riceSubsidy = riceSubsidy;
        this.phoneAllowance = phoneAllowance;
        this.clothingAllowance = clothingAllowance;
    }

    // Getters and Setters
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(double riceSubsidy) { this.riceSubsidy = riceSubsidy; }

    public double getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(double phoneAllowance) { this.phoneAllowance = phoneAllowance; }

    public double getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(double clothingAllowance) { this.clothingAllowance = clothingAllowance; }

    public double getTotalBenefits() {
        return riceSubsidy + phoneAllowance + clothingAllowance;
    }

    @Override
    public String toString() {
        return "Benefits{" +
                "position='" + position + '\'' +
                ", totalBenefits=" + getTotalBenefits() +
                '}';
    }
}