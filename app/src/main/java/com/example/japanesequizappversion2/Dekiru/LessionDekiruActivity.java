package com.example.japanesequizappversion2.Dekiru;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.japanesequizappversion2.Database.DataManager;
import com.example.japanesequizappversion2.R;

import java.util.ArrayList;

public class LessionDekiruActivity extends AppCompatActivity {

    private ArrayList<String> mListName = new ArrayList<>();
    private ListView mListView;
    private ArrayAdapter mArrayAdapter;

    public static int positionLesion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lession_dekiru);
        View header = findViewById(R.id.headerQuestion2);
        ImageView back = header.findViewById(R.id.viewBack);
        back.setOnClickListener(v -> onBackPressed());

        for (int i = 0; i < DataManager.lesionDekiruArrayList.size(); i++) {
            mListName.add("Bài " + (i + 1));
        }

        mListView = findViewById(R.id.listListDekiru);

        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mListName);
        mListView.setAdapter(mArrayAdapter);

        mListView.setOnItemClickListener((parent, view, position, id) -> {
            positionLesion = position;
            Intent intent = new Intent(getApplicationContext(), FlashCardDekiruActivity.class);
            startActivity(intent);
        });
    }
}