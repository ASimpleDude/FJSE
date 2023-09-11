package com.example.japanesequizappversion2.Database.quizz;

import java.util.ArrayList;

public class Lesson {
    private ArrayList<QuestionDetail> listLesson;

    public Lesson(ArrayList<QuestionDetail> listLesson) {
        this.listLesson = listLesson;
    }

    public ArrayList<QuestionDetail> getListLesson() {
        return listLesson;
    }

    public void setListLesson(ArrayList<QuestionDetail> listLesson) {
        this.listLesson = listLesson;
    }
}
