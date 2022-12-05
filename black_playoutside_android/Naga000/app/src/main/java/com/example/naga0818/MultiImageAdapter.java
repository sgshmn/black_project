package com.example.naga0818;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MultiImageAdapter extends RecyclerView.Adapter<MultiImageAdapter.ViewHolder>{
    private ArrayList<Uri> mData = null ;
    private Context mContext = null ;

    MultiImageAdapter(ArrayList<Uri> list, Context context) {
        mData = list ;
        mContext = context;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ViewHolder(View itemView) {
            super(itemView) ;
            image = itemView.findViewById(R.id.image);
        }
    }
    @Override    // 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;
        View view = inflater.inflate(R.layout.multi_image_item, parent, false) ;
        ViewHolder viewHolder = new ViewHolder(view) ;

        return viewHolder ;
    }
    @Override     // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
    public void onBindViewHolder(ViewHolder holder, int position) {
        Uri image_uri = mData.get(position) ;
        // 객체 생성시 전달받은 list 객체에 들어있는 uri 정보를 Glide 라이브러리를 통해 보여주게 됩니다.
        Glide.with(mContext).load(image_uri).into(holder.image);
    }
    @Override
    public int getItemCount() {
        return mData.size() ;
    }
}