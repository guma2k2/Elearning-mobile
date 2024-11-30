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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AnswerVM> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerVM> answers) {
        this.answers = answers;
    }
}
