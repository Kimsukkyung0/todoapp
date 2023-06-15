package com.example.todoapp;

import com.example.todoapp.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({TodoService.class})
class TodoServiceTest {
    private MockMvc mvc;

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    void insTodo() throws Exception {
        when(mapper.insTodo(any(TodoEntity.class))).thenReturn(1);

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용 입력");
        int result = service.insTodo(dto);

        assertEquals(0,result); //기대한값/넘어온값 비교

        verify(mapper).insTodo(any(TodoEntity.class));
    }
    //autowired 빈등록 하기 위해서 import과정이 필요함
// 전체적인 파일을 올리는 것이 아니기때문에 각자 빈등록해야하는 특성상 반드시 필요한 과정임.
    @Test
    @DisplayName("TodoService - Todo 리스트가져오기")
    void selTodo() throws Exception{
        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1,"테스트","223"));
        mockList.add(new TodoVo(2,"테스트2","223"));
        given(service.getList()).willReturn(mockList);

        when(mapper.getList()).thenReturn(mockList);


        List<TodoVo> list = mapper.getList();
        assertEquals(mockList,list);

        verify(mapper).getList();

//
//        TodoSelDto dto = new TodoSelDto();
//        dto.setPage(1);
//        dto.setRow(5);
//        list = service.getList();
//        int size = list.size();
//        assertEquals(0,size); //기대한값/넘어온값 비교
//
//        verify(mapper).getList();
//    }
}

    @Test
    @DisplayName("TodoService - Todo 완료처리 토글")
    void updTodo() {

        TodoFinDto fDto = new TodoFinDto();
        fDto.setItodo(1);
        TodoEntity entity = new TodoEntity();
        entity.setItodo(fDto.getItodo());
        entity.setFinishYn(1);
        //값넘겨주는 과정
//        given(service.updTodo(any(TodoFinDto.class))).willReturn(1);
        //
        when(mapper.finTodo(entity)).thenReturn(1);

        int result = service.updTodo(fDto);


        assertEquals(entity.getFinishYn(),result);

        verify(mapper).finTodo(entity);

    }


    @Test
    @DisplayName("TodoService - Todo 삭제처리 토글")
    void delTodo() {

        TodoFinDto fDto = new TodoFinDto();
        fDto.setItodo(1);
        when(mapper.delTodo(any(TodoFinDto.class))).thenReturn(1);
        int result = service.delTodo(fDto);

        assertEquals(1,result);//result = 1

        verify(mapper).delTodo(fDto);
    }
}