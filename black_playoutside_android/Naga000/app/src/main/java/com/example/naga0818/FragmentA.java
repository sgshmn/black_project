package com.example.naga0818;

import static android.media.CamcorderProfile.get;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class FragmentA extends Fragment {

    private RecyclerView recyclerView, recyclerView1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_a, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview00);
        GridLayoutManager layoutManager =
                new GridLayoutManager(getActivity(), 4);
        recyclerView.setLayoutManager(layoutManager);

        MaintemAdapter adapter = new MaintemAdapter();

        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem1),"전시/문화")); // exhibition
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem2),"동물탐험대")); // animal
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem3),"농촌/자연")); // nature
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem4),"쿠킹/베이킹")); // cook
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem5),"스포츠")); // sport
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem6),"도예/공방")); // handmade
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem7),"체험/관람")); // experience
        adapter.addItem(new maintemvo(ContextCompat.getDrawable(getActivity(), R.drawable.tem8),"테마파크")); // park

        recyclerView.setAdapter(adapter);


        // TOP 10
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview01);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        MainTopAdapter adapter1 = new MainTopAdapter();

        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak1),"쿠키야 놀자"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak2),"플레이 플로어볼 나이스샷~!"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak8),"북구청소년어울림마당 6회"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak7),"광산구청소년어울림마당 4회"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak5),"행복 크레센도(중학교 2일)"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak6),"청소년카페 매니저 체험프로그램"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak4),"와글와글 인성놀이터(초등학교 당일)"));
        adapter1.addItem(new MainTopVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak3),"밤 하늘로의 여행(중학교)"));


        recyclerView1.setAdapter(adapter1);


        // CH 10
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recyclerview02);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        MainCHAdapter adapter2 = new MainCHAdapter();

        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak5),"행복 크레센도(중학교 2일)"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak6),"청소년카페 매니저 체험프로그램"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak7),"광산구청소년어울림마당 4회"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak8),"북구청소년어울림마당 6회"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak1),"쿠키야 놀자"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak2),"플레이 플로어볼"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak3),"밤 하늘로의 여행(중학교)"));
        adapter2.addItem(new MainCheHumVo(ContextCompat.getDrawable(getActivity(), R.drawable.hak4),"와글와글 인성놀이터(초등학교 당일)"));

        recyclerView1.setAdapter(adapter2);


        return view;
    }

}
