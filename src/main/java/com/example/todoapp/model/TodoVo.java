package com.example.todoapp.model;

import lombok.Data;
import lombok.Getter;

@Getter
public class TodoVo {
    private int itodo;
    private String ctnt;
    private String createdAt;
}