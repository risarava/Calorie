package com.icm.calapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.activity.CalculatorResultActivity;
import com.icm.calapp.activity.FoodDrinkListActivity;
import com.icm.calapp.custom.AbstractAppCompatActivity;

public class MainActivity extends AbstractAppCompatActivity {

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
                Intent intent = new Intent(activity, FoodDrinkListActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_food_and_drink_list);

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
    }

    public void okButton(View view) {
        Intent intent = new Intent(activity, CalculatorResultActivity.class);
        startActivity(intent);
    }
}
