package com.example.gymproject;

public class Workout {

    private String name;
    private String subText;
    private int imgId;

    public Workout(String name, String subText, int imgId) {
        this.name = name;
        this.subText = subText;
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubText() {
        return subText;
    }

    public void setSubText(String subText) {
        this.subText = subText;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }
}
