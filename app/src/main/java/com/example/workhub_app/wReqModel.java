package com.example.workhub_app;

public class wReqModel {
    //String date,dailyWage,description,tel,pTitle;
    String cust_project_description,cust_project_telnumber,cust_project_title;
    int cust_project_aprox_date,cust_project_dailywage;

    public wReqModel() {
    }

    public wReqModel(String cust_project_description, String cust_project_telnumber, String cust_project_title, int cust_project_aprox_date, int cust_project_dailywage) {
        this.cust_project_description = cust_project_description;
        this.cust_project_telnumber = cust_project_telnumber;
        this.cust_project_title = cust_project_title;
        this.cust_project_aprox_date = cust_project_aprox_date;
        this.cust_project_dailywage = cust_project_dailywage;
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

    public int getCust_project_aprox_date() {
        return cust_project_aprox_date;
    }

    public void setCust_project_aprox_date(int cust_project_aprox_date) {
        this.cust_project_aprox_date = cust_project_aprox_date;
    }

    public int getCust_project_dailywage() {
        return cust_project_dailywage;
    }

    public void setCust_project_dailywage(int cust_project_dailywage) {
        this.cust_project_dailywage = cust_project_dailywage;
    }

    //    public wReqModel(String date, String dailyWage, String description, String tel, String pTitle) {
//        this.date = date;
//        this.dailyWage = dailyWage;
//        this.description = description;
//        this.tel = tel;
//        this.pTitle = pTitle;
//    }
//
//    public String getDate() {
//        return date;
//    }
//
//    public void setDate(String date) {
//        this.date = date;
//    }
//
//    public String getDailyWage() {
//        return dailyWage;
//    }
//
//    public void setDailyWage(String dailyWage) {
//        this.dailyWage = dailyWage;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getTel() {
//        return tel;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }
//
//    public String getpTitle() {
//        return pTitle;
//    }
//
//    public void setpTitle(String pTitle) {
//        this.pTitle = pTitle;
//    }
}
