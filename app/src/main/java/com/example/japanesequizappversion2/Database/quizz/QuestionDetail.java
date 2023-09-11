package com.example.japanesequizappversion2.Database.quizz;

import android.util.Pair;

import java.util.ArrayList;

public class QuestionDetail {
    private String answer;
    private String question;
    private ArrayList<Pair<String, String>> answerMatching;
    private ArrayList<Pair<String, String>> questionMatching;
    private ArrayList<String> answerChose;
    private Integer typeQuestion;

    public QuestionDetail(String answer, String question, ArrayList<Pair<String, String>> answerMatching, ArrayList<Pair<String, String>> questionMatching, ArrayList<String> answerChose, Integer typeQuestion) {
        this.answer = answer;
        this.question = question;
        this.answerMatching = answerMatching;
        this.questionMatching = questionMatching;
        this.answerChose = answerChose;
        this.typeQuestion = typeQuestion;
    }

    public ArrayList<String> getAnswerChose() {
        return answerChose;
    }

    public void setAnswerChose(ArrayList<String> answerChose) {
        this.answerChose = answerChose;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public ArrayList<Pair<String, String>> getAnswerMatching() {
        return answerMatching;
    }

    public void setAnswerMatching(ArrayList<Pair<String, String>> answerMatching) {
        this.answerMatching = answerMatching;
    }

    public ArrayList<Pair<String, String>> getQuestionMatching() {
        return questionMatching;
    }

    public void setQuestionMatching(ArrayList<Pair<String, String>> questionMatching) {
        this.questionMatching = questionMatching;
    }

    public Integer getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(Integer typeQuestion) {
        this.typeQuestion = typeQuestion;
    }
}
