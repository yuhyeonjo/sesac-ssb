package com.example.sbb.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionForm {

    @NotEmpty(message = "제목은 필수 항목")
    @Size(max= 200)
    private String subject;

    @NotEmpty(message = "내용은 필수 항목")
    private String content;

}
