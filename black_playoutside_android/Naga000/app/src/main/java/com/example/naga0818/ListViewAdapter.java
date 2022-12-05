package com.example.naga0818;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.ListMenuItemView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ViewHolder> {
    ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.listview_item, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        ListViewItem item = items.get(position);
        viewHolder.setItem(item);
    }

    public void addItem(ListViewItem item){
        items.add(item);
    }

    public void setItems(ArrayList<ListViewItem> items) {
        this.items = items;
    }

    public ListViewItem getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, ListViewItem item) {
        items.set(position, item);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            textView2 = itemView.findViewById(R.id.count);
        }
        public void setItem(ListViewItem item){
            textView.setText(item.getText());
            textView2.setText(item.getCount());
        }
        public void setImage(int resid){

        }

    }


}
