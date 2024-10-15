package com.example.elearningmobile.model.learning;

import com.example.elearningmobile.model.course.CourseListGetVM;

public class LearningCourseVM {
    private Long id;
    CourseListGetVM course;
    int percentFinished;

    public LearningCourseVM() {
    }

    public LearningCourseVM(Long id, CourseListGetVM course, int percentFinished) {
        this.id = id;
        this.course = course;
        this.percentFinished = percentFinished;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CourseListGetVM getCourse() {
        return course;
    }

    public void setCourse(CourseListGetVM course) {
        this.course = course;
    }

    public int getPercentFinished() {
        return percentFinished;
    }

    public void setPercentFinished(int percentFinished) {
        this.percentFinished = percentFinished;
    }
}
