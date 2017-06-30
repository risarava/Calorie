package com.icm.calapp.custom;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;

public abstract class AbstractAppCompatActivity extends AppCompatActivity {

    protected Activity activity;
    protected ProgressDialog progressDialog;
    private LinearLayout mToolbar;
    private ImageView imgIconLeft;
    private ImageView imgIconRight;
    private TextView txtTitleToolbar;

    protected abstract int setContentView();

    protected abstract void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar,
                                          TextView txtTitleToolbar);

    protected abstract void bindUI(Bundle savedInstanceState);

    protected abstract void setupUI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentView());
        activity = this;
        setProgressDialog();
        initActionbar();
        bindUI(savedInstanceState);
        setupUI();
    }

    private void setProgressDialog() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Now Loading...");
        progressDialog.setCancelable(false);
    }

    private void initActionbar() {
        mToolbar = (LinearLayout) findViewById(R.id.mToolBar);
        txtTitleToolbar = (TextView) findViewById(R.id.textviewTitleToolbar);
        imgIconLeft = (ImageView) findViewById(R.id.imageviewIconLeft);
        imgIconRight = (ImageView) findViewById(R.id.imageviewIconRight);
        bindActionbar(imgIconLeft, imgIconRight, mToolbar, txtTitleToolbar);
    }

    private View.OnClickListener onMenuClicked = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onBackPressed();
        }
    };

    public void onBackPressedButtonLeft() {
        imgIconLeft.setVisibility(View.VISIBLE);
        changeStatusBarColor(R.color.color_yellow_dark);
        imgIconLeft.setOnClickListener(onMenuClicked);
    }

    //change color of toolbar and status bar
    public void changeStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(color));
        }
        mToolbar.setBackgroundColor(getResources().getColor(color));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void setTitle(int title) {
        txtTitleToolbar.setText(getResources().getString(title));
    }
}