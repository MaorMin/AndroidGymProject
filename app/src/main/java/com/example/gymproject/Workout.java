package com.example.gymproject;

import java.util.ArrayList;
import java.util.List;

public class Workout {

    private String name;
    private int imgId;
    private List<Exercise> exeList;

    public Workout(String name, int imgId) {
        this.name = name;
        this.imgId = imgId;
        this.exeList = new ArrayList<>();
    }

    public Workout(){  this.exeList = new ArrayList<>(); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public List<Exercise> getExeList() {
        return exeList;
    }

    public void setExeList(List<Exercise> exeList) {
        this.exeList = exeList;
    }
}
