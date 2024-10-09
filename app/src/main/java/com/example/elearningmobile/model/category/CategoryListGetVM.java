package com.example.elearningmobile.model.category;

import java.util.List;

public class CategoryListGetVM {

    private int id;
    private String name;
    private String description;
    private boolean isPublish;
    private List<CategoryVM> childrens;

    public CategoryListGetVM() {
    }

    public CategoryListGetVM(int id, String name, String description, boolean isPublish, List<CategoryVM> childrens) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isPublish = isPublish;
        this.childrens = childrens;
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

    public List<CategoryVM> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<CategoryVM> childrens) {
        this.childrens = childrens;
    }
}
