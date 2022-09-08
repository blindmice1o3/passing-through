package com.jackingaming.passingthrough.models.database.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jackingaming.passingthrough.models.database.entities.GameEntity;

import java.util.List;

@Dao
public interface GameEntityDao {
    // allowing the insert of the same game entity multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(GameEntity gameEntity);

    @Query("DELETE FROM game_entity_table")
    void deleteAll();

    @Query("SELECT * FROM game_entity_table ORDER BY name ASC")
    LiveData<List<GameEntity>> getAlphabetizedGameEntities();
}