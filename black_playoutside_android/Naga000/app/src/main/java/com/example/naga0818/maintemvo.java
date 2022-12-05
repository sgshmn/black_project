package com.example.naga0818;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;


public class maintemvo {

    private Drawable main_tem_img;
    private String main_tem_title;

    public maintemvo(Drawable img, String name) {
        this.main_tem_img = img;
        this.main_tem_title = name;
    }

    public Drawable getImg() {
        return this.main_tem_img;
    }

    public String getName() {
        return this.main_tem_title;
    }

    public void setName(String name) {
        this.main_tem_title = name;
    }

}
