package com.example.naga0818;

import android.graphics.drawable.Drawable;
import android.webkit.WebView;

public class List2vo {
    String img; // String 변경해야함
    Drawable likes;
    String like_num;
    String name;
    String mobile;

    public List2vo(String img, Drawable likes, String like_num, String name, String mobile) {
        this.img = img;
        this.likes = likes;
        this.like_num = like_num;
        this.name = name;
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
