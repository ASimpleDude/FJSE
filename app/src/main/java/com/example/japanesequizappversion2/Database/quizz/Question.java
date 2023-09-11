package com.example.japanesequizappversion2.Database.quizz;

import java.util.ArrayList;

public class Question {
    private ArrayList<Lesson> lessons;

    public Question() {
    }

    public Question(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}

