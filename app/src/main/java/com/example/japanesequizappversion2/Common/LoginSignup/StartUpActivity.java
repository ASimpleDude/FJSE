package com.example.japanesequizappversion2.Common.LoginSignup;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.R;

import java.util.Locale;

public class StartUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_up);
    }

    public void callLoginActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.btn_startup_login), "transition_login");
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpActivity.this, pairs);
        startActivity(intent);
    }

    public void callSignupActivity(View view) {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
//        Pair[] pairs = new Pair[1];
//        pairs[0] = new Pair<View, String>(findViewById(R.id.btn_startup_signup), "transition_signup");
//        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(StartUpActivity.this, pairs);
        startActivity(intent);
    }

    public void btn_japanese(View view) {
        setLocale("ja");
        recreate();
    }

    public void btn_english(View view) {
        setLocale("en");
        recreate();
    }
    public void btn_vietnamese(View view) {
        setLocale("vi");
        recreate();
    }
    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Configuration config = getResources().getConfiguration();
        config.locale = locale;
        getResources().updateConfiguration(config, null);
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
}