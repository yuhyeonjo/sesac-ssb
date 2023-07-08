package com.example.sbb.repository;

import com.example.sbb.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findBySubject(String subject);
    Question findBySubjectAndContent(String subject, String content);
    List<Question> findBySubjectLike(String subject);

    Page<Question> findAll(Pageable pageable);

    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    @Query("select "
            + "distinct root "
            + "from Question root "
            + "left outer join SiteUser u1 on root.author=u1 "
            + "left outer join Answer a on a.question=root "
            + "left outer join SiteUser u2 on a.author=u2 "
            + "where "
            + " root.subject like %:kw% "
            + " or root.content like %:kw% "
            + " or u1.username like %:kw% "
            + " or a.content like %:kw% "
            + " or u2.username like %:kw% ")
        Page<Question> findAllByKeyword(@Param("kw") String kw,Pageable pageable);

    
}
