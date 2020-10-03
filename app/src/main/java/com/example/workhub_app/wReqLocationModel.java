package com.example.workhub_app;

public class wReqLocationModel {
    String location,email,name,occupation;

    public wReqLocationModel() {
    }

    public wReqLocationModel(String location, String email, String name, String occupation) {
        this.location = location;
        this.email = email;
        this.name = name;
        this.occupation = occupation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
}
