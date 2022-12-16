package com.example.mytodoapp;

public class Tutorial {

    private String title;
    private String description;
    private int logoImage;
    private int examplImage;
    private int id;

    public Tutorial(String title,String description,int logo,int example) {
        this.title = title;
        this.description = description;
        this.logoImage = logo;
        this.examplImage = example;
    }
    public Tutorial(String title,String description,int logo) {
        this.title = title;
        this.description = description;
        this.logoImage = logo;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLogoImage() {
        return logoImage;
    }

    public void setLogoImage(int logoImage) {
        this.logoImage = logoImage;
    }

    public int getExamplImage() {
        return examplImage;
    }


    public void setExamplImage(int examplImage) {
        this.examplImage = examplImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
