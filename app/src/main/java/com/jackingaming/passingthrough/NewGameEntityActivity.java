package com.jackingaming.passingthrough;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewGameEntityActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.gameentitylistsql.REPLY";

    private EditText editTextName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_entity);
        editTextName = findViewById(R.id.edit_name);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editTextName.getText())) {
                setResult(RESULT_CANCELED, replyIntent);
            } else {
                String name = editTextName.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, name);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}