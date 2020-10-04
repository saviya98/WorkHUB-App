package com.example.workhub_app;

public class cust_proj_model {

    private String cust_project_description;
    private String cust_project_telnumber;
    private String cust_project_title;

    public cust_proj_model(String cust_project_description, String cust_project_telnumber, String cust_project_title) {
        this.cust_project_description = cust_project_description;
        this.cust_project_telnumber = cust_project_telnumber;
        this.cust_project_title = cust_project_title;
    }

    public cust_proj_model() {
    }

    public String getCust_project_description() {
        return cust_project_description;
    }

    public void setCust_project_description(String cust_project_description) {
        this.cust_project_description = cust_project_description;
    }

    public String getCust_project_telnumber() {
        return cust_project_telnumber;
    }

    public void setCust_project_telnumber(String cust_project_telnumber) {
        this.cust_project_telnumber = cust_project_telnumber;
    }

    public String getCust_project_title() {
        return cust_project_title;
    }

    public void setCust_project_title(String cust_project_title) {
        this.cust_project_title = cust_project_title;
    }
}
