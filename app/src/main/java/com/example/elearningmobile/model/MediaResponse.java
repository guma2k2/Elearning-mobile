package com.example.elearningmobile.model;

import java.io.Serializable;

public class MediaResponse implements Serializable {

    private String id;

    private String url;

    private String duration;

    private String fileName;

    public MediaResponse() {
    }

    public MediaResponse(String id, String url, String duration, String fileName) {
        this.id = id;
        this.url = url;
        this.duration = duration;
        this.fileName = fileName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
