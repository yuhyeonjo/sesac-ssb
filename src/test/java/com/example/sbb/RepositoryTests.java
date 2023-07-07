package com.example.sbb;

import com.example.sbb.entity.Answer;
import com.example.sbb.entity.Question;
import com.example.sbb.repository.AnswerRepository;
import com.example.sbb.repository.QuestionRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class RepositoryTests extends SbbApplicationTests{

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // 데이터 저장
//    @Test
//    void InsertTest() {
//        Question q1 = new Question();
//        q1.setSubject("Jpa가 무엇인가요?");
//        q1.setContent("Jpa에 대해서 알고 싶어요.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1); // 첫 번째 질문 저장
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문입니다.");
//        q2.setContent("id는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2); // 두 번재 질문 저장
//    }
//
//
//    // Question 테이블에 저장된 모든 데이터를 조회
//   @Test
//    void FindTest() {
//        List<Question> all = this.questionRepository.findAll();
//        assertEquals(2, all.size());
//
//        Question q = all.get(0);
//        assertEquals("Jpa가 무엇인가요?", q.getSubject());
//    }
//
//    //  Question 엔티티의 Id값으로 데이터를 조회
//    @Test
//    void FindByIdTest() {
//        Optional<Question> oq = this.questionRepository.findById(1);
//        if(oq.isPresent()) {
//            Question q = oq.get();
//            assertEquals("Jpa가 무엇인가요?", q.getSubject());
//        }
//    }
//
//
//    // subject 컬럼 조건으로 where문
//    @Test
//    void FindBySubjectTest() {
//        Question q = this.questionRepository.findBySubject("Jpa가 무엇인가요?");
//        assertEquals(1, q.getId());
//
//    }
//
//    // subject, content 컬럼이 and 조건으로 where문
//    @Test
//    void FindBySubjectAndContentTest() {
//        Question q = this.questionRepository.findBySubjectAndContent("Jpa가 무엇인가요?", "Jpa에 대해서 알고 싶어요.");
//        assertEquals(1, q.getId());
//    }
//
//    // 특정 문자열이 포함되어 있는 데이터를 조회
//    @Test
//    void FindBySubjectLikeTest() {
//        List<Question> qList = this.questionRepository.findBySubjectLike("Jpa%");
//        Question q = qList.get(0);
//        assertEquals("Jpa가 무엇인가요?", q.getSubject());
//    }
//
//    // 데이터 수정
//    @Test
//    void UpdateTest() {
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q =  oq.get();
//        q.setSubject("Jpa는 무엇인가요?(수정2)");
//        this.questionRepository.save(q);
//    }

    // 데이터 삭제
    @Test
    void DeleteTest() {
        assertEquals(2, this.questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1, this.questionRepository.count());

    }


    // 답변 데이터 생성 후 저장
    @Test
    void AnswerInsertTest() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        Answer a = new Answer();
        a.setContent("네 자동생성됩니다.");
        a.setQuestion(q); // 어떤 질문의 답변인지 알기 위해 Question 객체 필요
        this.answerRepository.save(a);
    }


    @Test
    void AnswerFindByIdTest() {
        Optional<Answer> oa = this.answerRepository.findById(1);
        assertTrue(oa.isPresent());
        Answer a = oa.get();
        assertEquals(2, a.getQuestion().getId());
    }

    @Transactional
    @Test
    void AnswerFindByIdTest2() {
        Optional<Question> oq = this.questionRepository.findById(2);
        assertTrue(oq.isPresent());
        Question q = oq.get();

        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }




}
