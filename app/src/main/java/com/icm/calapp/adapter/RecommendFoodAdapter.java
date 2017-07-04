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
import com.icm.calapp.custom.CategotyIcon;
import com.icm.calapp.model.RecommendFoodObject;

import java.util.ArrayList;

public class RecommendFoodAdapter extends RecyclerView.Adapter<RecommendFoodAdapter.ViewHolder> {

    private ArrayList<RecommendFoodObject> recommendFoodArrayList = new ArrayList<>();

    private Activity activity;
    private OnItemClickListener onItemClickListener;

    public RecommendFoodAdapter(Activity activity) {
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
        RecommendFoodObject foodAndDrinkObject = recommendFoodArrayList.get(position);
        holder.txtName.setText(foodAndDrinkObject.getName());
        holder.txtCaloriePerUnit.setText(activity.getString(R.string.food_and_drink_calorie_per_unit,
                foodAndDrinkObject.getCalorie(),
                foodAndDrinkObject.getUnit()));
        holder.imgIcon.setImageResource(CategotyIcon.getIcon(foodAndDrinkObject.getTypeId()));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        if (position == recommendFoodArrayList.size() - 1) {
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
        return recommendFoodArrayList.size();
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

    public void setRecommendFoodArrayList(ArrayList<RecommendFoodObject> recommendFoodArrayList) {
        this.recommendFoodArrayList = recommendFoodArrayList;
        notifyDataSetChanged();
    }

    public ArrayList<RecommendFoodObject> getRecommendFoodArrayList() {
        return this.recommendFoodArrayList;
    }

    public int getCalorie() {
        int calorie = 0;
        for (RecommendFoodObject object : this.recommendFoodArrayList) {
            calorie += object.getCalorie();
        }
        return calorie;
    }

    public void removeAt(int position) {
        this.recommendFoodArrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, this.recommendFoodArrayList.size());
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnClickListener(OnItemClickListener OnItemClickListener) {
        this.onItemClickListener = OnItemClickListener;
    }
}
