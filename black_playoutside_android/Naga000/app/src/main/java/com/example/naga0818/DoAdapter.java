package com.example.naga0818;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class DoAdapter extends BaseAdapter {

    Context context;
    int layout;
    ArrayList<Do> data;
    LayoutInflater inflater;

    public DoAdapter(Context context, int layout, ArrayList<Do> data) {                 // 생성자
        this.context = context;
        this.layout = layout;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }
    @Override
    public Object getItem(int i) {
        return data.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override     // ArrayList에 내가 가진 데이터를 하나씩 꺼내서 내가 만든 xml(layOut)틀에 넣고 ListView에 하나씩 넣는 메소드
    public View getView(int i, View view, ViewGroup viewGroup) {

        TextView tv_do;

        if(view == null){
            view = inflater.inflate(layout, viewGroup, false);

            tv_do = view.findViewById(R.id.tv_do);

            tv_do.setText(data.get(i).getName());

            if (i % 2 == 1) {
                tv_do.setBackgroundColor(ContextCompat.getColor(context, R.color.pink));
            } else {
                tv_do.setBackgroundColor(ContextCompat.getColor(context, R.color.purple_200));
            }
        }
        return view;
    }
}
