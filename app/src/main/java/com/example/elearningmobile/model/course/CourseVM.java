package com.example.elearningmobile.model.course;

import com.example.elearningmobile.model.section.SectionVM;
import com.example.elearningmobile.model.user.UserProfileVM;

import java.util.List;

public class CourseVM {
    private Long id;
    private String title;
    private String headline;
    private String slug;
    private String[] objectives;
    private String[] requirements;
    private String[] targetAudiences;
    private String description;
    private String level;
    private String image;
    private String createdAt;
    private String updatedAt;
    private boolean free;
    private Long price;
    private boolean isPublish;
    private Integer categoryId;
    private Integer topicId;
    private int ratingCount;
    private double averageRating;
    private int totalLectureCourse;
    private String totalDurationCourse;
    private String createdBy;
    private List<SectionVM> sections;
    private UserProfileVM user;
    private boolean learning;
    private String breadcrumb;

    public CourseVM(Long id, String title, String headline, String slug, String[] objectives, String[] requirements, String[] targetAudiences, String description, String level, String image, String createdAt, String updatedAt, boolean free, Long price, boolean isPublish, Integer categoryId, Integer topicId, int ratingCount, double averageRating, int totalLectureCourse, String totalDurationCourse, String createdBy, List<SectionVM> sections, UserProfileVM user, boolean learning, String breadcrumb) {
        this.id = id;
        this.title = title;
        this.headline = headline;
        this.slug = slug;
        this.objectives = objectives;
        this.requirements = requirements;
        this.targetAudiences = targetAudiences;
        this.description = description;
        this.level = level;
        this.image = image;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.free = free;
        this.price = price;
        this.isPublish = isPublish;
        this.categoryId = categoryId;
        this.topicId = topicId;
        this.ratingCount = ratingCount;
        this.averageRating = averageRating;
        this.totalLectureCourse = totalLectureCourse;
        this.totalDurationCourse = totalDurationCourse;
        this.createdBy = createdBy;
        this.sections = sections;
        this.user = user;
        this.learning = learning;
        this.breadcrumb = breadcrumb;
    }

    public CourseVM() {
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

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String[] getObjectives() {
        return objectives;
    }

    public void setObjectives(String[] objectives) {
        this.objectives = objectives;
    }

    public String[] getRequirements() {
        return requirements;
    }

    public void setRequirements(String[] requirements) {
        this.requirements = requirements;
    }

    public String[] getTargetAudiences() {
        return targetAudiences;
    }

    public void setTargetAudiences(String[] targetAudiences) {
        this.targetAudiences = targetAudiences;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalLectureCourse() {
        return totalLectureCourse;
    }

    public void setTotalLectureCourse(int totalLectureCourse) {
        this.totalLectureCourse = totalLectureCourse;
    }

    public String getTotalDurationCourse() {
        return totalDurationCourse;
    }

    public void setTotalDurationCourse(String totalDurationCourse) {
        this.totalDurationCourse = totalDurationCourse;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<SectionVM> getSections() {
        return sections;
    }

    public void setSections(List<SectionVM> sections) {
        this.sections = sections;
    }

    public UserProfileVM getUser() {
        return user;
    }

    public void setUser(UserProfileVM user) {
        this.user = user;
    }

    public boolean isLearning() {
        return learning;
    }

    public void setLearning(boolean learning) {
        this.learning = learning;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }
}
