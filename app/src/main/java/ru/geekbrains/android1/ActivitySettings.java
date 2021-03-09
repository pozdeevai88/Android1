package ru.geekbrains.android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButton;

public class ActivitySettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPreferences = getSharedPreferences("123", MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor = sharedPreferences.edit();

        MaterialButton buttonSaveSettings = findViewById(R.id.ButtonSaveSettings);
        RadioButton radioLightTheme = findViewById(R.id.SelectLightTheme);
        RadioButton radioDarkTheme = findViewById(R.id.SelectDarkTheme);

        radioLightTheme.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            recreate();
        });
        radioDarkTheme.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            recreate();
        });

        buttonSaveSettings.setOnClickListener(v -> {
            finish();
        });
    }
}