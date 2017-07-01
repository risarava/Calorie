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

import com.icm.calapp.R;
import com.icm.calapp.adapter.FoodAndDrinkAllAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.SpinnerCustom;
import com.icm.calapp.database.DrinkManager;
import com.icm.calapp.database.FoodAndDrinkManager;
import com.icm.calapp.database.FoodManager;
import com.icm.calapp.model.DrinkObject;
import com.icm.calapp.model.FoodAndDrinkObject;
import com.icm.calapp.model.FoodObject;

import java.util.ArrayList;

public class FoodDrinkAllActivity extends AbstractAppCompatActivity {
    private static final int TYPE_CHICKEN = 1;
    private static final int TYPE_SHRIMP = 2;
    private static final int TYPE_PORK = 3;
    private static final int TYPE_CRAB = 4;
    private static final int TYPE_WATER = 5;
    private static final int TYPE_YOGURT = 6;
    private static final int TYPE_MILK = 7;

    private static final int CATEGORY_FOOD = 1;
    private static final int CATEGORY_DRINK = 2;


    private RecyclerView recyclerView;
    private Spinner spnCategory;
    private Spinner spnType;

    private LinearLayoutManager linearLayoutManager;
    private FoodAndDrinkAllAdapter adapter;
    private FoodManager foodManager;
    private DrinkManager drinkManager;
    private FoodAndDrinkManager foodAndDrinkManager;

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

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
        initRecycleView();

        setSpinner();
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
                foodAndDrinkManager.addFoodOrDrink(new FoodAndDrinkObject(
                        object.getId(), object.getName(),
                        object.getUnit(), object.getTypeId(),
                        object.getType(), object.getCalorie(),
                        object.islamic()));
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
        SpinnerCustom.setSpinner(activity, spnCategory, R.array.cateyory_list);

        spnCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    switch (position) {
                        case 1:
                            SpinnerCustom.setSpinner(activity, spnType, R.array.type_of_food_list);
                            setSpinnerTypeListener(CATEGORY_FOOD);
                            break;
                        case 2:
                            SpinnerCustom.setSpinner(activity, spnType, R.array.type_of_drink_list);
                            setSpinnerTypeListener(CATEGORY_DRINK);
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

    private void setSpinnerTypeListener(final int category) {

        spnType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    ArrayList<FoodAndDrinkObject> arrayList = new ArrayList<FoodAndDrinkObject>();
                    if (category == CATEGORY_FOOD) {
                        switch (position) {
                            case 1:
                                arrayList = addAllItemFood(foodManager.queryAll());
                                break;
                            case 2:
                                arrayList = addAllItemFood(foodManager.query(TYPE_CHICKEN));
                                break;
                            case 3:
                                arrayList = addAllItemFood(foodManager.query(TYPE_SHRIMP));
                                break;
                            case 4:
                                arrayList = addAllItemFood(foodManager.query(TYPE_PORK));
                                break;
                            case 5:
                                arrayList = addAllItemFood(foodManager.query(TYPE_CRAB));
                                break;
                            default:
                                arrayList = addAllItemFood(foodManager.queryAll());
                                break;

                        }
                    } else if (category == CATEGORY_DRINK) {
                        switch (position) {
                            case 1:
                                arrayList = addAllItemDrink(drinkManager.queryAll());
                                break;
                            case 2:
                                arrayList = addAllItemDrink(drinkManager.query(TYPE_WATER));
                                break;
                            case 3:
                                arrayList = addAllItemDrink(drinkManager.query(TYPE_YOGURT));
                                break;
                            case 4:
                                arrayList = addAllItemDrink(drinkManager.query(TYPE_MILK));
                                break;
                            default:
                                arrayList = addAllItemFood(foodManager.queryAll());
                                break;

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
