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
                    Toast.makeText(getApplicationContext(), "??? ????????????", Toast.LENGTH_SHORT).show();
                } else if (pw.equals(pw2)){
                    sendRequest();
                } else {
                    Toast.makeText(getApplicationContext(), "??????????????? ????????????", Toast.LENGTH_SHORT).show();
                }

                //fragment ????????? ?????????
                InputMethodManager mInputMethodManager = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(edt_join_pw_check.getWindowToken(), 0);
            }
        });
    }

    public void sendRequest() {

        // Volley Lib ????????? ???????????? ??????
        queue = Volley.newRequestQueue(this);
        // ????????? ????????? ??????
        String url = server_url + "/join";
        // ?????? ????????? ??????
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // ?????????????????? ???????????? ???
            @Override
            public void onResponse(String response) {
                if (response.equals("0")) {
                    Intent intent = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent);
                } else if (response.equals("1")) {
                    Toast.makeText(getApplicationContext(), "?????? ID??? ????????????", Toast.LENGTH_SHORT).show();
                } else if (response.equals("2")) {
                    Toast.makeText(getApplicationContext(), "?????? ???????????? ????????????", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener() {
            // ???????????? ?????? ????????? ??????
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response??? UTF8??? ??????????????? ????????????
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
            // ?????? ???????????? ???????????? ???
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
