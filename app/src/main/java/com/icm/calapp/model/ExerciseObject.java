package com.icm.calapp.model;

import io.realm.RealmObject;

public class ExerciseObject extends RealmObject{

    private int id;
    private String name;
    private int levelId;
    private String levelName;
    private int caloriePerHour;
    private int caloriePerMin;

    public ExerciseObject() {
    }

    public ExerciseObject(int id, String name, int levelId, String levelName, int caloriePerHour, int caloriePerMin) {
        this.id = id;
        this.name = name;
        this.levelId = levelId;
        this.levelName = levelName;
        this.caloriePerHour = caloriePerHour;
        this.caloriePerMin = caloriePerMin;
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

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public int getCaloriePerHour() {
        return caloriePerHour;
    }

    public void setCaloriePerHour(int caloriePerHour) {
        this.caloriePerHour = caloriePerHour;
    }

    public int getCaloriePerMin() {
        return caloriePerMin;
    }

    public void setCaloriePerMin(int caloriePerMin) {
        this.caloriePerMin = caloriePerMin;
    }
}
