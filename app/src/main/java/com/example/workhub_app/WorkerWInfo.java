package com.example.workhub_app;

public class WorkerWInfo {
    private String companyName;
    private String location;
    private String Services;
    private String hRate;

    public WorkerWInfo() {
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String services) {
        Services = services;
    }

    public String gethRate() {
        return hRate;
    }

    public void sethRate(String hRate) {
        this.hRate = hRate;
    }
}
