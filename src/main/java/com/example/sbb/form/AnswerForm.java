package com.example.sbb.form;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AnswerForm {
    
    @NotEmpty(message = "내용은 필수항목")
    private String content;
}
