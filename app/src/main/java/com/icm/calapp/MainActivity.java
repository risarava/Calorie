package com.icm.calapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.activity.CalculatorResultActivity;
import com.icm.calapp.activity.FoodDrinkAllActivity;
import com.icm.calapp.adapter.FoodAndDrinkAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.MyAlertDialog;
import com.icm.calapp.database.FoodAndDrinkManager;

public class MainActivity extends AbstractAppCompatActivity {

    private RecyclerView recyclerView;
    private TextView txtNotFound;
    private TextView txtCalorie;

    private LinearLayoutManager linearLayoutManager;
    private FoodAndDrinkAdapter adapter;
    private FoodAndDrinkManager foodAndDrinkManager;
    private MyAlertDialog myAlertDialog;

    @Override
    protected int setContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar,
                                 TextView txtTitleToolbar) {
        menuRight.setImageResource(R.drawable.img_plus2x);
        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, FoodDrinkAllActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_food_and_drink_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        txtNotFound = (TextView) findViewById(R.id.textviewNotFound);
        txtCalorie = (TextView) findViewById(R.id.textviewCalorie);

        foodAndDrinkManager = new FoodAndDrinkManager();

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
        initRecycleView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            return;
        }
        adapter.setFoodAndDrinkArrayList(foodAndDrinkManager.queryAll());
        update();
    }

    private void update() {
        txtNotFound.setVisibility((foodAndDrinkManager.queryAll().size() == 0) ? View.VISIBLE : View.GONE);
        txtCalorie.setText(String.valueOf(adapter.getCalorie()));
    }

    public void okButton(View view) {
        Intent intent = new Intent(activity, CalculatorResultActivity.class);
        startActivity(intent);
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new FoodAndDrinkAdapter(activity);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new FoodAndDrinkAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                myAlertDialog = new MyAlertDialog(activity);
                myAlertDialog.alertDialog(activity.getResources().getString(R.string.dialog_delete),
                        new MyAlertDialog.OnClickListener() {
                            @Override
                            public void onClickOk() {
                                foodAndDrinkManager.delete(adapter.getFoodAndDrinkArrayList().get(position).getName());
                                adapter.removeAt(position);
                                update();
                            }
                        });
            }
        });
    }
}
