package com.example.elearningmobile.ultity;

public class DataManager {
    private static long courseId;

    public static void setCourseId(long id) {
        courseId = id;
    }

    public static long getCourseId() {
        return courseId;
    }
}
