package com.example.japanesequizappversion2.Dekiru;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Dekiru.grama.GramaActivity;
import com.example.japanesequizappversion2.Dekiru.kenji.KenjiActivity;
import com.example.japanesequizappversion2.Dekiru.word.kenji.WordActivity;
import com.example.japanesequizappversion2.MainScreen.MainActivity;
import com.example.japanesequizappversion2.R;

public class FlashCardDekiruActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_dekiru);
        findViewById(R.id.btn_kenji).setOnClickListener(v ->
                startActivity(new Intent(FlashCardDekiruActivity.this, KenjiActivity.class)));
        findViewById(R.id.btn_grama).setOnClickListener(v ->
                startActivity(new Intent(FlashCardDekiruActivity.this, GramaActivity.class)));
        findViewById(R.id.btn_Word).setOnClickListener(v ->
                startActivity(new Intent(FlashCardDekiruActivity.this, WordActivity.class)));
        findViewById(R.id.viewBack).setOnClickListener(v->
                startActivity(new Intent(FlashCardDekiruActivity.this, MainActivity.class)));
    }
}