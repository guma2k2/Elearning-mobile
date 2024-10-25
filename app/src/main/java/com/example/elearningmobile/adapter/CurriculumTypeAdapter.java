package com.example.elearningmobile.adapter;

import com.example.elearningmobile.model.Curriculum;
import com.example.elearningmobile.model.LectureVm;
import com.example.elearningmobile.model.QuizVM;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class CurriculumTypeAdapter implements JsonDeserializer<Curriculum> {
    @Override
    public Curriculum deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String curriculumType = jsonObject.get("type").getAsString();

        try {
            if ("lecture".equalsIgnoreCase(curriculumType)) {
                return context.deserialize(jsonObject, LectureVm.class);
            } else if ("quiz".equalsIgnoreCase(curriculumType)) {
                return context.deserialize(jsonObject, QuizVM.class);
            } else {
                throw new JsonParseException("Unknown curriculum type: " + curriculumType);
            }
        } catch (Exception e) {
            System.err.println("Failed to deserialize Curriculum: " + e.getMessage());
            e.printStackTrace();
            return null;  // Or handle this with a fallback
        }
    }
}
