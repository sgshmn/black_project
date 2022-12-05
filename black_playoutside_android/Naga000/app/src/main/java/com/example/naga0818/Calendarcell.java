package com.example.naga0818;

import java.util.ArrayList;

public class Calendarcell {
    private String day;
    private ArrayList<Do> dolist;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public ArrayList<Do> getDolist() {
        return dolist;
    }

    public void setDolist(ArrayList<Do> dolist) {
        this.dolist = dolist;
    }

    public Calendarcell(String day, ArrayList<Do> dolist) {
        this.day = day;
        this.dolist = dolist;
    }
}
