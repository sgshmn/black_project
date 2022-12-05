package com.example.naga0818;

import android.graphics.drawable.Drawable;
import android.webkit.WebView;

public class MainTopVo {

    private Drawable main_top_img;
    private String main_top_title;

    public MainTopVo(Drawable img, String name) {
        this.main_top_img = img;
        this.main_top_title = name;
    }

    public Drawable getImg() {
        return this.main_top_img;
    }

    public void setImg(Drawable img) {
        this.main_top_img = img;
    }

    public String getName() {
        return this.main_top_title;
    }

    public void setName(String name) {
        this.main_top_title = name;
    }

}

