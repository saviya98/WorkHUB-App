package com.example.adverticepro;

import android.widget.EditText;

public class customerProjects {

    private String cusName;
    private int cusNumber;
    private String proDetails;

    public customerProjects() {

    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public int getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(int cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getProDetails() {
        return proDetails;
    }

    public void setProDetails(String proDetails) {
        this.proDetails = proDetails;
    }
}
