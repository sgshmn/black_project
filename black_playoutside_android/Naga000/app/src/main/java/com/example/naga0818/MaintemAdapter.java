package com.example.naga0818;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MaintemAdapter extends RecyclerView.Adapter<MaintemAdapter.ViewHolder> {

    //ListView에 보여줄 데이터 어댑터 안에 보관!
    private ArrayList<maintemvo> items = new ArrayList<maintemvo>();

    @NonNull
    @Override
    public MaintemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.maintemxml, viewGroup, false);
        return new MaintemAdapter.ViewHolder(itemView);
    }

    public void addItem(maintemvo item) {
        this.items.add(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MaintemAdapter.ViewHolder viewHolder, int position) {
        maintemvo item = items.get(position);
        viewHolder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.main_tem_img);
            textView = itemView.findViewById(R.id.main_tem_title);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition() + 1;  // db에서 seq는 1부터 시작하기 때문이다.
                    Intent intent = new Intent(view.getContext(), List2_theme.class);
                    intent.putExtra("theme", pos + "");
                    view.getContext().startActivity(intent);
                }
            });

        }

        public void setItem(maintemvo item) {
            textView.setText(item.getName());
            imageView.setImageDrawable(item.getImg());
        }

    }

}
