package com.example.japanesequizappversion2.Question;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Adapter.I0nClickNextQuestion;
import com.example.japanesequizappversion2.Adapter.QuestionAdapter;
import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Database.quizz.QuestionDetail;
import com.example.japanesequizappversion2.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionActivity extends AppCompatActivity implements I0nClickNextQuestion {

    ArrayList<QuestionDetail> newQuestionDetailArrayList = new ArrayList<>();
    QuestionAdapter adapter;
    TextView tvNumber;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        View header = findViewById(R.id.headerQuestion);
        ImageView back = header.findViewById(R.id.viewBack);
        MaterialButton btnNext = findViewById(R.id.btnNextQuestion);
        RecyclerView rc = findViewById(R.id.rcQuestion);
        progress = findViewById(R.id.process);
        tvNumber = findViewById(R.id.tvNumber);

        newQuestionDetailArrayList.addAll(DataManager.questionDetailArrayList);
        Collections.shuffle(newQuestionDetailArrayList);
        adapter = new QuestionAdapter(this, newQuestionDetailArrayList, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }

            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
        rc.setLayoutManager(linearLayoutManager);
        rc.setAdapter(adapter);

        back.setOnClickListener(v -> onBackPressed());

        int length = newQuestionDetailArrayList.size();
        btnNext.setOnClickListener(v -> {
                    onClick();
                    if (newQuestionDetailArrayList.size() != 0) {
                        newQuestionDetailArrayList.remove(0);
                        adapter.updateData(newQuestionDetailArrayList);
                        tvNumber.setText(length - newQuestionDetailArrayList.size() + "/" + length);
                        progress.setProgress(DataManager.questionDetailArrayList.size() - newQuestionDetailArrayList.size());
                    }
                }
        );

        progress.setMax(DataManager.questionDetailArrayList.size());
        progress.setProgress(1);

        tvNumber.setText(length - newQuestionDetailArrayList.size() + "/" + length);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick() {
        if (newQuestionDetailArrayList.size() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Số câu trả lời đúng và sai")
                    .setMessage(adapter.getValidAnswer())
                    .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    })
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            return;
        }
    }
}