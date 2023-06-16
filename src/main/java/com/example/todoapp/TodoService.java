package com.example.todoapp;

import com.example.todoapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class TodoService {
    private final TodoMapper mapper;

    @Autowired
    public TodoService(TodoMapper mapper){
        this.mapper = mapper;
    }


    TodoVo selTodo(TodoSelDto dto){
        return mapper.selTodoById(dto);
    };

    int insTodo(TodoInsDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setCtnt(dto.getCtnt());

        int result = mapper.insTodo(entity);

        if(result ==1){
            return entity.getItodo();
        }
        return result;

        }
//    TodoRes getTodoList(TodoSelDto dto){
//        dto.setStartIdx((dto.getPage()-1) * dto.getRow());
//        int tmp = (int)Math.ceil(mapper.getMaxTodo()/(double)dto.getRow());
//        int isMore = tmp > dto.getPage() ? 1 : 0;
//        List<TodoVo> list = mapper.selTodoList(dto);
//        return TodoRes.builder()
//                .list(list)
//                .isMore(isMore)
//                .build();
//    }

    List<TodoVo> getList(){
        return mapper.getList();
    }

    int updTodo(TodoFinDto dto){
        int tmp = mapper.getFinYn(dto);//0이나 1임
        TodoEntity entity = new TodoEntity();
        entity.setItodo(dto.getItodo());
        System.out.println(tmp);
        if(tmp==0){ //0이면 미완료
            entity.setFinishYn(1);
        }
        else if(tmp==1){//1이면 0으로 바꿈
            entity.setFinishYn(0);
        }

        return mapper.finTodo(entity);
    }
    int delTodo(TodoFinDto dto){
        return mapper.delTodo(dto);
    }

    }


