package com.example.japanesequizappversion2.StudyScreen;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.DigitalInkRecognize.DrawView;
import com.example.japanesequizappversion2.DigitalInkRecognize.StrokeManager;
import com.example.japanesequizappversion2.R;

public class PracticeHandWritingActivity extends AppCompatActivity {
    private Button btnRecognize;
    private Button btnClear;
    private DrawView drawView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_hand_writing);
        btnRecognize = findViewById(R.id.btn_recognize);
        btnClear = findViewById(R.id.btn_clear);
        drawView = findViewById(R.id.draw_view);
        textView = findViewById(R.id.text_recognize);

        StrokeManager.download();

        btnRecognize.setOnClickListener(view -> StrokeManager.recognize(textView));

        btnClear.setOnClickListener(view -> {
            drawView.clear();
            StrokeManager.clear();
            textView.setText("");
        });
    }
}