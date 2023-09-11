package com.example.japanesequizappversion2.Dekiru.word.kenji;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.dekiru.DekiruManager;
import com.example.japanesequizappversion2.R;

public class WordActivity extends AppCompatActivity {

    public DekiruManager mDekiruManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        mDekiruManager = DekiruManager.getInstance(this);
        mDekiruManager.getAllDekiru();
    }
}