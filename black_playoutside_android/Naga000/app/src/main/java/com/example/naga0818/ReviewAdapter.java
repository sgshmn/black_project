package com.example.naga0818;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {
    private ArrayList<String> items = new ArrayList<String>();

    private String server_url = MainActivity.getServerURL();

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        Log.v("뷰홀더", "dkddk");
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.review_view, viewGroup, false);
        return new ReviewAdapter.ViewHolder(itemView);
    }

    public void addItem(String item){
    items.add(item);
}

    public void setItems(ArrayList<String> items){
        this.items = items;
    }

    public String getItem(int position){
        return items.get(position);
    }

    public void setItem(int position, String item){
        items.set(position, item);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ViewHolder viewHolder, int position) {
        String item = items.get(position);
        viewHolder.setItem(item);
        
    }

    @Override
    public int getItemCount() {
        Log.v("Review getcount", items.size() + "");
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private WebView webView;
        private WebSettings webSettings;
        private String server_url = MainActivity.getServerURL();

        TextView tv_id;
        TextView tv_review_text;
        TextView tv_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.img_profile);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_review_text = itemView.findViewById(R.id.tv_review_text);
            tv_date = itemView.findViewById(R.id.tv_date);
        }
        public void setItem(String item){

            String[] item_list = item.split(";");

            tv_id.setText(item_list[1]); // 닉네임으로 바꿔야한다
            tv_review_text.setText(item_list[4]);
            tv_date.setText(item_list[5]);

            webView.setWebViewClient(new WebViewClient());
            webSettings = webView.getSettings();

            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);

            webView.setHorizontalScrollBarEnabled(false);
            webView.setVerticalScrollBarEnabled(false);

            webView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return (event.getAction() == MotionEvent.ACTION_MOVE);
                }
            });
//
            webView.loadUrl(server_url + "/static/profile/" + item_list[1] + "/fix.png");



        }
        public void setImage(int resid){
//            img_profile.setImageResource(resid);
        }
    }
}