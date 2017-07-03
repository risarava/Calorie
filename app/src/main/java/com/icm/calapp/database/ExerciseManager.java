package com.icm.calapp.database;

import com.icm.calapp.model.ExerciseObject;
import com.icm.calapp.model.FoodAndDrinkObject;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ExerciseManager {

    private Realm realm;

    public ExerciseManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addExercise(final ExerciseObject exerciseObject) {
        if (!isName(exerciseObject.getName())) {
            realm.beginTransaction();
            ExerciseObject object = realm.createObject(ExerciseObject.class);
            object.setId(exerciseObject.getId());
            object.setName(exerciseObject.getName());
            object.setLevelId(exerciseObject.getLevelId());
            object.setLevelName(exerciseObject.getLevelName());
            object.setCaloriePerHour(exerciseObject.getCaloriePerHour());
            object.setCaloriePerMin(exerciseObject.getCaloriePerMin());

            realm.commitTransaction();
        }
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<ExerciseObject> results = realm.where(ExerciseObject.class)
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
        final RealmResults<ExerciseObject> results = realm.where(ExerciseObject.class)
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
        RealmQuery<ExerciseObject> query = realm.where(ExerciseObject.class);
        RealmResults<ExerciseObject> result = query
                .equalTo("name", name)
                .findAll();
        if (result.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<ExerciseObject> queryAll() {
        RealmQuery<ExerciseObject> query = realm.where(ExerciseObject.class);
        RealmResults<ExerciseObject> result = query
                .findAll();
        ArrayList<ExerciseObject> foodArrayList = new ArrayList<>();
        foodArrayList.addAll(result);
        return foodArrayList;
    }
}
