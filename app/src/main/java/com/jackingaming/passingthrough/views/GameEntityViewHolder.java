package com.jackingaming.passingthrough.views;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.passingthrough.R;

public class GameEntityViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvName;

    private final Button buttonXMinus;
    private final TextView tvX;
    private final Button buttonXPlus;

    private final Button buttonYMinus;
    private final TextView tvY;
    private final Button buttonYPlus;

    private GameEntityViewHolder(View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);

        buttonXMinus = itemView.findViewById(R.id.button_x_minus);
        tvX = itemView.findViewById(R.id.tv_x);
        buttonXPlus = itemView.findViewById(R.id.button_x_plus);

        buttonYMinus = itemView.findViewById(R.id.button_y_minus);
        tvY = itemView.findViewById(R.id.tv_y);
        buttonYPlus = itemView.findViewById(R.id.button_y_plus);
    }

    public void bind(String name, float x, float y) {
        tvName.setText(name);

        buttonXMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GameEntityViewHolder", "buttonXMinus clicked");
            }
        });
        tvX.setText(Float.toString(x));
        buttonXPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GameEntityViewHolder", "buttonXPlus clicked");
            }
        });

        buttonYMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GameEntityViewHolder", "buttonYMinus clicked");
            }
        });
        tvY.setText(Float.toString(y));
        buttonYPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("GameEntityViewHolder", "buttonYPlus clicked");
            }
        });
    }

    static GameEntityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new GameEntityViewHolder(view);
    }
}