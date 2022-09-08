package com.jackingaming.passingthrough.models;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.jackingaming.passingthrough.models.database.GameEntityRoomDatabase;
import com.jackingaming.passingthrough.models.database.daos.GameEntityDao;
import com.jackingaming.passingthrough.models.database.entities.GameEntity;

import java.util.List;

public class GameEntityRepository {
    private GameEntityDao gameEntityDao;
    private LiveData<List<GameEntity>> gameEntities;

    // Note that in order to unit test the GameEntityRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    GameEntityRepository(Application application) {
        GameEntityRoomDatabase db = GameEntityRoomDatabase.getDatabase(application);
        gameEntityDao = db.gameEntityDao();
        gameEntities = gameEntityDao.getAlphabetizedGameEntities();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<GameEntity>> getGameEntities() {
        return gameEntities;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    void insert(GameEntity gameEntity) {
        GameEntityRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            gameEntityDao.insert(gameEntity);
        });
    }
}