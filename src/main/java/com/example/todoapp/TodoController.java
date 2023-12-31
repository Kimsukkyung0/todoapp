package com.example.todoapp;

import com.example.todoapp.model.*;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @GetMapping("/{itodo}")
    public TodoVo getTodo(@PathVariable int itodo){
        TodoSelDto sDto = new TodoSelDto();
        sDto.setItodo(itodo);
        return service.selTodo(sDto);
    }

    @PostMapping
    public int insTodo(@RequestBody TodoInsDto dto){
        return service.insTodo(dto);
    }

//    @GetMapping("/list")
//    public TodoRes getTodoList(@RequestParam @Min(1)int page, @RequestParam (defaultValue = "5") int row){
//        TodoSelDto dto = new TodoSelDto();
//        dto.setRow(row);
//        dto.setPage(page);
//        return service.getTodoList(dto);
//    }
    @GetMapping
    public List<TodoVo> getTodo(){
        return service.getList();
    }

    @PatchMapping
    public int finTodo(@RequestBody TodoFinDto dto){
        return service.updTodo(dto);
    }

    @PatchMapping("/{itodo}")
    public int delYnTodo(@PathVariable int itodo){
        TodoFinDto dto = new TodoFinDto();
        dto.setItodo(itodo);
        return service.delTodo(dto);
    }

    @PostMapping(value = "{itodo}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int postTodoPics(@PathVariable Long itodo, @RequestPart List<MultipartFile> pic){
        TodoPicsInsDto dto = new TodoPicsInsDto();
        dto.setItodo(itodo);
        return service.insTodoPics(dto, pic);
    }

    @PatchMapping(value ="/{itodo}/pic",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public int upTodoPic(@PathVariable int itodo, @RequestPart MultipartFile pic){
        return service.TodoPicUp(itodo,pic);
    }


}
