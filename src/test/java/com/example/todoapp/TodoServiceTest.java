package com.example.todoapp;

import com.example.todoapp.model.TodoEntity;
import com.example.todoapp.model.TodoInsDto;
import com.example.todoapp.model.TodoSelDto;
import com.example.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@Import({TodoService.class})//autowired 빈등록 하기 위해서 import과정이 필요함
// 전체적인 파일을 올리는 것이 아니기때문에 각자 빈등록해야하니까 어쩔수없슴..
class TodoServiceTest {
    private MockMvc mvc;

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    void insTodo() throws Exception {
//        TodoEntity entity = new TodoEntity();
//        entity.setCtnt("내용 입력");
//        entity.setItodo(3);
        when(mapper.insTodo(any(TodoEntity.class))).thenReturn(1);

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용 입력");
        int result = service.insTodo(dto);

        assertEquals(0,result); //기대한값/넘어온값 비교

        verify(mapper).insTodo(any(TodoEntity.class));
    }

    @Test
    @DisplayName("TodoService - Todo 리스트가져오기")
    void selTodo() throws Exception{
//        ResultActions resultActions = mvc.perform(
//                MockMvcRequestBuilders.get("/api/todo")
//        );
//        MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();
        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1,"테스트","223","null",0,"2023-05-11"));
        mockList.add(new TodoVo(2,"테스트2","223","null",0,"2023-05-11"));
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
}