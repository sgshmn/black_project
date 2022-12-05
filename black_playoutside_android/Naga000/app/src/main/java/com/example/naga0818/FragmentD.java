package com.example.naga0818;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;

public class FragmentD extends Fragment implements View.OnClickListener{

    Button btn1, btn2, btn3, btn4;
    ImageView cv_profile;
    private String server_url = MainActivity.getServerURL();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_d, container, false);

        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        btn4 = view.findViewById(R.id.btn4);

        btn1.setOnClickListener(this); // 고객센터
        btn2.setOnClickListener(this); // 공지사항
        btn3.setOnClickListener(this); // 환경설정
        btn4.setOnClickListener(this); // 문의사항

        cv_profile = view.findViewById(R.id.cv_profile);

        Glide.with(view.getContext())
                .load(server_url + "/static/profile/" + Login.getId() + "/fix.png")
                .apply(new RequestOptions()
                        .signature(new ObjectKey("signature string"))
                        .skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                )
                .into(cv_profile);

        cv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangeProfile.class);
                startActivityForResult(intent, 103);



            }
        });






        return view;
    }

    @Override
    public void onClick(View view) {


        Button btn = (Button) view;
   //     Toast.makeText(getActivity(), btn.getText().toString(), Toast.LENGTH_SHORT).show();


        if (view.getId() == R.id.btn4){
            Intent intent = new Intent(getActivity(), List1.class);
            startActivity(intent);
        }else{
            showMesssage();
        }

    }


    void showMesssage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("안내").setMessage("구현중 입니다^^");
        builder.setIcon(R.drawable.img_punch);


        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "예";
            }
        });

        builder.setNeutralButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "취소";
            }
        });

        builder.setNegativeButton("오케이", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = "아니오";
            }
        });

        AlertDialog alterDialog = builder.create();

        alterDialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 103 && resultCode == 104){
            Glide.with(getContext())
                    .load(server_url + "/static/profile/" + Login.getId() + "/fix.png")
                    .apply(new RequestOptions()
                            .signature(new ObjectKey("signature string"))
                            .skipMemoryCache(true)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                    )
                    .into(cv_profile);



        }
    }







}