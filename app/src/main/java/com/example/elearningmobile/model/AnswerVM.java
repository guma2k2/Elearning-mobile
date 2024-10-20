package com.example.elearningmobile.model;

public class AnswerVM {

    private Long id;
    private String answerText;
    private String reason;
    private boolean correct;

    public AnswerVM(Long id, String answerText, String reason, boolean correct) {
        this.id = id;
        this.answerText = answerText;
        this.reason = reason;
        this.correct = correct;
    }

    public AnswerVM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
