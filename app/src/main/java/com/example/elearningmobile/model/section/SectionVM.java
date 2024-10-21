package com.example.elearningmobile.model.section;

import com.example.elearningmobile.model.Curriculum;

import java.util.List;

public class SectionVM {
    private Long id;
    private String title;
    private float number;
    private String objective;
    private List<Curriculum> curriculums;
    private boolean toggle = false;

    // Constructor
    public SectionVM(Long id, String title, float number, String objective, List<Curriculum> curriculums) {
        this.id = id;
        this.title = title;
        this.number = number;
        this.objective = objective;
        this.curriculums = curriculums;
    }

    public boolean isToggle() {
        return toggle;
    }

    public void setToggle(boolean toggle) {
        this.toggle = toggle;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public float getNumber() {
        return number;
    }

    public String getObjective() {
        return objective;
    }

    public List<Curriculum> getCurriculums() {
        return curriculums;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNumber(float number) {
        this.number = number;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setCurriculums(List<Curriculum> curriculums) {
        this.curriculums = curriculums;
    }
}
