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

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ViewHolder> {

    private ArrayList<ExerciseObject> exerciseArrayList = new ArrayList<>();

    private Activity activity;
    private OnItemClickListener onItemClickListener;

    public ExerciseAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_food_and_drink, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        ExerciseObject exerciseObject = exerciseArrayList.get(position);

        holder.txtName.setText(exerciseObject.getName());
        holder.txtCaloriePerUnit.setText(activity.getString(R.string.exercise_calorie_per_hour,
                exerciseObject.getCaloriePerHour()));
//        holder.imgIcon.setImageResource(CategotyIcon.getIcon(exerciseObject.getTypeId()));

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

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener == null) {
                    return;
                }
                onItemClickListener.onItemClick(view, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName;
        private TextView txtCaloriePerUnit;
        private ImageView imgIcon;
        private ImageView imgDelete;


        public ViewHolder(View v) {
            super(v);
            txtName = (TextView) v.findViewById(R.id.textviewName);
            txtCaloriePerUnit = (TextView) v.findViewById(R.id.textviewCaloriePerUnit);
            imgIcon = (ImageView) v.findViewById(R.id.imageviewIcon);
            imgDelete = (ImageView) v.findViewById(R.id.imageviewDelete);

        }
    }

    public void setExerciseArrayList(ArrayList<ExerciseObject> exerciseArrayList) {
        this.exerciseArrayList = exerciseArrayList;
        notifyDataSetChanged();
    }

    public ArrayList<ExerciseObject> getExerciseArrayList() {
        return this.exerciseArrayList;
    }

    public void removeAt(int position) {
        this.exerciseArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.exerciseArrayList.size());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener OnItemClickListener) {
        this.onItemClickListener = OnItemClickListener;
    }
}
