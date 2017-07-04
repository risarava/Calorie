package com.icm.calapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icm.calapp.R;
import com.icm.calapp.adapter.FoodAndDrinkAllAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.ConvertJSON;
import com.icm.calapp.custom.SpinnerCustom;
import com.icm.calapp.database.DrinkManager;
import com.icm.calapp.database.FoodAndDrinkManager;
import com.icm.calapp.database.FoodManager;
import com.icm.calapp.database.RecommendFoodManager;
import com.icm.calapp.database.UserInfoManager;
import com.icm.calapp.model.CategoryObject;
import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodAndDrinkObject;
import com.icm.calapp.model.FoodObject;
import com.icm.calapp.model.RecommendFoodObject;
import com.icm.calapp.model.UserInfoObject;

import java.util.ArrayList;

public class FoodDrinkAllActivity extends AbstractAppCompatActivity {
    public static final int TYPE_CHICKEN = 1;
    public static final int TYPE_SHRIMP = 2;
    public static final int TYPE_PORK = 3;
    public static final int TYPE_CRAB = 4;
    public static final int TYPE_WATER = 5;
    public static final int TYPE_YOGURT = 6;
    public static final int TYPE_MILK = 7;
    public static final String EXTRA_IS_RECOMMEND = "IS_RECOMMEND";

    private static final int CATEGORY_FOOD = 1;
    private static final int CATEGORY_DRINK = 2;

    private boolean isRecommend = false;

    private RecyclerView recyclerView;
    private Spinner spnCategory;
    private Spinner spnType;

    private LinearLayoutManager linearLayoutManager;
    private FoodAndDrinkAllAdapter adapter;
    private FoodManager foodManager;
    private DrinkManager drinkManager;
    private FoodAndDrinkManager foodAndDrinkManager;
    private UserInfoManager userInfoManager;
    private RecommendFoodManager recommendFoodManager;

    @Override
    protected int setContentView() {
        return R.layout.activity_food_drink_list;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_food_and_drink);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        spnCategory = (Spinner) findViewById(R.id.spinnerCategory);
        spnType = (Spinner) findViewById(R.id.spinnerType);

        foodManager = new FoodManager();
        drinkManager = new DrinkManager();
        foodAndDrinkManager = new FoodAndDrinkManager();
        userInfoManager = new UserInfoManager();
        recommendFoodManager = new RecommendFoodManager();

