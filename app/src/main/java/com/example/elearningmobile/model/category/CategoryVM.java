package com.example.elearningmobile.model.category;

public class CategoryVM {

    private int id;
    private String name;
    private String description;
    private boolean isPublish;
    private String createdAt;
    private String updatedAt;
    private int parentId;

    public CategoryVM() {
    }

    public CategoryVM(int id, String name, String description, boolean isPublish, String createdAt, String updatedAt, int parentId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublish = isPublish;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPublish() {
        return isPublish;
    }

    public void setPublish(boolean publish) {
        isPublish = publish;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }
}
