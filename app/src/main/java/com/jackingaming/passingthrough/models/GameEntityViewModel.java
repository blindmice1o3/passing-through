package com.jackingaming.passingthrough.models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.jackingaming.passingthrough.models.database.entities.GameEntity;

import java.util.List;

public class GameEntityViewModel extends AndroidViewModel {
    private GameEntityRepository repository;
    private final LiveData<List<GameEntity>> gameEntities;

    public GameEntityViewModel(Application application) {
        super(application);
        repository = new GameEntityRepository(application);
        gameEntities = repository.getGameEntities();
    }

    public LiveData<List<GameEntity>> getGameEntities() {
        return gameEntities;
    }

    public void insert(GameEntity gameEntity) {
        repository.insert(gameEntity);
    }
}