package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icm.calapp.MainActivity;
import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.ConvertJSON;
import com.icm.calapp.custom.SpinnerCustom;
import com.icm.calapp.database.DrinkManager;
import com.icm.calapp.database.FoodAndDrinkManager;
import com.icm.calapp.database.FoodManager;
import com.icm.calapp.database.UserInfoManager;
import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodObject;
import com.icm.calapp.model.UserInfoObject;

import java.util.ArrayList;

public class UserInfoActivity extends AbstractAppCompatActivity implements View.OnClickListener {

    private int gender = 0;
    private Intent intent;

    private Spinner spnReligion;
    private Spinner spnActivity;
    private Spinner spnExercise;
    private EditText edtWeight;
    private EditText edtHeight;
    private EditText edtAge;
    private EditText edtReligion;
    private EditText edtActivity;
    private EditText edtExercise;
    private LinearLayout lloPork;
    private LinearLayout lloChicken;
    private LinearLayout lloShrimp;
    private LinearLayout lloCrab;
    private LinearLayout lloMilk;

    private FoodManager foodManager;
    private DrinkManager drinkManager;
    private FoodAndDrinkManager foodAndDrinkManager;
    private UserInfoManager userInfoManager;

    @Override
    protected int setContentView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_user_info);
        spnReligion = (Spinner) findViewById(R.id.spinnerReligion);
        spnActivity = (Spinner) findViewById(R.id.spinnerActivity);
        spnExercise = (Spinner) findViewById(R.id.spinnerExercise);
        edtWeight = (EditText) findViewById(R.id.edittextWeight);
        edtHeight = (EditText) findViewById(R.id.edittextHeight);
        edtAge = (EditText) findViewById(R.id.edittextAge);
        edtReligion = (EditText) findViewById(R.id.edittextReligion);
        edtActivity = (EditText) findViewById(R.id.edittextActivity);
        edtExercise = (EditText) findViewById(R.id.edittextExercise);
        lloPork = (LinearLayout) findViewById(R.id.linearlayoutPork);
        lloChicken = (LinearLayout) findViewById(R.id.linearlayoutChicken);
        lloShrimp = (LinearLayout) findViewById(R.id.linearlayoutShrimp);
        lloCrab = (LinearLayout) findViewById(R.id.linearlayoutCrab);
        lloMilk = (LinearLayout) findViewById(R.id.linearlayoutMilk);

        lloPork.setOnClickListener(this);
        lloChicken.setOnClickListener(this);
        lloShrimp.setOnClickListener(this);
        lloCrab.setOnClickListener(this);
        lloMilk.setOnClickListener(this);

        foodManager = new FoodManager();
        drinkManager = new DrinkManager();
        foodAndDrinkManager = new FoodAndDrinkManager();
        userInfoManager = new UserInfoManager();

        foodAndDrinkManager.deleteAll();
        userInfoManager.deleteAll();
        gender = getIntent().getIntExtra(SelectGenderActivity.EXTRA_GENDER, 0);
    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        setSpinner();

    }

    public void okButton(View view) {
        if (isValidate()) {
            setFood();
            setDrink();
            intent = new Intent(activity, MainActivity.class);
            startActivity(intent);
        }

    }

    private void setFood() {
        //load food from json file
        ArrayList<FoodObject> foodArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "food.json"), new TypeToken<ArrayList<FoodObject>>() {
        }.getType());
        //clear food all in data base
        foodManager.deleteAll();
        //add food to data base
        for (FoodObject foodObject : foodArrayList) {
            if (foodObject.getTypeId() == FoodDrinkAllActivity.TYPE_CHICKEN) {
                if (!lloChicken.isSelected()) {
                    //chicken type
                    addFood(foodObject);
                }
            } else if (foodObject.getTypeId() == FoodDrinkAllActivity.TYPE_SHRIMP) {
                if (!lloShrimp.isSelected()) {
                    //shrimp type
                    addFood(foodObject);
                }
            } else if (foodObject.getTypeId() == FoodDrinkAllActivity.TYPE_PORK) {
                if (!lloPork.isSelected()) {
                    // is selected islamic
                    if (spnReligion.getSelectedItemPosition() == 1) {
                        //pork type
                        addFood(foodObject);
                    }
                }
            } else if (foodObject.getTypeId() == FoodDrinkAllActivity.TYPE_CRAB) {
                if (!lloCrab.isSelected()) {
                    //crab type
                    addFood(foodObject);
                }
            } else {
                addFood(foodObject);
            }
        }
    }

    private void addFood(FoodObject foodObject) {
        foodManager.addFood(new FoodObject(foodObject.getId(),
                foodObject.getName(),
                foodObject.getUnit(),
                foodObject.getTypeId(),
                foodObject.getType(),
                foodObject.getCalorie(),
                foodObject.islamic()));
    }

    private void addDrink(DrinkObject drinkObject) {
        drinkManager.addDrink(new DrinkObject(drinkObject.getId(),
                drinkObject.getName(),
                drinkObject.getUnit(),
                drinkObject.getTypeId(),
                drinkObject.getType(),
                drinkObject.getCalorie(),
                drinkObject.islamic()));
    }

    private void setDrink() {
        //load drink from json file
        ArrayList<DrinkObject> drinkArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "drink.json"), new TypeToken<ArrayList<DrinkObject>>() {
        }.getType());
        //clear drink all in data base
        drinkManager.deleteAll();
        //add drink to data base
        for (DrinkObject drinkObject : drinkArrayList) {
            if (drinkObject.getTypeId() == FoodDrinkAllActivity.TYPE_MILK) {
                if (!lloMilk.isSelected()) {
                    //milk type
                    addDrink(drinkObject);
                }
            } else {
                addDrink(drinkObject);
            }
        }
    }

    private void setSpinner() {
        SpinnerCustom.setSpinner(activity, spnReligion, R.array.religion_list, 0);
        SpinnerCustom.setSpinner(activity, spnActivity, R.array.activity_list, 0);
        SpinnerCustom.setSpinner(activity, spnExercise, R.array.exercise_list, 0);
    }

    private boolean isValidate() {
        boolean isValid = false;
        String weightStr = edtWeight.getText().toString().trim();
        String heightStr = edtHeight.getText().toString().trim();
        String ageStr = edtAge.getText().toString().trim();

        if (weightStr.isEmpty()) {
            edtWeight.setError(activity.getResources().getString(R.string.error_fill_text));
            edtWeight.requestFocus();
        } else if (heightStr.isEmpty()) {
            edtHeight.setError(activity.getResources().getString(R.string.error_fill_text));
            edtHeight.requestFocus();
        } else if (ageStr.isEmpty()) {
            edtAge.setError(activity.getResources().getString(R.string.error_fill_text));
            edtAge.requestFocus();
        } else if (spnReligion.getSelectedItemPosition() == 0) {
            edtReligion.setError(activity.getResources().getString(R.string.error_selected));
            edtReligion.requestFocus();
        } else if (spnActivity.getSelectedItemPosition() == 0) {
            edtActivity.setError(activity.getResources().getString(R.string.error_selected));
            edtActivity.requestFocus();
        } else if (spnExercise.getSelectedItemPosition() == 0) {
            edtExercise.setError(activity.getResources().getString(R.string.error_selected));
            edtExercise.requestFocus();
        } else {
            int weight = 0;
            int height = 0;
            int age = 0;

            try {
                weight = Integer.parseInt(weightStr);
                height = Integer.parseInt(heightStr);
                age = Integer.parseInt(ageStr);

            } catch (Exception ignored) {

            }
            UserInfoObject userInfoObject = new UserInfoObject();
            userInfoObject.setGender(gender);
            userInfoObject.setWeight(weight);
            userInfoObject.setHeight(height);
            userInfoObject.setAge(age);
            userInfoObject.setReligionId(spnReligion.getSelectedItemPosition());
            userInfoObject.setReligionName(spnReligion.getSelectedItem().toString());
            userInfoObject.setPork(!lloPork.isSelected());
            userInfoObject.setChicken(!lloChicken.isSelected());
            userInfoObject.setShrimp(!lloShrimp.isSelected());
            userInfoObject.setCrab(!lloCrab.isSelected());
            userInfoObject.setMilk(!lloMilk.isSelected());
            userInfoObject.setActivity(spnActivity.getSelectedItemPosition());
            userInfoObject.setExercise(spnExercise.getSelectedItemPosition());
            //save to data base
            userInfoManager.addUserInfo(userInfoObject);
            isValid = true;
        }
        return isValid;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linearlayoutPork:
                lloPork.setSelected(!lloPork.isSelected());
                break;
            case R.id.linearlayoutChicken:
                lloChicken.setSelected(!lloChicken.isSelected());
                break;
            case R.id.linearlayoutShrimp:
                lloShrimp.setSelected(!lloShrimp.isSelected());
                break;
            case R.id.linearlayoutCrab:
                lloCrab.setSelected(!lloCrab.isSelected());
                break;
            case R.id.linearlayoutMilk:
                lloMilk.setSelected(!lloMilk.isSelected());
                break;

            default:
                break;
        }
    }
}
