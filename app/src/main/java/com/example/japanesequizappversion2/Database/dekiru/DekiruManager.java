package com.example.japanesequizappversion2.Database.dekiru;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Pair;

import com.example.japanesequizappversion2.Model.Grama;
import com.example.japanesequizappversion2.Model.Kanji;
import com.example.japanesequizappversion2.Model.Word;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DekiruManager {

    public static ArrayList<Pair<String, Kanji>> mListKanjiMemorized = new ArrayList<>();
    public static ArrayList<Pair<String, Kanji>> mListKanjiNotMemorized = new ArrayList<>();

    public static ArrayList<Pair<String, Word>> mListWordMemorized = new ArrayList<>();
    public static ArrayList<Pair<String, Word>> mListWordNotMemorized = new ArrayList<>();

    public static ArrayList<Grama> mListGramaNotMemorized = new ArrayList<>();
    public static ArrayList<Grama> mListGramaMemorized = new ArrayList<>();

    SharedPreferences mSharedPreferences;
    Context context;

    private static DekiruManager sInstance;


    public static final String DEKIRU = "Dekiru";
    public static final String LIST_KANJI_MEMORIZED = "LIST_KANJI_MEMORIZED";
    public static final String LIST_KANJI_NOT_MEMORIZED = "LIST_KANJI_NOT_MEMORIZED";

    public static final String LIST_GRAMA_NOT_MEMORIZED = "LIST_GRANA_NOT_MEMORIZED";
    public static final String LIST_GRAMA_MEMORIZED = "LIST_GRAMA_MEMORIZED";

    public static final String LIST_WORD_NOT_MEMORIZED = "LIST_WORD_NOT_MEMORIZED";
    public static final String LIST_WORD_MEMORIZED = "LIST_WORD_MEMORIZED";

    public static final String TUTORIAL_FLAG_GRAMA = "TUTORIAL_FLAG_GRAMA";
    public static final String TUTORIAL_FLAG_KENJI = "TUTORIAL_FLAG_KENJI";
    public static final String TUTORIAL_FLAG_WORD = "TUTORIAL_FLAG_WORD";

    public static DekiruManager getInstance(Context context) {
        if (sInstance == null)
            sInstance = new DekiruManager(context);

        return sInstance;
    }

    private DekiruManager(Context context) {
        this.context = context;
        mSharedPreferences = context.getSharedPreferences(DEKIRU, Context.MODE_PRIVATE);
        getAllDekiru();
    }

    public void refresh(){
        getAllDekiru();
    }

    public void getAllDekiru() {
        String jsonKanjiMemorized = mSharedPreferences.getString(LIST_KANJI_MEMORIZED, "");
        String jsonKanjiNotMemorized = mSharedPreferences.getString(LIST_KANJI_NOT_MEMORIZED, "");

        String jsonGramaNotMemorized = mSharedPreferences.getString(LIST_GRAMA_NOT_MEMORIZED, "");
        String jsonGramaMemorized = mSharedPreferences.getString(LIST_GRAMA_MEMORIZED, "");

        String jsonWordNotMemorized = mSharedPreferences.getString(LIST_WORD_NOT_MEMORIZED, "");
        String jsonWordMemorized = mSharedPreferences.getString(LIST_WORD_MEMORIZED, "");

        Type typeKanji = new TypeToken<ArrayList<Pair<String, Kanji>>>() {
        }.getType();
        Type typeGrama = new TypeToken<ArrayList<Grama>>() {
        }.getType();
        Type typeWord = new TypeToken<ArrayList<Pair<String, Word>>>() {
        }.getType();

        if (!jsonKanjiMemorized.isEmpty()) {
            mListKanjiMemorized = new Gson().fromJson(jsonKanjiMemorized, typeKanji);
        }

        if (!jsonKanjiMemorized.isEmpty()) {
            mListKanjiNotMemorized = new Gson().fromJson(jsonKanjiNotMemorized, typeKanji);
        }

        if (!jsonGramaNotMemorized.isEmpty()) {
            mListGramaNotMemorized = new Gson().fromJson(jsonGramaNotMemorized, typeGrama);
        }

        if (!jsonGramaMemorized.isEmpty()) {
            mListGramaMemorized = new Gson().fromJson(jsonGramaMemorized, typeGrama);
        }

        if (!jsonWordNotMemorized.isEmpty()) {
            mListWordNotMemorized = new Gson().fromJson(jsonWordNotMemorized, typeWord);
        }

        if (!jsonWordMemorized.isEmpty()) {
            mListWordMemorized = new Gson().fromJson(jsonWordMemorized, typeWord);
        }
    }

    public void saveListKanjiMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_KANJI_MEMORIZED, new Gson().toJson(mListKanjiMemorized));
        editor.apply();
    }

    public void saveListKanjiNotMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_KANJI_NOT_MEMORIZED, new Gson().toJson(mListKanjiNotMemorized));
        editor.apply();
    }

    public void addKanjiMemorized(Pair<String, Kanji> kanji) {
        mListKanjiMemorized.add(kanji);
        saveListKanjiMemorized();
    }

    public void addKanjiNotMemorized(Pair<String, Kanji> kanji) {
        mListKanjiNotMemorized.add(kanji);
        saveListKanjiNotMemorized();
    }

    public void clearKanjiNotMemorized() {
        mListKanjiNotMemorized.clear();
        saveListKanjiNotMemorized();
    }


    public void clearKanjiMemorized() {
        mListKanjiMemorized.clear();
        saveListKanjiMemorized();
    }

    public void saveListGramaMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_GRAMA_MEMORIZED, new Gson().toJson(mListGramaMemorized));
        editor.apply();
    }

    public void saveListGramaNotMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_GRAMA_NOT_MEMORIZED, new Gson().toJson(mListGramaNotMemorized));
        editor.apply();
    }

    public void addGramaMemorized(Grama grama) {
        mListGramaMemorized.add(grama);
        saveListGramaMemorized();
    }

    public void addGramaNotMemorized(Grama grama) {
        mListGramaNotMemorized.add(grama);
        saveListGramaNotMemorized();
    }

    public void clearGramaNotMemorized() {
        mListGramaNotMemorized.clear();
        saveListGramaNotMemorized();
    }

    public void clearGramaMemorized() {
        mListGramaMemorized.clear();
        saveListGramaMemorized();
    }

    public void saveNotDisplayTutorialWord() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(TUTORIAL_FLAG_WORD, false);
        editor.apply();
    }

    public void saveNotDisplayTutorialKenji() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(TUTORIAL_FLAG_KENJI, false);
        editor.apply();
    }

    public void saveNotDisplayTutorialGrama() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(TUTORIAL_FLAG_GRAMA, false);
        editor.apply();
    }

    public boolean isDisplayTutorialGrama() {
        return mSharedPreferences.getBoolean(TUTORIAL_FLAG_GRAMA, true);
    }

    public boolean isDisplayTutorialWord() {
        return mSharedPreferences.getBoolean(TUTORIAL_FLAG_WORD, true);
    }

    public boolean isDisplayTutorialKenji() {
        return mSharedPreferences.getBoolean(TUTORIAL_FLAG_KENJI, true);
    }

    public void saveListWordMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_WORD_MEMORIZED, new Gson().toJson(mListWordMemorized));
        editor.apply();
    }

    public void saveListWordNotMemorized() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(LIST_WORD_NOT_MEMORIZED, new Gson().toJson(mListWordNotMemorized));
        editor.apply();
    }

    public void addWordMemorized(Pair<String, Word> word) {
        mListWordMemorized.add(word);
        saveListWordMemorized();
    }

    public void addWordNotMemorized(Pair<String, Word> word) {
        mListWordNotMemorized.add(word);
        saveListWordNotMemorized();
    }

    public void clearWordNotMemorized() {
        mListWordNotMemorized.clear();
        saveListWordNotMemorized();
    }

    public void clearWordMemorized() {
        mListWordMemorized.clear();
        saveListWordMemorized();
    }
}
