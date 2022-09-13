package com.jackingaming.passingthrough.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.passingthrough.R;

public class GameEntityViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvName;
    private final TextView tvX;
    private final TextView tvY;

    private GameEntityViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
        tvX = itemView.findViewById(R.id.tv_x);
        tvY = itemView.findViewById(R.id.tv_y);
    }

    public void bind(String name, float x, float y) {
        tvName.setText(name);
        tvX.setText(Float.toString(x));
        tvY.setText(Float.toString(y));
    }

    static GameEntityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new GameEntityViewHolder(view);
    }
}