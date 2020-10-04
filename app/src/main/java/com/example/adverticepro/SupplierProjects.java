package com.example.adverticepro;

public class SupplierProjects {

    private String supName; //initializing variable
    private String supNumber;
    private String supProDetails;

    public SupplierProjects() { //constructor
    }

    public String getSupName() {   //getters and setters
        return supName;
    }

    public void setSupName(String supName) {
        this.supName = supName;
    }

    public String getSupNumber() {
        return supNumber;
    }

    public void setSupNumber(String supNumber) {
        this.supNumber = supNumber;
    }

    public String getSupProDetails() {
        return supProDetails;
    }

    public void setSupProDetails(String supProDetails) {
        this.supProDetails = supProDetails;
    }
}
