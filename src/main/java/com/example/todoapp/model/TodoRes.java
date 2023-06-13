package com.example.todoapp.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
@Builder
public class TodoRes {
    private List<TodoVo> list;
    private int isMore;
}
