package com.example.todoapp;

import com.example.todoapp.model.*;
import com.example.todoapp.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.Externalizable;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TodoService {
    private final TodoMapper mapper;


    @Value("${file.dir}")
    private String fileDir;

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

    int insTodoPics(TodoPicsInsDto dto,List<MultipartFile> pic){


        for (int i = 0; i < pic.size(); i++) {
            String rdName = FileUtils.makeRandomFileNm(pic.get(i).getOriginalFilename());
            dto.setPic(rdName);
            dto.setItodo(dto.getItodo());
            mapper.insTodoPics(dto);

            String mainDir = String.format("%s/todo",fileDir);
            String targetDir = String.format("%s/%d/pics",fileDir,dto.getItodo());
            //d/download/todo/1/pics
            File file = new File(targetDir);

            if (!file.exists()){
                file.mkdirs();
            }
            String finDir = String.format("%s/%s",targetDir,rdName);
            File target = new File(finDir);
            try {
                pic.get(i).transferTo(target);
            } catch (IOException e) {
                return 0;
            }

            try {
                int result = mapper.insTodoPics(dto);//만약에, db에 결과값이 전송이 안되었을떄
                if (result==0){
                throw new Exception ("사진이 전송되지않았습니다");
                }
            }catch(Exception e){
                target.delete();
                return 0;
            }
        }
        return 1;
    };

    public int TodoPicUp(int itodo, MultipartFile pic){


        String rdName = FileUtils.makeRandomFileNm(pic.getOriginalFilename());

        String finDir = String.format("%s/%d",fileDir,itodo);
        String targetDir = String.format("%s/%s",finDir,rdName);
        File target = new File(targetDir);

        TodoPicUpDto dto = new TodoPicUpDto();
        dto.setItodo(itodo);
        dto.setPic(rdName);

        File file = new File(finDir);

        if(!(file.exists())){
            file.mkdirs();
        }

        try{
            pic.transferTo(target);
        }catch(Exception e){
            return -1;
        }



        try{
            int result = mapper.upTodoPic(dto);
            if(result==0){
                throw new Exception("사진이 등록안됐어유");
            }
        }catch (Exception e){

            return 0;
        }

        return 1;


    }
    }


