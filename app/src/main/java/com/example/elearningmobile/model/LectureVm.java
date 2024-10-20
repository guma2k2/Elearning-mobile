package com.example.elearningmobile.model;

public class LectureVm extends Curriculum   {
    private String videoId;

    private String lectureDetails;

    private float duration;

    private String formattedDuration;
    private boolean finished;
    private int watchingSecond = 0;

    private String type = "lecture";

    public LectureVm(String videoId, String lectureDetails, float duration, String formattedDuration, boolean finished, int watchingSecond) {
        this.videoId = videoId;
        this.lectureDetails = lectureDetails;
        this.duration = duration;
        this.formattedDuration = formattedDuration;
        this.finished = finished;
        this.watchingSecond = watchingSecond;
    }

    public LectureVm(Long id, String title, float number, ECurriculumType type, String videoId, String lectureDetails, float duration, String formattedDuration, boolean finished, int watchingSecond) {
        super(id, title, number, type);
        this.videoId = videoId;
        this.lectureDetails = lectureDetails;
        this.duration = duration;
        this.formattedDuration = formattedDuration;
        this.finished = finished;
        this.watchingSecond = watchingSecond;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getLectureDetails() {
        return lectureDetails;
    }

    public void setLectureDetails(String lectureDetails) {
        this.lectureDetails = lectureDetails;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getFormattedDuration() {
        return formattedDuration;
    }

    public void setFormattedDuration(String formattedDuration) {
        this.formattedDuration = formattedDuration;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getWatchingSecond() {
        return watchingSecond;
    }

    public void setWatchingSecond(int watchingSecond) {
        this.watchingSecond = watchingSecond;
    }
}
