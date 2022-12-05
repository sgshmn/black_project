package com.example.naga0818;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PostscriptContentAdapter extends RecyclerView.Adapter<PostscriptContentAdapter.PostscriptContentViewHolder> {

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private List<PostscriptContent> postscriptContentList;
    PostscriptContentAdapter(List<PostscriptContent> postscriptContentList) {
        this.postscriptContentList = postscriptContentList;
    }

    @NonNull
    @Override
    public PostscriptContentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postscript_content, viewGroup, false);
        return new PostscriptContentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostscriptContentViewHolder postscriptContentViewHolder, int i) {
        PostscriptContent postscriptContent = postscriptContentList.get(i);
        postscriptContentViewHolder.tv_pName.setText(postscriptContent.getpName());
        postscriptContentViewHolder.tv_content.setText(postscriptContent.getpContent());

//        holder.btn_pDetail = view.findViewById(R.id.btn_pDetail);
        // 클릭 이벤트 설정
//        holder.btn_pDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(holder.itemView.getContext(), "상세페이지로 이동합니다.", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(holder.itemView.getContext(), Reserve_TimePicker.class);
//                holder.itemView.getContext().startActivity(intent);
//            }
//        });
        // 자식 레이아웃 매니저 설정
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                postscriptContentViewHolder.re_img.getContext(),
                LinearLayoutManager.HORIZONTAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(postscriptContent.getMultiImage2List().size());

        // 하위 어답터 설정
        MultiImageAdapter2 multiImageAdapter2 = new MultiImageAdapter2(postscriptContent.getMultiImage2List());

        postscriptContentViewHolder.re_img.setLayoutManager(layoutManager);
        postscriptContentViewHolder.re_img.setAdapter(multiImageAdapter2);
        postscriptContentViewHolder.re_img.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return postscriptContentList.size();
    }

    class PostscriptContentViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_pName;
        private TextView tv_content;
        private RecyclerView re_img;
        private Button btn_pDetail;

        PostscriptContentViewHolder(View itemView) {
            super(itemView);
            // 상위타이틀
            tv_pName = itemView.findViewById(R.id.tv_pName);
            tv_content = itemView.findViewById(R.id.tv_content);
            btn_pDetail = itemView.findViewById(R.id.btn_pDetail);
            // 하위아이템 영역
            re_img = itemView.findViewById(R.id.re_img);
        }
    }
}
