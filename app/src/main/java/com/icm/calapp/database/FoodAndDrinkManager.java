package com.icm.calapp.database;

import com.icm.calapp.model.FoodAndDrinkObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class FoodAndDrinkManager {

    private Realm realm;

    public FoodAndDrinkManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addFoodOrDrink(final FoodAndDrinkObject foodAndDrinkObject) {
        if (!isName(foodAndDrinkObject.getName())) {
            realm.beginTransaction();
            FoodAndDrinkObject object = realm.createObject(FoodAndDrinkObject.class);
            object.setId(foodAndDrinkObject.getId());
            object.setName(foodAndDrinkObject.getName());
            object.setUnit(foodAndDrinkObject.getUnit());
            object.setTypeId(foodAndDrinkObject.getTypeId());
            object.setType(foodAndDrinkObject.getType());
            object.setCalorie(foodAndDrinkObject.getCalorie());
            object.setIslamic(foodAndDrinkObject.islamic());

            realm.commitTransaction();
        }
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<FoodAndDrinkObject> results = realm.where(FoodAndDrinkObject.class)
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
        final RealmResults<FoodAndDrinkObject> results = realm.where(FoodAndDrinkObject.class)
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
        RealmQuery<FoodAndDrinkObject> query = realm.where(FoodAndDrinkObject.class);
        RealmResults<FoodAndDrinkObject> result = query
                .equalTo("name", name)
                .findAll();
        if (result.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<FoodAndDrinkObject> queryAll() {
        RealmQuery<FoodAndDrinkObject> query = realm.where(FoodAndDrinkObject.class);
        RealmResults<FoodAndDrinkObject> result = query
                .findAll();
        ArrayList<FoodAndDrinkObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }
}
