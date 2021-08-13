package com.example.LabExercise4;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerNumber;
    private String customerName;
    private double customerInitialDeposit;
    private int numberOfYears;
    private String savingsType;


    public List<Integer> year = new ArrayList<>();
    public List<Double> starting = new ArrayList<>();
    public List<Double> interest = new ArrayList<>();
    public List<Double> ending = new ArrayList<>();

    public Customer(String customerNumber, String customerName, double customerInitialDeposit, int numberOfYears, String savingsType) {
        this.customerNumber = customerNumber;
        this.customerName = customerName;
        this.customerInitialDeposit = customerInitialDeposit;
        this.numberOfYears = numberOfYears;
        this.savingsType = savingsType;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getCustomerInitialDeposit() {
        return customerInitialDeposit;
    }

    public void setCustomerInitialDeposit(double customerInitialDeposit) {
        this.customerInitialDeposit = customerInitialDeposit;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears) {
        this.numberOfYears = numberOfYears;
    }

    public String getSavingsType() {
        return savingsType;
    }

    public void setSavingsType(String savingsType) {
        this.savingsType = savingsType;
    }


}
