package com.example.elearningmobile.model.course;

public class CourseLearningVm {

    private  CourseVM course;
    private  Long sectionId;
    private  Long curriculumId;
    private  Integer secondWatched;
    private  String type;

    public CourseLearningVm(CourseVM course, Long sectionId, Long curriculumId, Integer secondWatched, String type) {
        this.course = course;
        this.sectionId = sectionId;
        this.curriculumId = curriculumId;
        this.secondWatched = secondWatched;
        this.type = type;
    }

    public CourseVM getCourse() {
        return course;
    }

    public void setCourse(CourseVM course) {
        this.course = course;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(Long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public Integer getSecondWatched() {
        return secondWatched;
    }

    public void setSecondWatched(Integer secondWatched) {
        this.secondWatched = secondWatched;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
