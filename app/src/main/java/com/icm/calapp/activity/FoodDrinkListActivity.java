package com.icm.calapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractAppCompatActivity;

public class FoodDrinkListActivity extends AbstractAppCompatActivity {
    public static final String EXTRA_TYPE = "type";

    private int type = 0;

    @Override
    protected int setContentView() {
        return R.layout.activity_food_drink_list;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        type = getIntent().getIntExtra(EXTRA_TYPE, 0);

        setTitle((type == SelectCategoryActivity.CATEGORY_FOOD) ?
                R.string.title_toolbar_food : R.string.title_toolbar_drink);
    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
    }
}
