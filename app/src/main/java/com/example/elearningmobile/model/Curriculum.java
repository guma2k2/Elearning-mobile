package com.example.elearningmobile.model;

public class Curriculum {
    private Long id;
    private String title;

    private float number;

    private ECurriculumType type;

    private String updatedAt;

    public Curriculum() {
    }

    public Curriculum(Long id, String title, float number, ECurriculumType type) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.type = type;
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

    public float getNumber() {
        return number;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public ECurriculumType getType() {
        return type;
    }

    public void setType(ECurriculumType type) {
        this.type = type;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}
