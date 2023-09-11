package com.example.japanesequizappversion2.Model;


import android.util.Pair;

public class Grama {
    private Content content;
    private String mean;
    private String note;
    private Sentence sentence;

    public Grama(Content content, String mean, String note, Sentence sentence) {
        this.content = content;
        this.mean = mean;
        this.note = note;
        this.sentence = sentence;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Sentence getSentence() {
        return sentence;
    }

    public void setSentence(Sentence sentence) {
        this.sentence = sentence;
    }
}
