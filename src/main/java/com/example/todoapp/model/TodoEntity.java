package com.example.todoapp.model;

import lombok.Data;

@Data
public class TodoEntity {
    private int itodo;
    private String ctnt;
    private String createdAt;
    private int delYn;
    private int finishYn;
    private int finishedAt;
    private String pic;
}
