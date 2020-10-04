package com.example.workhub_app;

public class cus_model {

    private String email;
    private String industryType;
    private String name;
    private String phone;

    public cus_model(String email, String industryType, String name, String phone) {
        this.email = email;
        this.industryType = industryType;
        this.name = name;
        this.phone = phone;
    }

    public cus_model() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndustrytype() {
        return industryType;
    }

    public void setIndustrytype(String industrytype) {
        this.industryType = industrytype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
