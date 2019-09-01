package com.example.taskmanager;

public class Data {

    private int id;
    private String Nomtask;
    private String task;
    private String date;

    public Data() {
    }

    public Data(int id, String nomtask, String task, String date) {
        this.id = id;
        Nomtask = nomtask;
        this.task = task;
        this.date = date;
    }

    public Data(String nomtask, String task, String date) {
        Nomtask = nomtask;
        this.task = task;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomtask() {
        return Nomtask;
    }

    public void setNomtask(String nomtask) {
        Nomtask = nomtask;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
