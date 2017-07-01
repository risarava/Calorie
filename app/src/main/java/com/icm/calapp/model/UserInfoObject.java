package com.icm.calapp.model;

import io.realm.RealmObject;

public class UserInfoObject extends RealmObject {

    private int gender;
    private int weight;
    private int height;
    private int age;
    private int religion;
    private int foodAllergy;
    private int activity;
    private int exercise;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getReligion() {
        return religion;
    }

    public void setReligion(int religion) {
        this.religion = religion;
    }

    public int getFoodAllergy() {
        return foodAllergy;
    }

    public void setFoodAllergy(int foodAllergy) {
        this.foodAllergy = foodAllergy;
    }

    public int getActivity() {
        return activity;
    }

    public void setActivity(int activity) {
        this.activity = activity;
    }

    public int getExercise() {
        return exercise;
    }

    public void setExercise(int exercise) {
        this.exercise = exercise;
    }
}
