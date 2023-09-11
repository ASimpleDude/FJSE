package com.example.japanesequizappversion2.Database;

import android.app.Application;

import com.example.japanesequizappversion2.Database.quizz.Question;
import com.example.japanesequizappversion2.Database.quizz.QuestionDetail;
import com.example.japanesequizappversion2.Model.LessionDekiru;

import java.util.ArrayList;

public class DataManager extends Application {

    public static Question questions = new Question();

    public static ArrayList<QuestionDetail> questionDetailArrayList = new ArrayList<>();
    public static ArrayList<LessionDekiru> lesionDekiruArrayList = new ArrayList<>();
}
