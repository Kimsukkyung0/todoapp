package com.example.todoapp;

import com.example.todoapp.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    TodoVo selTodoById(TodoSelDto dto);
    int insTodo(TodoEntity enti);
    List<TodoVo> selTodoList(TodoSelDto dto);
    int getMaxTodo();
}
