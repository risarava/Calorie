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
import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodAndDrinkObject;
import com.icm.calapp.model.FoodObject;

import java.util.ArrayList;

public class UserInfoActivity extends AbstractAppCompatActivity {

    private int gender = 0;
    private Intent intent;

    private Spinner spnReligion;
    private Spinner spnActivity;
    private Spinner spnExercise;
    private EditText edtWeight;
    private EditText edtHeight;
    private EditText edtAge;

    private FoodManager foodManager;
    private DrinkManager drinkManager;
    private FoodAndDrinkManager foodAndDrinkManager;

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

        foodManager = new FoodManager();
        drinkManager = new DrinkManager();
        foodAndDrinkManager = new FoodAndDrinkManager();

        foodAndDrinkManager.deleteAll();
        gender = getIntent().getIntExtra(SelectGenderActivity.EXTRA_GENDER, 0);
    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        setSpinner();

    }

    public void okButton(View view) {
        setFood();
        setDrink();
        intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
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
            foodManager.addFood(new FoodObject(foodObject.getId(),
                    foodObject.getName(),
                    foodObject.getUnit(),
                    foodObject.getTypeId(),
                    foodObject.getType(),
                    foodObject.getCalorie(),
                    foodObject.islamic()));
        }
    }

    private void setDrink() {
        //load drink from json file
        ArrayList<DrinkObject> drinkArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "drink.json"), new TypeToken<ArrayList<DrinkObject>>() {
        }.getType());
        //clear drink all in data base
        drinkManager.deleteAll();
        //add drink to data base
        for (DrinkObject foodObject : drinkArrayList) {
            drinkManager.addDrink(new DrinkObject(foodObject.getId(),
                    foodObject.getName(),
                    foodObject.getUnit(),
                    foodObject.getTypeId(),
                    foodObject.getType(),
                    foodObject.getCalorie(),
                    foodObject.islamic()));
        }
    }

    private void setSpinner() {
        SpinnerCustom.setSpinner(activity, spnReligion, R.array.religion_list, 0);
        SpinnerCustom.setSpinner(activity, spnActivity, R.array.activity_list, 0);
        SpinnerCustom.setSpinner(activity, spnExercise, R.array.exercise_list, 0);
    }
}
