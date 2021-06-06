package ru.geekbrains.lesson2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private SwitchMaterial changeTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE);

        changeTheme = findViewById(R.id.change_theme);

        checkNightModeActivated();

        changeTheme.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                saveNightModeState(true);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                saveNightModeState(false);
            }
            recreate();
        });
    }

    public void checkNightModeActivated() {
        if (sharedPreferences.getBoolean("keyNightMode", false)) {
            changeTheme.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            changeTheme.setChecked(false);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    private void saveNightModeState(boolean nightMode) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("keyNightMode", nightMode).apply();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("keyChangeTheme", changeTheme.isChecked());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        changeTheme.setChecked(savedInstanceState.getBoolean("keyChangeTheme"));
    }
}