package com.icm.calapp.database;

import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodAndDrinkObject;
import com.icm.calapp.model.FoodObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class FoodManager {

    private Realm realm;

    public FoodManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addFood(final FoodObject foodObject) {
        realm.beginTransaction();
        FoodObject object = realm.createObject(FoodObject.class);
        object.setId(foodObject.getId());
        object.setName(foodObject.getName());
        object.setUnit(foodObject.getUnit());
        object.setTypeId(foodObject.getTypeId());
        object.setType(foodObject.getType());
        object.setCalorie(foodObject.getCalorie());
        object.setIslamic(foodObject.islamic());

        realm.commitTransaction();
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<FoodObject> results = realm.where(FoodObject.class)
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

    public ArrayList<FoodObject> queryAll() {
        RealmQuery<FoodObject> query = realm.where(FoodObject.class);
        RealmResults<FoodObject> result = query
                .findAll();
        ArrayList<FoodObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }

    public ArrayList<FoodObject> queryByReligion(boolean isIslamic) {
        if (isIslamic) {
            RealmQuery<FoodObject> query = realm.where(FoodObject.class);
            RealmResults<FoodObject> result = query
                    .equalTo("isIslamic", isIslamic)
                    .findAll();
            ArrayList<FoodObject> foodArrayList = new ArrayList<>();
            foodArrayList.addAll(result);
            return foodArrayList;
        } else {
            return queryAll();
        }
    }

    public ArrayList<FoodObject> query(int typeId) {
        RealmQuery<FoodObject> query = realm.where(FoodObject.class);
        RealmResults<FoodObject> result = query
                .equalTo("typeId", typeId)
                .findAll();
        ArrayList<FoodObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }
}
