package com.example.todoapp;

import com.example.todoapp.model.TodoInsDto;
import com.example.todoapp.model.TodoSelDto;
import com.example.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService service;

    @Test
    @DisplayName("TODO - 등록")
    void insTodo() throws Exception {
        //given - 테스트 설정 단계
        given(service.insTodo(any(TodoInsDto.class))).willReturn(3);
        //가짜 ins dto 한테 임무를 부여

        //when단계 - 실제실행단계/보내는 형식 json 형식으로 만들어주기
        String json = "{\" ctnt\":\" 빨래개기\"}";
        ResultActions ra = mvc.perform(post("/api/todo")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON));


        //then - 검증
        ra.andExpect(status().isOk()) //ok = 200 값을 기대한다
         .andExpect(content().string("3"))//MockMvcResultMatchers.content;
         .andDo(print());

        verify(service).insTodo(any());
    }


    @Test
    @DisplayName("TODO - 리스트")
    void getList() throws Exception {
        //given
        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1,"테스트","223","null",0,"2023-05-11"));
        mockList.add(new TodoVo(2,"테스트2","223","null",0,"null"));
//        given(service.getList()).willReturn(mockList);

       //when
        ResultActions ra = mvc.perform(get("/api/todo"));
        List<TodoVo> actualList = service.getList();
        assertEquals(mockList,actualList.size());


        verify(service).getList();
    }
}