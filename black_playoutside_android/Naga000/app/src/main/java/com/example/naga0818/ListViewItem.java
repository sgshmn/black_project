package com.example.naga0818;

public class ListViewItem {

    private String count;
    private String text;

    public ListViewItem(String count, String text) {
        this.count = count;
        this.text = text;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
