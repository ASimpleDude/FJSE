package com.example.japanesequizappversion2.Model;

public class DekiruListModel {
    private String Description;
    private String Title;

    public DekiruListModel() {
    }

    public DekiruListModel(String description, String title) {
        Description = description;
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }
}
