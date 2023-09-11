package com.example.japanesequizappversion2.Model;

public class Content {
    private String first;
    private String second;

    public Content(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public Content() {
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
