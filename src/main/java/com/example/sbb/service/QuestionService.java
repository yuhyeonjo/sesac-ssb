package com.example.sbb.service;

import com.example.sbb.entity.Question;
import com.example.sbb.exception.DataNotFoundException;
import com.example.sbb.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.sbb.exception.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    // Question 목록 조회 
    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    // Question 데이터 조회
    public Question getQuestion(Integer id) {
        Optional<Question> question = this.questionRepository.findById(id);
        if(question.isPresent()) {
            return question.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }
}
