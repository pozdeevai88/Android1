package ru.geekbrains.android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioButton;

import com.google.android.material.button.MaterialButton;

public class ActivitySettings extends AppCompatActivity {

    static final String LOG_TAG = "CALC_LOG: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Log.d(LOG_TAG, "Settings.onCreate()");

        MaterialButton buttonSaveSettings = findViewById(R.id.ButtonSaveSettings);
        RadioButton radioLightTheme = findViewById(R.id.SelectLightTheme);
        RadioButton radioDarkTheme = findViewById(R.id.SelectDarkTheme);
        SharedPreferences sharedPreferences = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor settingsEditor = sharedPreferences.edit();

        boolean isSavedThemeDark = sharedPreferences.getBoolean("isDarkTheme", false);
        Log.d(LOG_TAG, "Settings.isSavedThemeDark = " + isSavedThemeDark);


        if (isSavedThemeDark) {
            radioDarkTheme.setChecked(true);
        } else {
            radioLightTheme.setChecked(true);
        }

        radioLightTheme.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            settingsEditor.putBoolean("isDarkTheme", false).apply();
            Log.d(LOG_TAG,"Saved isDarkTheme = " + sharedPreferences.getBoolean("isDarkTheme", false));
        });

        radioDarkTheme.setOnClickListener(v -> {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            settingsEditor.putBoolean("isDarkTheme", true).apply();
            Log.d(LOG_TAG,"Saved isDarkTheme = " + sharedPreferences.getBoolean("isDarkTheme", false));
        });

        buttonSaveSettings.setOnClickListener(v -> finish());
    }
}