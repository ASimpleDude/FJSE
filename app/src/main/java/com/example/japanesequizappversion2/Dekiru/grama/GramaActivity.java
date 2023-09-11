package com.example.japanesequizappversion2.Dekiru.grama;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.R;

public class GramaActivity extends AppCompatActivity {

    public DekiruManager mDekiruManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grama);
        mDekiruManager = DekiruManager.getInstance(this);
        mDekiruManager.getAllDekiru();
    }
}