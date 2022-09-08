package com.jackingaming.passingthrough.views;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.jackingaming.passingthrough.models.database.entities.GameEntity;

public class GameEntityListAdapter extends ListAdapter<GameEntity, GameEntityViewHolder> {
    public GameEntityListAdapter(@NonNull DiffUtil.ItemCallback<GameEntity> diffCallback) {
        super(diffCallback);
    }

    @Override
    public GameEntityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return GameEntityViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(GameEntityViewHolder holder, int position) {
        GameEntity current = getItem(position);
        holder.bind(current.getName());
    }

    public static class GameEntityDiff extends DiffUtil.ItemCallback<GameEntity> {

        @Override
        public boolean areItemsTheSame(@NonNull GameEntity oldItem, @NonNull GameEntity newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull GameEntity oldItem, @NonNull GameEntity newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}