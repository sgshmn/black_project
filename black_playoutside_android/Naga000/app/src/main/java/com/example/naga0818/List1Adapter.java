package com.example.naga0818;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class List1Adapter extends RecyclerView.Adapter<List1Adapter.ViewHolder> {
    ArrayList<List1vo> items = new ArrayList<List1vo>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list1xml, viewGroup, false);
        return new ViewHolder(itemView);
    }

    public void addItem(List1vo item){
        items.add(item);
    }

    public void setItems(ArrayList<List1vo> items) {
        this.items = items;
    }

    public List1vo getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, List1vo item) {
        items.set(position, item);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        List1vo item = items.get(position);
        viewHolder.setItem(item);
    }
    @Override
    public int getItemCount() {
        return items.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;
        TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.wv_list2_prog_photo);
            textView = itemView.findViewById(R.id.tv_list2_prog_name);
            textView2 = itemView.findViewById(R.id.tv_list2_prog_addr);
        }
        public void setItem(List1vo item){
            textView.setText(item.getName());
            textView2.setText(item.getMobile());
            imageView.setImageDrawable(item.getImg());
        }
        public void setImage(int resid){
            imageView.setImageResource(resid);
        }

    }


}
