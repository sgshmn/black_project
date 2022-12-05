package com.example.naga0818;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainCHAdapter extends RecyclerView.Adapter<MainCHAdapter.ViewHolder> {

    //ListView 에 보여줄 데이터 어댑터 안에 보관!
    private ArrayList<MainCheHumVo> items2 =  new ArrayList<MainCheHumVo>();

    private String prog_data_string;


    @NonNull
    @Override
    public MainCHAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.layout_chehum10, viewGroup, false);
        return new MainCHAdapter.ViewHolder(itemView);
    }


    public void addItem(MainCheHumVo item){
        this.items2.add(item);
    }

    public void setItems(ArrayList<MainCheHumVo> items) {
        this.items2 = items;
    }

    public MainCheHumVo getItem(int position){
        return items2.get(position);
    }

    public void setItem(int position, MainCheHumVo item) {
        items2.set(position, item);
    }


    @Override
    public void onBindViewHolder(@NonNull MainCHAdapter.ViewHolder viewHolder, int position) {
        MainCheHumVo item = items2.get(position);
        viewHolder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items2.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView1;
        TextView textView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.main_ch_img);
            textView1 = itemView.findViewById(R.id.main_ch_title);

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String data = "";
                    int pos = getAdapterPosition();
    //                Toast.makeText(view.getContext(), "click" + pos, Toast.LENGTH_SHORT).show();
                    if (pos != RecyclerView.NO_POSITION){
                        // cilck event
                    }

                    if (pos == 0){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 1){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 2){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 3){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 4){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 5){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 6){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 7){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 8){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    } else if (pos == 9){
                        Intent intent = new Intent(view.getContext(), ListDetail.class);
                        view.getContext().startActivity(intent);
                    }

                }
            });





        }
        public void setItem(MainCheHumVo item){
            textView1.setText(item.getName());
            imageView1.setImageDrawable(item.getImg());
        }
        public void setImage(int resid){
            imageView1.setImageResource(resid);
        }

    }


}
