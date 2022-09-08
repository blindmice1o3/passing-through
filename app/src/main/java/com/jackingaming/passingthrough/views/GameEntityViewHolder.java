package com.jackingaming.passingthrough.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jackingaming.passingthrough.R;

public class GameEntityViewHolder extends RecyclerView.ViewHolder {
    private final TextView gameEntityItemView;

    private GameEntityViewHolder(View itemView) {
        super(itemView);
        gameEntityItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text) {
        gameEntityItemView.setText(text);
    }

    static GameEntityViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new GameEntityViewHolder(view);
    }
}