package com.icm.calapp.database;

import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodAndDrinkObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DrinkManager {

    private Realm realm;

    public DrinkManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addDrink(final DrinkObject drinkObject) {
        realm.beginTransaction();
        DrinkObject object = realm.createObject(DrinkObject.class);
        object.setId(drinkObject.getId());
        object.setName(drinkObject.getName());
        object.setUnit(drinkObject.getUnit());
        object.setTypeId(drinkObject.getTypeId());
        object.setType(drinkObject.getType());
        object.setCalorie(drinkObject.getCalorie());
        object.setIslamic(drinkObject.islamic());

        realm.commitTransaction();
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<DrinkObject> results = realm.where(DrinkObject.class)
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

    public ArrayList<DrinkObject> queryAll() {
        RealmQuery<DrinkObject> query = realm.where(DrinkObject.class);
        RealmResults<DrinkObject> result = query
                .findAll();
        ArrayList<DrinkObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }

    public ArrayList<DrinkObject> query(int typeId) {
        RealmQuery<DrinkObject> query = realm.where(DrinkObject.class);
        RealmResults<DrinkObject> result = query
                .equalTo("typeId", typeId)
                .findAll();
        ArrayList<DrinkObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }
}
