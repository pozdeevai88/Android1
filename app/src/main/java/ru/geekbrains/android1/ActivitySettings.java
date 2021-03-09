package ru.geekbrains.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.button.MaterialButton;

public class ActivitySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        MaterialButton buttonSaveSettings = findViewById(R.id.ButtonSaveSettings);

        buttonSaveSettings.setOnClickListener(v -> {
            finish();
        });
    }
}