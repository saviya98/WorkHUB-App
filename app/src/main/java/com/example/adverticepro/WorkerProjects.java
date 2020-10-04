package com.example.adverticepro;

public class WorkerProjects {

    private String workerName; //initializing variables
    private String workerNumber;
    private String workerProDetails;

    public WorkerProjects() { //constructor
    }

    public String getWorkerName() {  //getters and setters
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(String workerNumber) {
        this.workerNumber = workerNumber;
    }

    public String getWorkerProDetails() {
        return workerProDetails;
    }

    public void setWorkerProDetails(String workerProDetails) {
        this.workerProDetails = workerProDetails;
    }
}
