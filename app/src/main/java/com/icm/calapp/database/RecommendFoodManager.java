package com.icm.calapp.database;

import com.icm.calapp.model.RecommendFoodObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class RecommendFoodManager {

    private Realm realm;

    public RecommendFoodManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addRecommendFood(final RecommendFoodObject recommendFoodObject) {
        if (!isName(recommendFoodObject.getName())) {
            realm.beginTransaction();
            RecommendFoodObject object = realm.createObject(RecommendFoodObject.class);
            object.setId(recommendFoodObject.getId());
            object.setName(recommendFoodObject.getName());
            object.setUnit(recommendFoodObject.getUnit());
            object.setTypeId(recommendFoodObject.getTypeId());
            object.setType(recommendFoodObject.getType());
            object.setCalorie(recommendFoodObject.getCalorie());
            object.setIslamic(recommendFoodObject.islamic());

            realm.commitTransaction();
        }
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<RecommendFoodObject> results = realm.where(RecommendFoodObject.class)
                .findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Delete all matches
                results.deleteAllFromRealm();
            }
        });
    }

    public void delete(String name) {
        // obtain the results of a query
        final RealmResults<RecommendFoodObject> results = realm.where(RecommendFoodObject.class)
                .equalTo("name", name)
                .findAll();

        // All changes to data must happen in a transaction
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // Delete first matches
                results.deleteFirstFromRealm();
            }
        });
    }

    public boolean isName(String name) {
        RealmQuery<RecommendFoodObject> query = realm.where(RecommendFoodObject.class);
        RealmResults<RecommendFoodObject> result = query
                .equalTo("name", name)
                .findAll();
        if (result.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<RecommendFoodObject> queryAll() {
        RealmQuery<RecommendFoodObject> query = realm.where(RecommendFoodObject.class);
        RealmResults<RecommendFoodObject> result = query
                .findAll();
        ArrayList<RecommendFoodObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }
}
