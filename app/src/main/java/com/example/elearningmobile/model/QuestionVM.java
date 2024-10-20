package com.example.elearningmobile.model;

import java.util.List;

public class QuestionVM {

    private Long id;

    private String title;
    private List<AnswerVM> answers;

    public QuestionVM() {
    }

    public QuestionVM(Long id, String title, List<AnswerVM> answers) {
        this.id = id;
        this.title = title;
        this.answers = answers;
    }
}
