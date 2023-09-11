package com.example.japanesequizappversion2.Model;

public class KanamojiModel {
    private String textModel;
    private String imgUrl;

    private String gifUrl;

    public KanamojiModel() {
    }

    public KanamojiModel(String textModel, String imgUrl) {
        this.textModel = textModel;
        this.imgUrl = imgUrl;
    }

    public KanamojiModel(String textModel, String imgUrl, String gifUrl) {
        this.textModel = textModel;
        this.imgUrl = imgUrl;
        this.gifUrl = gifUrl;
    }

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public String getTextModel() {
        return textModel;
    }

    public void setTextModel(String textModel) {
        this.textModel = textModel;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
