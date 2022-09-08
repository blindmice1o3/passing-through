package com.jackingaming.passingthrough;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jackingaming.passingthrough.models.GameEntityViewModel;
import com.jackingaming.passingthrough.models.database.entities.GameEntity;
import com.jackingaming.passingthrough.views.GameEntityListAdapter;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_GAME_ENTITY_ACTIVITY_REQUEST_CODE = 1;

    private GameEntityViewModel gameEntityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final GameEntityListAdapter adapter = new GameEntityListAdapter(new GameEntityListAdapter.GameEntityDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        gameEntityViewModel = new ViewModelProvider(this).get(GameEntityViewModel.class);
        gameEntityViewModel.getGameEntities().observe(this, gameEntities -> {
            // Update the cached copy of the gameEntities in the adapter.
            adapter.submitList(gameEntities);
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewGameEntityActivity.class);
            startActivityForResult(intent, NEW_GAME_ENTITY_ACTIVITY_REQUEST_CODE);
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_GAME_ENTITY_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            GameEntity gameEntity = new GameEntity(data.getStringExtra(NewGameEntityActivity.EXTRA_REPLY));
            gameEntityViewModel.insert(gameEntity);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}