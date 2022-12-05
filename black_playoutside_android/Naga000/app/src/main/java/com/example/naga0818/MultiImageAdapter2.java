package com.example.naga0818;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MultiImageAdapter2 extends RecyclerView.Adapter<MultiImageAdapter2.MultiImage2ViewHolder> {

    private List<MultiImage2> multiImage2List;

    MultiImageAdapter2(List<MultiImage2> multiImage2List) {
        this.multiImage2List = multiImage2List;
    }

    @NonNull
    @Override
    public MultiImage2ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_multi_image2, viewGroup, false);
        return new MultiImage2ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MultiImage2ViewHolder multiImage2ViewHolder, int i) {
        MultiImage2 multiImage2 = multiImage2List.get(i);
        multiImage2ViewHolder.image2.setImageResource(multiImage2.getMultiImage2());


    }

    @Override
    public int getItemCount() {
        return multiImage2List.size();
    }

    class MultiImage2ViewHolder extends RecyclerView.ViewHolder {
        ImageView image2;

        MultiImage2ViewHolder(View itemView) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image2);
        }
    }
}
