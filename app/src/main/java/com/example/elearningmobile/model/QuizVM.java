package com.example.elearningmobile.model;

import java.util.ArrayList;
import java.util.List;

public class QuizVM extends Curriculum {
    private String description;

    private List<QuestionVM> questions = new ArrayList<>();

    private boolean finished;

    public QuizVM(String description, List<QuestionVM> questions, boolean finished) {
        this.description = description;
        this.questions = questions;
        this.finished = finished;
    }

    public QuizVM(Long id, String title, float number, ECurriculumType type, String description, List<QuestionVM> questions, boolean finished) {
        super(id, title, number, type);
        this.description = description;
        this.questions = questions;
        this.finished = finished;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<QuestionVM> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionVM> questions) {
        this.questions = questions;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }
}
