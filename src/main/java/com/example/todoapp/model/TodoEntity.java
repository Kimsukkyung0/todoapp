package com.example.todoapp.model;


public class TodoEntity {
    private int itodo;
    private String ctnt;
    private String createdAt;
    private int delYn;
    private String pic;

    public int getItodo() {
        return itodo;
    }

    public void setItodo(int itodo) {
        this.itodo = itodo;
    }
    public String getCtnt() {
        return ctnt;
    }

    public void setCtnt(String ctnt) {
        this.ctnt = ctnt;
    }
}
