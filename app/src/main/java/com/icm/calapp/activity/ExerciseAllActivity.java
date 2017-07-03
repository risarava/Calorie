package com.icm.calapp.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.icm.calapp.R;
import com.icm.calapp.adapter.ExerciseAllAdapter;
import com.icm.calapp.custom.AbstractAppCompatActivity;
import com.icm.calapp.custom.ConvertJSON;
import com.icm.calapp.database.ExerciseManager;
import com.icm.calapp.model.ExerciseObject;

import java.util.ArrayList;

public class ExerciseAllActivity extends AbstractAppCompatActivity {

    private RecyclerView recyclerView;

    private LinearLayoutManager linearLayoutManager;
    private ExerciseAllAdapter adapter;
    private ExerciseManager exerciseManager;

    @Override
    protected int setContentView() {
        return R.layout.activity_exercise_all;
    }

    @Override
    protected void bindActionbar(ImageView menuLeft, ImageView menuRight, LinearLayout toolbar, TextView txtTitleToolbar) {

    }

    @Override
    protected void bindUI(Bundle savedInstanceState) {
        setTitle(R.string.title_toolbar_exercise_all);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        exerciseManager = new ExerciseManager();

    }

    @Override
    protected void setupUI() {
        onBackPressedButtonLeft();

        initRecycleView();

        //load food from json file
        ArrayList<ExerciseObject> exerciseArrayList = new Gson().fromJson(ConvertJSON.loadJSONFromAsset(getApplicationContext()
                , "exercise.json"), new TypeToken<ArrayList<ExerciseObject>>() {
        }.getType());

        adapter.setExerciseArrayList(exerciseArrayList);
    }

    private void initRecycleView() {
        linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
//
        adapter = new ExerciseAllAdapter(activity);
        recyclerView.setAdapter(adapter);

        adapter.setOnClickListener(new ExerciseAllAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ExerciseObject object = adapter.getExerciseArrayList().get(position);
                exerciseManager.addExercise(new ExerciseObject(
                        object.getId(), object.getName(),
                        object.getLevelId(), object.getLevelName(),
                        object.getCaloriePerHour(), object.getCaloriePerMin()));
                onBackPressed();
            }
        });
    }

}
