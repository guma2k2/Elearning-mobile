package com.example.elearningmobile.model.course;

public class CourseListGetVM {
    private Long id;
    private String title;
    private String headline;
    private String level;
    private String slug;
    private String totalDurationCourse;
    private int totalLectures;
    private double averageRating;
    private int ratingCount;
    private String image;
    private Long price;
    private boolean free;
    private String createdBy;

    public CourseListGetVM(Long id, String title, String headline, String level, String slug, String totalDurationCourse, int totalLectures, double averageRating, int ratingCount, String image, Long price, boolean free, String createdBy) {
        this.id = id;
        this.title = title;
        this.headline = headline;
        this.level = level;
        this.slug = slug;
        this.totalDurationCourse = totalDurationCourse;
        this.totalLectures = totalLectures;
        this.averageRating = averageRating;
        this.ratingCount = ratingCount;
        this.image = image;
        this.price = price;
        this.free = free;
        this.createdBy = createdBy;
    }

    public CourseListGetVM() {
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTotalDurationCourse() {
        return totalDurationCourse;
    }

    public void setTotalDurationCourse(String totalDurationCourse) {
        this.totalDurationCourse = totalDurationCourse;
    }

    public int getTotalLectures() {
        return totalLectures;
    }

    public void setTotalLectures(int totalLectures) {
        this.totalLectures = totalLectures;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
