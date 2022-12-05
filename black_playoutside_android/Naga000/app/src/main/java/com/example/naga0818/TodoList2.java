package com.example.naga0818;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TodoList2 extends AppCompatActivity {

    private Animation rotateOpen, rotateClose, toBottom, fromBottom;
    private FloatingActionButton fb_share, fb_delete, fb_add;
    private boolean clicked = false;
    private TextView tv_checkDay;
    private RecyclerView re_pscList;
    private PostscriptContentAdapter postscriptContentAdapter;
    ArrayList<PostscriptContent> postscriptContentList = new ArrayList<PostscriptContent>();
    private ArrayList<MultiImage2> multiImage2List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list_content);

        rotateOpen = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        rotateClose = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        toBottom = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);
        fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);

        tv_checkDay = findViewById(R.id.tv_checkDay);

        re_pscList = findViewById(R.id.re_pscList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(TodoList2.this);
        postscriptContentAdapter = new PostscriptContentAdapter((ArrayList<PostscriptContent>) buildPostscriptContentList());
        re_pscList.setAdapter(postscriptContentAdapter);
        re_pscList.setLayoutManager(layoutManager);

//        // 달력 선택날짜 표시
//        Intent intent = getIntent();
//        String name = intent.getStringExtra("checkDay");
//        tv_checkDay.setText(name);

        // 추가 버튼
        fb_add = findViewById(R.id.fb_add);
        fb_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddButtonClicked();
            }
        });
        // 공유 버튼
        fb_share = findViewById(R.id.fb_share);
        fb_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"공유하고 싶어요.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String sendMessage = "볼거리와 참여하여 배우는 학습내용이 많았어요.\n단순히 노는 것이 아니라 과학의 실험을 직접 보고 느끼고 체험할 수 있어서 좋았어요.\n 체험할 내용이 생각보다 많기 떄문에 시간을 넉넉히 잡고 봐야겠어요.\n 오후 늦게 갔더니 모두 체험하지 못하고 와서 조금 아쉬었어요.\n 그래도 내용이 알차고 잘 설명되어 있어서 유익한 관람이었어요.";
                intent.putExtra(Intent.EXTRA_TEXT, sendMessage);
                Intent shareIntent = Intent.createChooser(intent, "share");
                startActivity(shareIntent);
//                finish();
            }
        });
        // 삭제 버튼 -> 쓰기 장소로 이동
        fb_delete = findViewById(R.id.fb_delete);
        fb_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"삭제되었습니다. 작성지로 이동합니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),Postscript.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // 클릭 이벤트
    private void onAddButtonClicked() {
        setVisibility(clicked);
        setAnimation(clicked);
        clicked = !clicked;
    }
    // clicked 클릭 여부에 따른 보여짐, 사라짐
    private void setVisibility(boolean clicked) {
        if (!clicked) {
            fb_share.setVisibility(fb_share.VISIBLE);
            fb_delete.setVisibility(fb_delete.VISIBLE);
        } else {
            fb_share.setVisibility(fb_share.INVISIBLE);
            fb_delete.setVisibility(fb_delete.INVISIBLE);
        }
    }
    // clicked 클릭 여부에 따른 에니메이션 효과
    private void setAnimation(boolean clicked) {
        if (!clicked) {
            fb_share.startAnimation(fromBottom);
            fb_delete.startAnimation(fromBottom);
            fb_add.startAnimation(rotateOpen);
        } else {
            fb_share.startAnimation(toBottom);
            fb_delete.startAnimation(toBottom);
            fb_add.startAnimation(rotateClose);
        }
    }


    private List<PostscriptContent> buildPostscriptContentList() {
        List<PostscriptContent> postscriptContentList = new ArrayList<>();
        return postscriptContentList;
    }

    private ArrayList<MultiImage2> buildMultiImage2List() {
        ArrayList<MultiImage2> multiImage2List = new ArrayList<>();
        return multiImage2List;
    }
}