package com.example.sbb.service;

import com.example.sbb.entity.Answer;
import com.example.sbb.entity.Question;
import com.example.sbb.entity.SiteUser;
import com.example.sbb.exception.DataNotFoundException;
import com.example.sbb.repository.QuestionRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    public void create(String subject, String content, SiteUser user) {
        Question q = new Question();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
    }

    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Question> spec = search(kw);
        // 자바코드로 조회
        // return this.questionRepository.findAll(spec, pageable);
        // Query문으로 조회
        return this.questionRepository.findAllByKeyword(kw, pageable);
    }

    public void modify(Question question, String subject, String content) {
        question.setSubject(subject);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        this.questionRepository.save(question);
    }

    public void delete(Question question) {
        this.questionRepository.delete(question);
    }

    public void vote(Question question, SiteUser siteUser) {
        question.getVoter().add(siteUser);
        this.questionRepository.save(question);
    }

    private Specification<Question> search(String kw) {
        return new Specification<Question>() {
            private static final long serialVersionID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                query.distinct(true); // 중복제거
                Join<Question, SiteUser> u1 = root.join("author", JoinType.LEFT);
                Join<Question, Answer> a = root.join("answerList", JoinType.LEFT);
                Join<Question, SiteUser> u2 = a.join("author", JoinType.LEFT);
                return criteriaBuilder.or(
                        criteriaBuilder.like(root.get("subject"), "%" + kw + "%"), // 제목
                        criteriaBuilder.like(root.get("content"), "%" + kw + "%"), // 내용
                        criteriaBuilder.like(u1.get("username"), "%" + kw + "%"), // 질문 작성자
                        criteriaBuilder.like(a.get("content"), "%" + kw + "%"), // 답변 내용
                        criteriaBuilder.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
                }
        };
    }
}

