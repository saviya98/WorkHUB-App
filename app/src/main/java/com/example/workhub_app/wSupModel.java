package com.example.workhub_app;

public class wSupModel {
    String companyName,email,address,ownerName,phone;

    public wSupModel(){

    }

    public wSupModel(String companyName, String email, String address, String ownerName, String phone) {
        this.companyName = companyName;
        this.email = email;
        this.address = address;
        this.ownerName = ownerName;
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
