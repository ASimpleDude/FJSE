package com.example.japanesequizappversion2.Model;

public class Kanji {
    private String mean;
    private String read;

    public Kanji(String mean, String read) {
        this.mean = mean;
        this.read = read;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }
}
