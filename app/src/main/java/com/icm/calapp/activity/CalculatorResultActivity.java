package com.icm.calapp.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractAppCompatActivity;

public class CalculatorResultActivity extends AbstractAppCompatActivity {
    @Override
    protected int setContentView() {
        return R.layout.activity_calculatetor_result;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_calculation_result);
    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
    }
}
