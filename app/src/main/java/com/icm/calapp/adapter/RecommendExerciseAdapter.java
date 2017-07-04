package com.icm.calapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.icm.calapp.R;
import com.icm.calapp.model.ExerciseObject;

import java.util.ArrayList;

public class RecommendExerciseAdapter extends RecyclerView.Adapter<RecommendExerciseAdapter.ViewHolder> {

    private ArrayList<ExerciseObject> exerciseArrayList = new ArrayList<>();
    private int calorie =0 ;

    private Activity activity;
    private OnItemClickListener onItemClickListener;

    public RecommendExerciseAdapter(Activity activity, int calorie) {
        this.calorie = calorie;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recommend_exercise, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ExerciseObject exerciseObject = exerciseArrayList.get(position);

        holder.txtName.setText(exerciseObject.getName());
        holder.txtCaloriePerHour.setText(activity.getString(R.string.exercise_calorie_per_hour,
                exerciseObject.getCaloriePerHour()));

        int minute = (calorie / exerciseArrayList.size()) / exerciseObject.getCaloriePerMin();
        holder.txtRecommend.setText(activity.getString(R.string.exercise_recommend, minute));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        if (position == exerciseArrayList.size() - 1) {
            params.setMargins(0, 15, 0, 15);
        } else {
            params.setMargins(0, 15, 0, 0);
        }
        holder.itemView.setLayoutParams(params);

    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtCaloriePerHour;
        private TextView txtRecommend;
        private ImageView imgIcon;

        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.textviewName);
            txtCaloriePerHour = (TextView) v.findViewById(R.id.textviewCaloriePerHour);
            txtRecommend = (TextView) v.findViewById(R.id.textviewRecommend);
            imgIcon = (ImageView) v.findViewById(R.id.imageviewIcon);
        }
    }

    public void setExerciseArrayList(ArrayList<ExerciseObject> exerciseArrayList) {
        this.exerciseArrayList = exerciseArrayList;
        notifyDataSetChanged();
    }

    public ArrayList<ExerciseObject> getExerciseArrayList() {
        return this.exerciseArrayList;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener OnItemClickListener) {
        this.onItemClickListener = OnItemClickListener;
    }
}
