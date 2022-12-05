package com.example.naga0818;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

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

public class Join extends AppCompatActivity {
    private EditText edt_join_id, edt_join_nick, edt_join_pw, edt_join_pw_check;
    private Button btn_join;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private ScrollView join_scroll;

    private String id;
    private String pw;
    private String nick;

    private String server_url = MainActivity.getServerURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        edt_join_id = findViewById(R.id.edt_join_id);
        edt_join_nick = findViewById(R.id.edt_join_nick);
        edt_join_pw = findViewById(R.id.edt_join_pw);
        edt_join_pw_check = findViewById(R.id.edt_join_pw_check);
        btn_join = findViewById(R.id.btn_join);

        join_scroll = findViewById(R.id.join_scroll);


        edt_join_pw_check.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                join_scroll.scrollTo(0, 400);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id = edt_join_id.getText().toString();
                pw = edt_join_pw.getText().toString();
                String pw2 = edt_join_pw_check.getText().toString();
                nick = edt_join_nick.getText().toString();


                if (id.equals("") || pw.equals("") || nick.equals("")) {
                    Toast.makeText(getApplicationContext(), "다 써주세요", Toast.LENGTH_SHORT).show();
                } else if (pw.equals(pw2)){
                    sendRequest();
                } else {
                    Toast.makeText(getApplicationContext(), "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                }

                //fragment 키보드 내리기
                InputMethodManager mInputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(edt_join_pw_check.getWindowToken(), 0);
            }
        });
    }

    public void sendRequest() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(this);
        // 서버에 요청할 주소
        String url = server_url + "/join";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                if (response.equals("0")) {
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                } else if (response.equals("1")) {
                    Toast.makeText(getApplicationContext(), "다른 ID를 써주세요", Toast.LENGTH_SHORT).show();
                } else if (response.equals("2")) {
                    Toast.makeText(getApplicationContext(), "다른 닉네임을 써주세요", Toast.LENGTH_SHORT).show();
                }


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

                params.put("id",id);
                params.put("pw",pw);
                params.put("nick",nick);
                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }

}
