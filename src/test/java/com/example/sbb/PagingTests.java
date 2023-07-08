package com.example.sbb;

import com.example.sbb.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PagingTests extends SbbApplicationTests{

    @Autowired
    private QuestionService questionService;

//    @Test
//    void createData() {
//        for(int i = 1; i <=300; i++) {
//            String subject = String.format("테스트데이터 : [%03d]", i);
//            String content = "테스트 데이터 내용";
//
//            this.questionService.create(subject, content, null);
//        }
//    }

    @Test
    void Search() {

    }

}
