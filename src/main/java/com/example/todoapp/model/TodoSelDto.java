package com.example.todoapp.model;

import lombok.Data;

@Data
public class TodoSelDto {
    private int itodo;
    private int row;
    private int startIdx;
    private int page;
}
