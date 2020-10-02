package com.example.adverticepro;

import android.widget.EditText;

public class CustomerProjects {

    private String cusName;
    private String cusNumber;
    private String proDetails;

    public CustomerProjects() {
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getProDetails() {
        return proDetails;
    }

    public void setProDetails(String proDetails) {
        this.proDetails = proDetails;
    }
}
