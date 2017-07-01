package com.icm.calapp.database;

import com.icm.calapp.model.UserInfoObject;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class UserInfoManager {

    private Realm realm;

    public UserInfoManager() {
        realm = Realm.getDefaultInstance();
    }

    public void addUserInfo(final UserInfoObject userObject) {
        realm.beginTransaction();
        UserInfoObject object = realm.createObject(UserInfoObject.class);
        object.setWeight(userObject.getWeight());
        object.setHeight(userObject.getHeight());
        object.setAge(userObject.getAge());
        object.setReligion(userObject.getReligion());
        object.setFoodAllergy(userObject.getFoodAllergy());
        object.setActivity(userObject.getActivity());
        object.setExercise(userObject.getExercise());

        realm.commitTransaction();
    }

    public void deleteAll() {
        // obtain the results of a query
        final RealmResults<UserInfoObject> results = realm.where(UserInfoObject.class)
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

    public RealmResults<UserInfoObject> queryAll() {
        RealmQuery<UserInfoObject> query = realm.where(UserInfoObject.class);
        RealmResults<UserInfoObject> result = query
                .findAll();
        return result;
    }
}
