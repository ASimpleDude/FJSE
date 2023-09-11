package com.example.japanesequizappversion2.Model;

public class Sentence {
    private String jp;
    private String vi;

    public Sentence() {
    }

    public Sentence(String jp, String vi) {
        this.jp = jp;
        this.vi = vi;
    }

    public String getJp() {
        return jp;
    }

    public void setJp(String jp) {
        this.jp = jp;
    }

    public String getVi() {
        return vi;
    }

    public void setVi(String vi) {
        this.vi = vi;
    }
}
