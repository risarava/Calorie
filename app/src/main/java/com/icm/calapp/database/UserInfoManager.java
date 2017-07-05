package com.icm.calapp.database;

import com.icm.calapp.model.UserInfoObject;

import java.util.ArrayList;

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
        object.setGender(userObject.getGender());
        object.setWeight(userObject.getWeight());
        object.setHeight(userObject.getHeight());
        object.setAge(userObject.getAge());
        object.setReligionId(userObject.getReligionId());
        object.setReligionName(userObject.getReligionName());
        object.setActivity(userObject.getActivity());
        object.setExercise(userObject.getExercise());
        object.setPork(userObject.isPork());
        object.setChicken(userObject.isChicken());
        object.setShrimp(userObject.isShrimp());
        object.setCrab(userObject.isCrab());
        object.setMilk(userObject.isMilk());

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

    public ArrayList<UserInfoObject> queryAll() {
        RealmQuery<UserInfoObject> query = realm.where(UserInfoObject.class);
        RealmResults<UserInfoObject> result = query
                .findAll();
        ArrayList<UserInfoObject> userInfoArrayList = new ArrayList<>();
        userInfoArrayList.addAll(result);
        return userInfoArrayList;
    }
}
