package com.example.todoapp;

import com.example.todoapp.model.*;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService service;

//    @Autowired
//    public TodoController(TodoService service){
//        this.service = service;
//    }
    @GetMapping("{itodo}")
    public TodoVo getTodo(@PathVariable int itodo){
        TodoSelDto sDto = new TodoSelDto();
        sDto.setItodo(itodo);
        return service.selTodo(sDto);
    }

    @PostMapping
    public int insTodo(@RequestBody TodoInsDto dto){
        return service.insTodo(dto);
    }
//
//    @GetMapping("/list")
//    public TodoRes getTodoList(@RequestParam @Min(1)int page, @RequestParam (defaultValue = "5") int row){
//        TodoSelDto dto = new TodoSelDto();
//        dto.setRow(row);
//        dto.setPage(page);
//        return service.getTodoList(dto);
//    }
    @GetMapping
    public List<TodoVo> getList(){
        return service.getList();
    }
    @PatchMapping("{itodo}")
    public int finTodo(@PathVariable int itodo){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(itodo);
        return service.updTodo(entity);
    }
}
