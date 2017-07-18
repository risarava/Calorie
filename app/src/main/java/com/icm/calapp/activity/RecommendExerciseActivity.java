package com.icm.calapp.activity;

import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.adapter.RecommendExerciseAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.MyAlertDialog;
import com.icm.calapp.custom.SpannableText;
import com.icm.calapp.database.ExerciseManager;

import static com.icm.calapp.MainActivity.EXTRA_CALORIE;

public class RecommendExerciseActivity extends AbstractAppCompatActivity {

    private int calorie = 0;

    private RecyclerView recyclerView;
    private TextView txtSelected;
    private TextView txtNeedUsed;

    private LinearLayoutManager linearLayoutManager;
    private RecommendExerciseAdapter adapter;
    private ExerciseManager exerciseManager;

    @Override
    protected int setContentView() {
        return R.layout.activity_recommend_exercise;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_recommend_exercise);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        txtSelected = (TextView) findViewById(R.id.textviewSelected);
        txtNeedUsed = (TextView) findViewById(R.id.textviewUsed);

        exerciseManager = new ExerciseManager();

        calorie = getIntent().getIntExtra(EXTRA_CALORIE, 0);

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        initRecycleView();

        adapter.setExerciseArrayList(exerciseManager.queryAll());

        update();
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecommendExerciseAdapter(activity, calorie);
        recyclerView.setAdapter(adapter);

    }

    private void update() {
        if (calorie < 0) {
            calorie = calorie * (-1);
        }
        txtSelected.setText(SpannableText.getSpan(activity, getString(R.string.exercise_selected, adapter.getItemCount()),
                String.valueOf(adapter.getItemCount())));
        txtNeedUsed.setText(SpannableText.getSpan(activity, getString(R.string.exercise_need_used, calorie),
                String.valueOf(calorie)));
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
}
