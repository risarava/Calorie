package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.adapter.ExerciseAdapter;
import com.icm.calapp.adapter.RecommendFoodAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.MyAlertDialog;
import com.icm.calapp.database.ExerciseManager;
import com.icm.calapp.database.RecommendFoodManager;

import static com.icm.calapp.MainActivity.EXTRA_CALORIE;
import static com.icm.calapp.activity.FoodDrinkAllActivity.EXTRA_IS_RECOMMEND;

public class RecommendFoodActivity extends AbstractAppCompatActivity {
    private int calorie = 0;

    private TextView txtSelected;
    private TextView txtNeed;
    private TextView txtStatus;
    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private RecommendFoodAdapter adapter;
    private RecommendFoodManager recommendFoodManager;
    private MyAlertDialog myAlertDialog;

    @Override
    protected int setContentView() {
        return R.layout.activity_recommend_food;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {
        menuRight.setImageResource(R.drawable.img_plus2x);
        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FoodDrinkAllActivity.class);
                intent.putExtra(EXTRA_IS_RECOMMEND, true);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_exercise);

        txtSelected = (TextView) findViewById(R.id.textviewSelected);
        txtNeed = (TextView) findViewById(R.id.textviewNeed);
        txtStatus = (TextView) findViewById(R.id.textviewStatus);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        recommendFoodManager = new RecommendFoodManager();

        calorie = (int) getIntent().getDoubleExtra(EXTRA_CALORIE, 0);

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        initRecycleView();
    }

    private void update() {
        if (calorie < 0) {
            //convert to positive
            calorie = calorie * (-1);
        }
        txtSelected.setText(getString(R.string.recommend_food_need, calorie));
        txtNeed.setText(getString(R.string.recommend_food_selected, adapter.getCalorie()));
        String status = "";
        if (calorie > adapter.getCalorie()) {
            status = getResources().getString(R.string.recommend_food_status_more);
        } else if (calorie == adapter.getCalorie()) {
            status = getResources().getString(R.string.recommend_food_status_good);
        } else if (calorie < adapter.getCalorie()) {
            status = getResources().getString(R.string.recommend_food_status_max);
        }
        txtStatus.setText(getString(R.string.recommend_food_status, status));
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecommendFoodAdapter(activity);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new RecommendFoodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                myAlertDialog = new MyAlertDialog(activity);
                myAlertDialog.alertDialog(activity.getResources().getString(R.string.dialog_delete),
                        new MyAlertDialog.OnClickListener() {
                            @Override
                            public void onClickOk() {
                                recommendFoodManager.delete(adapter.getRecommendFoodArrayList().get(position).getName());
                                adapter.removeAt(position);
                                update();
                            }
                        });
            }
        });
    }

    public void endButton(View view) {
        endApp();
    }

    public void endApp() {
        myAlertDialog = new MyAlertDialog(activity);
        myAlertDialog.alertDialog(activity.getResources().getString(R.string.dialog_end),
                new MyAlertDialog.OnClickListener() {
                    @Override
                    public void onClickOk() {
                        ActivityCompat.finishAffinity(activity);
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            return;
        }
        adapter.setRecommendFoodArrayList(recommendFoodManager.queryAll());
        update();
    }
}
