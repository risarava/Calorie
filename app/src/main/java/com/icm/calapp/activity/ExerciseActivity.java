package com.icm.calapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.adapter.ExerciseAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.MyAlertDialog;
import com.icm.calapp.database.ExerciseManager;

import static com.icm.calapp.MainActivity.EXTRA_CALORIE;

public class ExerciseActivity extends AbstractAppCompatActivity {
    private int calorie = 0;

    private TextView txtSelected;
    private TextView txtNeedUsed;
    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private ExerciseAdapter adapter;
    private ExerciseManager exerciseManager;
    private MyAlertDialog myAlertDialog;

    @Override
    protected int setContentView() {
        return R.layout.activity_exercise;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {
        menuRight.setImageResource(R.drawable.img_plus2x);
        menuRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, ExerciseAllActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_exercise);

        txtSelected = (TextView) findViewById(R.id.textviewSelected);
        txtNeedUsed = (TextView)findViewById(R.id.textviewUsed);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        exerciseManager = new ExerciseManager();

        calorie = (int) getIntent().getDoubleExtra(EXTRA_CALORIE, 0);

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        initRecycleView();
    }

    private void update() {
        txtSelected.setText(getString(R.string.exercise_selected, adapter.getItemCount()));
        txtNeedUsed.setText(getString(R.string.exercise_need_used, calorie));
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ExerciseAdapter(activity);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new ExerciseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, final int position) {
                myAlertDialog = new MyAlertDialog(activity);
                myAlertDialog.alertDialog(activity.getResources().getString(R.string.dialog_delete),
                        new MyAlertDialog.OnClickListener() {
                            @Override
                            public void onClickOk() {
                                exerciseManager.delete(adapter.getExerciseArrayList().get(position).getName());
                                adapter.removeAt(position);
                                update();
                            }
                        });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter == null) {
            return;
        }
        adapter.setExerciseArrayList(exerciseManager.queryAll());
        update();
    }
}
