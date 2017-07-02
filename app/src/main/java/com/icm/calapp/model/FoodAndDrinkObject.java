package com.icm.calapp.model;

import io.realm.RealmObject;

public class FoodAndDrinkObject extends RealmObject {

    private int id;
    private String name;
    private String unit;
    private int typeId;
    private String type;
    private int calorie;
    private boolean isIslamic;

    public FoodAndDrinkObject() {
    }

    public FoodAndDrinkObject(int id, String name, String unit, int typeId, String type, int calorie, boolean isIslamic) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.typeId = typeId;
        this.type = type;
        this.calorie = calorie;
        this.isIslamic = isIslamic;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    public boolean islamic() {
        return isIslamic;
    }

    public void setIslamic(boolean islamic) {
        isIslamic = islamic;
    }
}
