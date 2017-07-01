package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractActivity;

public class SelectGenderActivity extends AbstractActivity {

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
        startActivity(intent);
    }

    public void womenButton(View view) {
        intent = new Intent(activity, UserInfoActivity.class);
        startActivity(intent);
    }
}
