package com.example.todoapp;

import com.example.todoapp.model.TodoEntity;
import com.example.todoapp.model.TodoFinDto;
import com.example.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;

@MybatisTest
@ActiveProfiles("test")
//default yaml설정 , 대시 "---" 하단부의 on-profile 에 추가한 설정까지 적용한다는 의미
//활성화시킬 설정파일을 지정하고있다.

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //데이터베이스 자동설정 .
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;//service, controller 테스트 시에는 가짜 service를 만들어썼지만,
    // mapper 테스트 시에는 실제 dao(interface/mapper) 가 적용

    @Test
    @DisplayName("TODO Mapper - 추가테스트")
    void insTodo() {
        //given
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("테스트");

        int result = mapper.insTodo(entity);
        System.out.println(entity.getItodo());//use generate key 가 잘 실행되는지!
        assertEquals(1,result);
        assertEquals(5,entity.getItodo());
    }

    @Test
    @DisplayName("TODO Mapper - 리스트가져오기")
    void getList() {
        List<TodoVo> list = mapper.getList();

        assertEquals(4,list.size());//사이즈테스트
        TodoVo vo = list.get(0);
        assertEquals(1,vo.getItodo());
        assertEquals("string",vo.getCtnt());


    }

    @Test
    @DisplayName("TODO Mapper - 완료 설정 테스트")
    void FinTodo() {
        TodoEntity entity = new TodoEntity();
        entity.setItodo(1);

        int result = mapper.finTodo(entity);
        assertEquals(1,result);
    }

    @Test
    @DisplayName("TODO Mapper - 삭제테스트")
    void delTodo() {
        int expectedResult = 1;
        TodoFinDto dto = new TodoFinDto();
        dto.setItodo(anyInt());
        int actualresult = mapper.delTodo(dto);
        assertEquals(expectedResult,actualresult);
    }
}