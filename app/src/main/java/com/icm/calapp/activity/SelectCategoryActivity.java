package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractActivity;

public class SelectCategoryActivity extends AbstractActivity {

    public static final int CATEGORY_FOOD = 88;
    public static final int CATEGORY_DRINK = 99;

    private Intent intent;

    @Override
    protected int setContentView() {
        return R.layout.activity_select_category;
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {

    }

    @Override
    protected void setupUI() {

    }

    public void foodButton(View view) {
        intent = new Intent(activity, FoodDrinkListActivity.class);
        intent.putExtra(FoodDrinkListActivity.EXTRA_TYPE, CATEGORY_FOOD);
        startActivity(intent);
    }

    public void drinkButton(View view) {
        intent = new Intent(activity, FoodDrinkListActivity.class);
        intent.putExtra(FoodDrinkListActivity.EXTRA_TYPE, CATEGORY_DRINK);
        startActivity(intent);
    }

    public void bacKButton(View view) {
        onBackPressed();
    }
}
