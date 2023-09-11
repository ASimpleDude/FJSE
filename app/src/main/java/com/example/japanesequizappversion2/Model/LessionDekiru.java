package com.example.japanesequizappversion2.Model;

import android.util.Pair;

import java.util.ArrayList;


public class LessionDekiru {
    private ArrayList<Pair<String, Kanji>> listKanji;
    private ArrayList<Pair<String, Word>> newWordDekiru;
    private ArrayList<Grama> grama;

    public LessionDekiru(ArrayList<Pair<String, Kanji>> listKanji, ArrayList<Pair<String, Word>> newWordDekiru, ArrayList<Grama> grama) {
        this.listKanji = listKanji;
        this.newWordDekiru = newWordDekiru;
        this.grama = grama;
    }

    public ArrayList<Pair<String, Kanji>> getListKanji() {
        return listKanji;
    }

    public void setListKanji(ArrayList<Pair<String, Kanji>> listKanji) {
        this.listKanji = listKanji;
    }

    public ArrayList<Pair<String, Word>> getNewWordDekiru() {
        return newWordDekiru;
    }

    public void setNewWordDekiru(ArrayList<Pair<String, Word>> newWordDekiru) {
        this.newWordDekiru = newWordDekiru;
    }

    public ArrayList<Grama> getGrama() {
        return grama;
    }

    public void setGrama(ArrayList<Grama> grama) {
        this.grama = grama;
    }
}