        isRecommend = getIntent().getBooleanExtra(EXTRA_IS_RECOMMEND, false);

    }

    @Override
    protected void setupUI() {
        initRecycleView();

        setSpinner();
    }

    public void cancelButton(View view) {
        onBackPressed();
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new FoodAndDrinkAllAdapter(activity);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new FoodAndDrinkAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                FoodAndDrinkObject object = adapter.getFoodAndDrinkArrayList().get(position);
                if (isRecommend) {
                    recommendFoodManager.addRecommendFood(new RecommendFoodObject(
                            object.getId(), object.getName(),
                            object.getUnit(), object.getTypeId(),
                            object.getType(), object.getCalorie(),
                            object.islamic()));
                } else {
                    foodAndDrinkManager.addFoodOrDrink(new FoodAndDrinkObject(
                            object.getId(), object.getName(),
                            object.getUnit(), object.getTypeId(),
                            object.getType(), object.getCalorie(),
                            object.islamic()));
                }
                onBackPressed();
            }
        });
    }

    private ArrayList<FoodAndDrinkObject> addAllItemFood(ArrayList<FoodObject> foodAndDrinkObjectArrayList) {
        ArrayList<FoodAndDrinkObject> arrayList = new ArrayList<>();
        for (FoodObject object : foodAndDrinkObjectArrayList) {
            arrayList.add(new FoodAndDrinkObject(
                    object.getId(), object.getName(),
                    object.getUnit(), object.getTypeId(),
                    object.getType(), object.getCalorie(),
                    object.islamic()));
        }
        return arrayList;
    }

    private ArrayList<FoodAndDrinkObject> addAllItemDrink(ArrayList<DrinkObject> foodAndDrinkObjectArrayList) {
        ArrayList<FoodAndDrinkObject> arrayList = new ArrayList<>();
        for (DrinkObject object : foodAndDrinkObjectArrayList) {
            arrayList.add(new FoodAndDrinkObject(
                    object.getId(), object.getName(),
                    object.getUnit(), object.getTypeId(),
                    object.getType(), object.getCalorie(),
                    object.islamic()));
        }
        return arrayList;
    }

    private void setSpinner() {
        //load food from json file
        ArrayList<CategoryObject> categoryArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "category.json"), new TypeToken<ArrayList<CategoryObject>>() {
        }.getType());

        ArrayList<CategoryObject> foodTypeAllArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "food_type.json"), new TypeToken<ArrayList<CategoryObject>>() {
        }.getType());

        ArrayList<CategoryObject> drinkTypeAllArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "drink_type.json"), new TypeToken<ArrayList<CategoryObject>>() {
        }.getType());

        UserInfoObject userInfoObject = userInfoManager.queryAll().get(0);

        final ArrayList<CategoryObject> foodTypeList = new ArrayList<>();
        for (CategoryObject object : foodTypeAllArrayList) {
            if (object.getId() == 1) {
                if (userInfoObject.isChicken()) {
                    foodTypeList.add(new CategoryObject(object.getId(), object.getName()));
                }
            } else if (object.getId() == 2) {
                if (userInfoObject.isShrimp()) {
                    foodTypeList.add(new CategoryObject(object.getId(), object.getName()));
                }
            } else if (object.getId() == 3) {
                if (userInfoObject.isPork()) {
                    if (userInfoObject.getReligionId() == 1) {
                        foodTypeList.add(new CategoryObject(object.getId(), object.getName()));
                    }
                }
            } else if (object.getId() == 4) {
                if (userInfoObject.isCrab()) {
                    foodTypeList.add(new CategoryObject(object.getId(), object.getName()));
                }
            } else {
                foodTypeList.add(new CategoryObject(object.getId(), object.getName()));
            }
        }

        final ArrayList<CategoryObject> drinkTypeList = new ArrayList<>();
        for (CategoryObject object : drinkTypeAllArrayList) {
            if (object.getId() == 7) {
                if (userInfoObject.isMilk()) {
                    drinkTypeList.add(new CategoryObject(object.getId(), object.getName()));
                }
            } else {
                drinkTypeList.add(new CategoryObject(object.getId(), object.getName()));
            }
        }

        SpinnerCustom.setSpinner(activity, spnCategory, categoryArrayList,
                activity.getResources().getString(R.string.food_and_drink_category));

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    switch (position) {
                        case 1:
                            SpinnerCustom.setSpinner(activity, spnType, foodTypeList,
                                    activity.getResources().getString(R.string.food_and_drink_type));
                            setSpinnerTypeListener(CATEGORY_FOOD, foodTypeList);
                            break;
                        case 2:
                            SpinnerCustom.setSpinner(activity, spnType, drinkTypeList,
                                    activity.getResources().getString(R.string.food_and_drink_type));
                            setSpinnerTypeListener(CATEGORY_DRINK, drinkTypeList);
                            break;

                        default:
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void setSpinnerTypeListener(final int category, final ArrayList<CategoryObject> categoryArrayList) {

        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    ArrayList<FoodAndDrinkObject> arrayList = new ArrayList<FoodAndDrinkObject>();
                    if (category == CATEGORY_FOOD) {
                        if (categoryArrayList.get(position - 1).getId() == 0) {
                            arrayList = addAllItemFood(foodManager.queryAll());
                        } else {
                            arrayList = addAllItemFood(foodManager.query(categoryArrayList.get(position - 1).getId()));
                        }

                    } else if (category == CATEGORY_DRINK) {
                        if (categoryArrayList.get(position - 1).getId() == 0) {
                            arrayList = addAllItemDrink(drinkManager.queryAll());
                        } else {
                            arrayList = addAllItemDrink(drinkManager.query(categoryArrayList.get(position - 1).getId()));
                        }
                    }

                    adapter.setFoodAndDrinkArrayList(arrayList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
