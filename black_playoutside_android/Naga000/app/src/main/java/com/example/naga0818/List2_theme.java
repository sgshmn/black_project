package com.example.naga0818;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class List2_theme extends AppCompatActivity {

    private RequestQueue queue;
    private StringRequest stringRequest;
    private String theme;
    private String[] prog_data_strings;

    private GridLayoutManager layoutManager;
    private List2Adapter adapter;
    private RecyclerView recyclerView;

    private String server_url = MainActivity.getServerURL();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2xml);

        recyclerView = findViewById(R.id.recyclerview2);
        layoutManager =
                new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new List2Adapter();

        Intent data = getIntent();
        theme = data.getStringExtra("theme");
        sendRequest1();

    }
    // prog_seqs 받아오기
    public void sendRequest1() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/progSieve";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("theme",response);

                // theme
                prog_data_strings = response.split("`");

                Log.d("prog_split", prog_data_strings[0]);

                adapter = new List2Adapter();

                for (int i = 0; i < prog_data_strings.length; i++) {
                    adapter.addItem(prog_data_strings[i]);
                }
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {

                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    return Response.error(new ParseError(e));
                }
            }
            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                params.put("theme", theme);
                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }


}