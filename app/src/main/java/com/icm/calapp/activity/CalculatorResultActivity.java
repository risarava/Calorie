package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.Spannable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.Calculator;
import com.icm.calapp.custom.MyAlertDialog;
import com.icm.calapp.custom.SpannableText;
import com.icm.calapp.database.UserInfoManager;
import com.icm.calapp.model.UserInfoObject;

import static com.icm.calapp.MainActivity.EXTRA_CALORIE;

public class CalculatorResultActivity extends AbstractAppCompatActivity implements View.OnClickListener {

    private int calorie = 0;
    private double bmr;
    private double tdee;
    private UserInfoObject userInfoObject;

    private TextView txtAge;
    private TextView txtWeight;
    private TextView txtHeight;
    private TextView txtReligion;
    private TextView txtBMR;
    private TextView txtTDEE;
    private TextView txtCalorie;
    private TextView txtResult;
    private TextView txtRecommend;
    private ImageView imgIcon;

    private UserInfoManager userInfoManager;

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
        txtAge = (TextView) findViewById(R.id.textviewAge);
        txtWeight = (TextView) findViewById(R.id.textviewWeight);
        txtHeight = (TextView) findViewById(R.id.textviewHeight);
        txtReligion = (TextView) findViewById(R.id.textviewReligion);
        txtBMR = (TextView) findViewById(R.id.textviewBMR);
        txtTDEE = (TextView) findViewById(R.id.textviewTDEE);
        txtCalorie = (TextView) findViewById(R.id.textviewCalorie);
        txtResult = (TextView) findViewById(R.id.textveiwResult);
        txtRecommend = (TextView) findViewById(R.id.textviewRecommend);
        imgIcon = (ImageView) findViewById(R.id.imageviewIconGender);

        calorie = getIntent().getIntExtra(EXTRA_CALORIE, 0);

        userInfoManager = new UserInfoManager();

        userInfoObject = userInfoManager.queryAll().get(0);

        txtRecommend.setOnClickListener(this);

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();
        calculate();

        updateProfile();
    }

    private void calculate() {
        txtCalorie.setText(SpannableText.getSpan(activity, getString(R.string.calculator_result_calorie, calorie),
                String.valueOf(calorie)));

        bmr = Calculator.calculateBMR(userInfoObject.getGender(), userInfoObject.getWeight(),
                userInfoObject.getHeight(), userInfoObject.getAge());
        txtBMR.setText(SpannableText.getSpan(activity, getString(R.string.calculator_result_bmr, bmr),
                String.format("%,.2f", bmr)));

        tdee = Calculator.calculateTDEE(bmr, userInfoObject.getExercise());

        txtTDEE.setText(SpannableText.getSpan(activity, getString(R.string.calculator_result_tdee, tdee),
                String.format("%,.2f", tdee)));

        double part = (tdee - calorie);
        String recommend = "";
        if (part < -100) {
            recommend = getResources().getString(R.string.button_recommend_exercise);
//            txtResult.setText(SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
//                    getResources().getString(R.string.calculator_result_fat)),
//                    getResources().getString(R.string.calculator_result_fat)));
        } else if (part > 100) {
            recommend = getResources().getString(R.string.button_recommend_food);
//            txtResult.setText(SpannableText.getSpan(activity, getString(R.string.calculator_result_result,
//                    getResources().getString(R.string.calculator_result_thin)),
//                    getResources().getString(R.string.calculator_result_thin)));
        } else {
            txtRecommend.setVisibility(View.GONE);
//            txtResult.setText(SpannableText.getSpan(activity, getString(R.string.calculator_result_result,
//                    getResources().getString(R.string.calculator_result_slim)),
//                    getResources().getString(R.string.calculator_result_slim)));
        }

        txtResult.setText(getBMIText(Calculator.calculateBMI(userInfoObject.getWeight(), userInfoObject.getHeight())));
        txtRecommend.setText(recommend);
    }

    private void updateProfile() {
        if (userInfoObject == null) {
            return;
        }
        txtWeight.setText(getString(R.string.calculator_result_weight, userInfoObject.getWeight()));
        txtHeight.setText(getString(R.string.calculator_result_height, (userInfoObject.getHeight())));
        txtAge.setText(getString(R.string.calculator_result_age, (userInfoObject.getAge())));
        txtReligion.setText(getString(R.string.calculator_result_religion, (userInfoObject.getReligionName())));
        imgIcon.setImageResource((userInfoObject.getGender() == 1) ? R.drawable.img_male :
                R.drawable.img_female);

    }

    public Spannable getBMIText(double bmi) {
        Spannable spannable;
        if (bmi < 18.5) {
            //ผอมเกินไป
            spannable = SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
                    "ผอมเกินไป"),
                    "ผอมเกินไป");
        } else if(bmi >= 18.6 && bmi <= 22.9) {
            //น้ำหนักปกติ เหมาะสม
            spannable = SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
                    "น้ำหนักปกติ เหมาะสม"),
                    "น้ำหนักปกติ เหมาะสม");
        } else if (bmi >= 23 && bmi <= 24.9) {
            //น้ำหนักเกิน
            spannable = SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
                    "น้ำหนักเกิน"),
                    "น้ำหนักเกิน");
        } else if (bmi >= 25 && bmi<= 29.9) {
            //อ้วน
            spannable = SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
                    "อ้วน"),
                    "อ้วน");
        } else {
            //อ้วนมาก
            spannable = SpannableText.getSpan(activity,  getString(R.string.calculator_result_result,
                    "อ้วนมาก"),
                    "อ้วนมาก");
        }
        return spannable;
    }

    public void endButton(View view) {
        endApp();
    }

    public void endApp() {
        MyAlertDialog myAlertDialog = new MyAlertDialog(activity);
        myAlertDialog.alertDialog(activity.getResources().getString(R.string.dialog_end),
                new MyAlertDialog.OnClickListener() {
                    @Override
                    public void onClickOk() {
                        ActivityCompat.finishAffinity(activity);
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.textviewRecommend:
                double part = (tdee - calorie);
                if (part < -100) {
                    Intent intent = new Intent(activity, ExerciseActivity.class);
                    intent.putExtra(EXTRA_CALORIE, part);
                    startActivity(intent);
                } else if (part > 100) {
                    Intent intent = new Intent(activity, RecommendFoodActivity.class);
                    intent.putExtra(EXTRA_CALORIE, part);
                    startActivity(intent);
                }

                break;
            default:
                break;
        }
    }
}
