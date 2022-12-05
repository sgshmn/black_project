package com.example.naga0818;

import android.graphics.drawable.Drawable;

public class MainCheHumVo {


    private Drawable main_ch_img;
    private String main_ch_title;

    public MainCheHumVo(Drawable img, String name) {
        this.main_ch_img = img;
        this.main_ch_title = name;
    }

    public Drawable getImg() {
        return this.main_ch_img;
    }

    public void setImg(Drawable img) {
        this.main_ch_img = img;
    }

    public String getName() {
        return this.main_ch_title;
    }

    public void setName(String name) {
        this.main_ch_title = name;
    }

}
