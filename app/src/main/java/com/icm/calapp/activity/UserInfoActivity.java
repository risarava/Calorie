package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.MainActivity;
import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractAppCompatActivity;

public class UserInfoActivity extends AbstractAppCompatActivity {

    private Intent intent;

    @Override
    protected int setContentView() {
        return R.layout.activity_user_info;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_user_info);
    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

    }

    public void okButton(View view) {
        intent = new Intent(activity, MainActivity.class);
        startActivity(intent);
    }
}
