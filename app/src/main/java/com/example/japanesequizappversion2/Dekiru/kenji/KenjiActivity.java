package com.example.japanesequizappversion2.Dekiru.kenji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.R;

public class KenjiActivity extends AppCompatActivity {

    public DekiruManager mDekiruManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kenji);
        mDekiruManager = DekiruManager.getInstance(this);
        mDekiruManager.getAllDekiru();
    }
}