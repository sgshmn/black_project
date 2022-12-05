package com.example.naga0818;

import android.graphics.drawable.Drawable;

public class Review {
    String profile;
    String id;
    String review_text;
    String date;

    public Review(String profile, String id, String review_text, String date) {
        this.profile = profile;
        this.id = id;
        this.review_text = review_text;
        this.date = date;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview_text() {
        return review_text;
    }

    public void setReview_text(String review_text) {
        this.review_text = review_text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
