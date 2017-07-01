package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractActivity;

public class SelectGenderActivity extends AbstractActivity {
    public static final String EXTRA_GENDER = "GENDER";
    public static final int GENDER_MEN = 1;
    public static final int GENDER_WOMEN = 0;

    private Intent intent;

    @Override
    protected int setContentView() {
        return R.layout.activity_select_gender;
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {

    }

    @Override
    protected void setupUI() {

    }

    public void menButton(View view) {
        intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(EXTRA_GENDER, GENDER_MEN);
        startActivity(intent);
    }

    public void womenButton(View view) {
        intent = new Intent(activity, UserInfoActivity.class);
        intent.putExtra(EXTRA_GENDER, GENDER_WOMEN);
        startActivity(intent);
    }
}
