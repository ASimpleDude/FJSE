package com.example.japanesequizappversion2.MainScreen;

import static com.example.japanesequizappversion2.Database.DataManager.questionDetailArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.japanesequizappversion2.Adapter.TrainingAdapter;
import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.Database.quizz.Lesson;
import com.example.japanesequizappversion2.Database.quizz.QuestionDetail;
import com.example.japanesequizappversion2.Question.QuestionActivity;
import com.example.japanesequizappversion2.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    TrainingAdapter adapter;
    RecyclerView rc;
    MaterialButton btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        rc = findViewById(R.id.rc);
        btnNext = findViewById(R.id.btnNext);

        View header = findViewById(R.id.header);
        ImageView back = header.findViewById(R.id.viewBack);

        back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter = new TrainingAdapter(this, DataManager.questions.getLessons());

        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);

        btnNext.setOnClickListener(v -> {
            if (adapter.numberLesson.size() <= 0) {
                Toast.makeText(getApplicationContext(), "Cần chọn ít nhất 1 bài", Toast.LENGTH_SHORT).show();
                return;
            }

            questionDetailArrayList.clear();
            ArrayList<Integer> numberLesson = adapter.numberLesson;
            int numberGet = (int) Math.ceil(30 / numberLesson.size());
            for (Integer number : numberLesson) {
                Lesson lesson = DataManager.questions.getLessons().get(number);
                if (lesson.getListLesson().size() < numberGet) {
                    questionDetailArrayList.addAll(lesson.getListLesson());
                } else {
                    questionDetailArrayList.addAll(lesson.getListLesson().subList(0, numberGet));
                }
            }
            ArrayList<QuestionDetail> tempQuestionDetailArrayList = new ArrayList<>();
            tempQuestionDetailArrayList.addAll(questionDetailArrayList);
            if (tempQuestionDetailArrayList.size() > 30) {
                questionDetailArrayList.clear();
                questionDetailArrayList.addAll(tempQuestionDetailArrayList.subList(0, 29));
            }

            startActivity(new Intent(this, QuestionActivity.class));
        });
    }
}