package com.example.naga0818;

import android.graphics.drawable.Drawable;

public class List1vo {
    Drawable img;
    String name;
    String mobile;

    public List1vo(Drawable img, String name, String mobile) {
        this.img = img;
        this.name = name;
        this.mobile = mobile;
    }

    public Drawable getImg() {
        return img;
    }

    public void setImg(Drawable img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
