package com.example.naga0818;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class List2Adapter extends RecyclerView.Adapter<List2Adapter.ViewHolder> implements OnPersonItemClickListener{
    private ArrayList<String> items = new ArrayList<String>();
    private OnPersonItemClickListener listener;
    static Context context;

    private String server_url = MainActivity.getServerURL();

    @NonNull
    @Override
    public List2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        context = viewGroup.getContext();

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.list2, viewGroup, false);
        return new List2Adapter.ViewHolder(itemView,this);

    }

    public void addItem(String item){
        items.add(item);
    }

    public void setOnItemClickListener(OnPersonItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull List2Adapter.ViewHolder viewHolder, int position) {
        int pos = position;
        String item = items.get(pos);
        viewHolder.setItem(item);

        Log.d("onBindViewHolder", "onBindViewHolder");
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("String", items.size() + "");

//                Intent intent1 = new Intent(v.getContext(), ListDetail.class);
//                intent1.putExtra("prog_data_string", item);
//
//                v.getContext().startActivity(intent1);
            }
        });
    }
    

    @Override
    public int getItemCount() {
        Log.v("getcount", items.size() + "");
            return items.size();
            }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {
        if (listener != null) {
            listener.onItemClick(holder, view, position);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView wv_list2_prog_photo;
        WebSettings webSettings;

        ImageView iv_list2_heart;
        TextView tv_list2_prog_name;
        TextView tv_list2_prog_addr;
        TextView tv_list2_like_cnt;

        public ViewHolder(@NonNull View itemView, final OnPersonItemClickListener listener) {
            super(itemView);
            wv_list2_prog_photo = itemView.findViewById(R.id.wv_list2_prog_photo);
            iv_list2_heart = itemView.findViewById(R.id.iv_list2_heart);
            tv_list2_prog_name = itemView.findViewById(R.id.tv_list2_prog_name);
            tv_list2_prog_addr = itemView.findViewById(R.id.tv_list2_prog_addr);
            tv_list2_like_cnt = itemView.findViewById(R.id.tv_list2_like_cnt);


        }
        public void setItem(String item){

            String server_url = MainActivity.getServerURL();
            Log.d("setItem", "setItem");

            String[] item_list = item.split(";");

            tv_list2_prog_name.setText(item_list[1]);
            tv_list2_prog_addr.setText(item_list[3]);
            
//            tv_list2_like_cnt.setText(); // 서버와 통신을 해야한다 #####################################

            iv_list2_heart.setImageResource(R.drawable.lovered); // 하트 그림인데 이거는 통신해서 할 필요는 없을 듯

//            wv_list2_prog_photo.setWebViewClient(new WebViewClient());
//            webSettings = wv_list2_prog_photo.getSettings();
//
//            webSettings.setUseWideViewPort(true);
//            webSettings.setLoadWithOverviewMode(true);

//            wv_list2_prog_photo.setHorizontalScrollBarEnabled(false);
//            wv_list2_prog_photo.setVerticalScrollBarEnabled(false);

            Glide.with(List2Adapter.context)
                    .load(server_url + "/static/prog_photo/" + item_list[0] + "/original.png")
                    .into(wv_list2_prog_photo);


            wv_list2_prog_photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent1 = new Intent(v.getContext(), ListDetail.class);
                    intent1.putExtra("prog_data_string", item);
                    v.getContext().startActivity(intent1);
                }
            });


//            wv_list2_prog_photo.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    Log.d("List2Adapter", "onTouch");
//
//                    Intent intent1 = new Intent(v.getContext(), ListDetail.class);
//                    intent1.putExtra("prog_data_string", item);
//                    v.getContext().startActivity(intent1);
//
////                    return (event.getAction() == MotionEvent.ACTION_MOVE);
//                    return false;
//                }
//            });

//            wv_list2_prog_photo.loadUrl("http://192.168.21.62:5000/static/prog_photo/" + item_list[0] + "/original.png");


        }

    }





}
