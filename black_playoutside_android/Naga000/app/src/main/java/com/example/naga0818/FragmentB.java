package com.example.naga0818;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FragmentB extends Fragment {
    Handler handler = new Handler();
    private Button btn_search, layout_btn_search;
    private ProgressBar btn_progress;
    private TextInputEditText search_term_edit_text;
    private TextInputLayout search_term_text_layout;
    private TextView textView, text, count;
    private ScrollView scrollView;
    private RecyclerView listview1;

    private android.widget.SpinnerAdapter SpinnerAdapter;
    private String[] items = {"집에", "가고", "시퍼요오"};
    final ArrayList<String> list = new ArrayList<>();

    // 고민
    private ListViewAdapter adapter;
    private ListViewAdapter listViewItem = new ListViewAdapter();
    ArrayList<String> list2 = new ArrayList<>();

    private RequestQueue queue;
    private StringRequest stringRequest;

    private String top_search_text;  // ,가 들어있는 상태 ㅇㅇ

    private ListViewAdapter adapter1;

    private String server_url = MainActivity.getServerURL();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_b, container, false);

        btn_search = view.findViewById(R.id.btn_search);
        btn_progress = view.findViewById(R.id.btn_progress);
        search_term_edit_text = view.findViewById(R.id.search_term_edit_text);
        search_term_text_layout = view.findViewById(R.id.search_term_text_layout);

        textView = view.findViewById(R.id.tv_list2_prog_name);
        scrollView = view.findViewById(R.id.scrollView);

        text = view.findViewById(R.id.text);
        count = view.findViewById(R.id.count);


        // 인기 검색어 띄우기
        sendRequest2();


        search_term_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 입력난에 변화가 있을 시 조치
                if (charSequence.length()>0){
                    scrollView.scrollTo(0, 350);
                    search_term_text_layout.setHelperText("검색어를 입력하세요.");
                    search_term_text_layout.setCounterMaxLength(12);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 입력이 끝났을 때 조치
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 입력하기 전에 조치
            }
        });


        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        SpinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, list);

//        final ScrollView scrollView = view.findViewById(R.id.scrollView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);



        listview1 = view.findViewById(R.id.listview1);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        listview1.setLayoutManager(layoutManager);

        adapter1 = new ListViewAdapter();

        // 리스트뷰 참조 및 adapter 달기
        Log.v("search_hot_text","1");

//        adapter1.addItem(new ListViewItem("1", "점핌"));
//        adapter1.addItem(new ListViewItem("2", "아이스파크"));
//        adapter1.addItem(new ListViewItem("3", "공원"));
//        adapter1.addItem(new ListViewItem("4", "물놀이"));
//        adapter1.addItem(new ListViewItem("5", "키즈카페"));
//        adapter1.addItem(new ListViewItem("6", "박물관"));
//        adapter1.addItem(new ListViewItem("7", "키즈다쿵"));

//        listview1.setAdapter(adapter1);



        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sear = search_term_edit_text.getText().toString();
                if (!sear.equals("")) {
                    list.add(sear);
                    if (list.size() == 6){
                        list.remove(0);
                    }
                    sendRequest1();
                }

                handleSearchButtonUi();
//                Toast.makeText(getActivity(), list.toString(), Toast.LENGTH_SHORT).show();

                //fragment 키보드 내리기
                InputMethodManager mInputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                mInputMethodManager.hideSoftInputFromWindow(search_term_edit_text.getWindowToken(), 0);

            }
        });

        return view;
    }


    public void handleSearchButtonUi(){
        btn_search.setText("");
        btn_progress.bringToFront();
//        btn_progress.setIndeterminate(false);
//        btn_progress.setProgress(80);
        btn_search.setVisibility(View.INVISIBLE);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btn_search.setVisibility(View.VISIBLE);
                btn_search.bringToFront();
                btn_search.setText("검색");
            }
        }, 1000);

    }


    public void sendRequest1() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소
        String url = server_url + "/search";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("prog_res",response);

                Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();
                if (response.equals("-1")) {
                    Toast.makeText(getActivity(), "없는 내용입니다. 다시 검색해 주세요~!", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getActivity(), List2_prog_seq.class);
                    intent.putExtra("prog_seq_string", response);
                    startActivity(intent);
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

                String search_word = search_term_edit_text.getText().toString();
                String id = Login.getId();
                params.put("id", id);
                params.put("search_word", search_word);

                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }



    public void sendRequest2() {
        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getActivity());
        // 서버에 요청할 주소
        String url1 = server_url + "/topSearch";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("topSearch",response);
                top_search_text = response;

                String[] topSearch = top_search_text.split(",");

                for (int i = 0; i < topSearch.length; i++) {
                    adapter1.addItem(new ListViewItem((i+1) + "", topSearch[i]));
                }

                listview1.setAdapter(adapter1);


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

                return params;
            }
        };

        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);
    }

}
