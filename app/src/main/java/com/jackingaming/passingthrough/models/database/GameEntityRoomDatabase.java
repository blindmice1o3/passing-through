package com.jackingaming.passingthrough.models.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.jackingaming.passingthrough.models.database.daos.GameEntityDao;
import com.jackingaming.passingthrough.models.database.entities.GameEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        entities = {GameEntity.class},
        version = 2,
        exportSchema = true,
        autoMigrations = {
                @AutoMigration(from = 1, to = 2)
        }
)
public abstract class GameEntityRoomDatabase extends RoomDatabase {
    public abstract GameEntityDao gameEntityDao();

    private static volatile GameEntityRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more game entities, just add them.
                GameEntityDao dao = INSTANCE.gameEntityDao();
                dao.deleteAll();

                GameEntity gameEntity = new GameEntity("Passing", 0f, 0f);
                dao.insert(gameEntity);
                gameEntity = new GameEntity("Through", 0f, 0f);
                dao.insert(gameEntity);
            });
        }
    };

    public static GameEntityRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (GameEntityRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    GameEntityRoomDatabase.class, "game_entity_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}