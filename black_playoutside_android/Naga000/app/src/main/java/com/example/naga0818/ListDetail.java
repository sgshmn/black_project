package com.example.naga0818;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
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
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ListDetail extends AppCompatActivity implements OnMapReadyCallback  {

    private MapView mapView;
    private static NaverMap naverMap;
    // 위도경도 표시
    private LatLng myLatLng = new LatLng( 35.604367, 127.283743);

    private TextView[] textViews = new TextView[20];
    private NestedScrollView scroll;
    private ImageView img_food, img_park, img_rent;
    private ImageView imgbtn_likes, imgbtn_jjim, btn_maps, btn_notice, btn_review;
    private TextView tv_locationName, tv_addr, tv_jjim, tv_re_infotext, tv_time_text, tv_price_text, tv_useroj_text, tv_detail_text;
    AlarmManager alarmManager;

    private WebView img_profile, img_main;
    private RecyclerView recyclerView;
    private ReviewAdapter adapter;
    private WebSettings webSettings;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private String[] prog_data;

    private Boolean like_cnt = true;
    private Boolean jjim_cnt = true;
    private Boolean notice_cnt = true;

    private String[] plan_data, temp;
    private String[] res_time_text;
    private String id;

    private String server_url = MainActivity.getServerURL();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_detail);

        Log.d("여긴 왔냐?", "어?");
        // 프로그램 정보 list2 받아오기
        Intent intent = getIntent();
        String prog_data_string = intent.getStringExtra("prog_data_string");
        prog_data = prog_data_string.split(";");



        mapView = (MapView) findViewById(R.id.mv);

        scroll = findViewById(R.id.scroll);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        img_food = findViewById(R.id.img_food);
        img_main = findViewById(R.id.img_main);
        imgbtn_jjim = findViewById(R.id.imgbtn_jjim);
        imgbtn_likes = findViewById(R.id.imgbtn_likes);

        img_park = findViewById(R.id.img_park);
        img_rent = findViewById(R.id.img_rent);
        img_profile = findViewById(R.id.img_profile);
        btn_notice = findViewById(R.id.btn_notice);
        btn_review = findViewById(R.id.btn_review);
        btn_maps = findViewById(R.id.btn_maps);
        tv_jjim = findViewById(R.id.tv_jjim);

        imgbtn_jjim.setImageResource(R.drawable.loveline);


        // prog_photo
        img_main.setWebViewClient(new WebViewClient());
        webSettings = img_main.getSettings();

        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);

        img_main.setHorizontalScrollBarEnabled(false);
        img_main.setVerticalScrollBarEnabled(false);

        img_main.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
       img_main.loadUrl(server_url + "/static/prog_photo/" + prog_data[0] + "/original.png");


        // 프로그램 이름
        tv_locationName = findViewById(R.id.tv_locationName);
        tv_locationName.setText(prog_data[1]);



        // 프로그램 주소
        tv_addr = findViewById(R.id.tv_addr);
        tv_addr.setText(prog_data[3]);

        // 프로그램 예약 정보
        tv_re_infotext = findViewById(R.id.tv_re_infotext);
        tv_re_infotext.setText(prog_data[8]);

        // 상세 홈페이지 연결
        tv_re_infotext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(prog_data[8]));
                startActivity(intent);
            }
        });



        // 프로그램 이용 정보
        //운영시간
        tv_time_text =findViewById(R.id.tv_time_text);
        sendRequest4();



        //이용요금
        tv_price_text = findViewById(R.id.tv_price_text);
        if (prog_data[4].equals("0") && prog_data[5].equals("0")){
            tv_price_text.setText("무료");
        }else if (prog_data[4] != null && prog_data[5].equals("0")){
            tv_price_text.setText(prog_data[4] + "원" + "~");
        }else if (prog_data[4].equals("0") && prog_data[5] != null){
            tv_price_text.setText("~" + prog_data[5] + "원");
        }else {
            tv_price_text.setText(prog_data[4] + "원" + " ~ " + prog_data[5] + "원");
        }

        //이용대상
        tv_useroj_text = findViewById(R.id.tv_useroj_text);
        if (prog_data[6].equals("0") && prog_data[7].equals("99")){
            tv_useroj_text.setText("전 연령");
        }else if (prog_data[6] != null && prog_data[7] == null){
            tv_useroj_text.setText(prog_data[6] + "~");
        }else if (prog_data[6] == null && prog_data[7] != null){
            tv_useroj_text.setText("~" + prog_data[7]);
        }else {
            tv_useroj_text.setText(prog_data[6] + " ~ " + prog_data[7]);
        }


        // 프로그램 편의정보


        // 프로그램 지도



        // 상세 페이지 리뷰 사진
        sendRequest1();

        // 지도 버튼
        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollToView(mapView, scroll, 0);
            }
        });

        // 리뷰 버튼
        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent1 = new Intent(view.getContext(), Postscript.class);
                intent1.putExtra("prog_seq", prog_data[0]);
                intent1.putExtra("prog_name", tv_locationName.getText().toString());
                intent1.putExtra("plan_data", plan_data);
                Log.v("intent_post_temp", "여기실행?");

                startActivity(intent1);

            }
        });

        // 예약 알림, 플랜에서 날짜 값
        imgbtn_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (like_cnt) {
                    imgbtn_likes.setImageResource(R.drawable.gstar);
                    like_cnt = false;
                } else {
                    imgbtn_likes.setImageResource(R.drawable.star);
                    like_cnt = true;
                }

            }
        });


        sendRequest3();
        Log.d("request3", "체크");

        imgbtn_jjim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                sendRequest2();
                Log.d("request2", "체크");
                if (jjim_cnt) {
                    imgbtn_jjim.setImageResource(R.drawable.lovered);
                    jjim_cnt = false;
                } else {
                    imgbtn_jjim.setImageResource(R.drawable.loveline);
                    jjim_cnt = true;
                }
            }
        });

        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);




    }

    // 알림 설정
    int Year, Month, Day;
    int Hour, Min;


    // 알람 선택
    public void clickBtn4(View view) {

        if (notice_cnt) {
            btn_notice.setImageResource(R.drawable.ybell);
            // 날짜선택 다이얼로그 보이기
            GregorianCalendar calendar = new GregorianCalendar(Locale.KOREA);
            DatePickerDialog dialog = new DatePickerDialog(this, onDateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            dialog.show();
            notice_cnt = false;

        } else {
            btn_notice.setImageResource(R.drawable.bell);
            notice_cnt = true;
            cancelAlarm();
        }
    }
    //알람 취소
    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getApplicationContext(), FragmentC.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
        alarmManager.cancel(pendingIntent);
        Toast.makeText(ListDetail.this, "알람이 취소되었습니다.", Toast.LENGTH_SHORT).show();
    }

    //날짜선택 리스너
    DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Toast.makeText(ListDetail.this, year + "," + (month + 1) + "," + day + "", Toast.LENGTH_SHORT).show();
            //선택한 날짜 저장
            Year = year;
            Month = month;
            Day = day;
            //시간 선택 다이얼로그 보이기
            GregorianCalendar calendar = new GregorianCalendar(Locale.KOREA);
            TimePickerDialog dialog = new TimePickerDialog(ListDetail.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            dialog.show();
        }
    };
    //시간 선택 리스너
    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int hour, int minute) {
            Toast.makeText(ListDetail.this, hour + ":" + minute, Toast.LENGTH_SHORT).show();
            Hour = hour;
            Min = minute;
            //선택한 날짜와 시간으로 알람 설정
            GregorianCalendar calendar = new GregorianCalendar(Year, Month, Day, Hour, Min);
            // 알림설정
            startAlarm(calendar);
        }
        // 알람 시작
        private void startAlarm(Calendar calendar) {
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getApplicationContext(), AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, 0);
            if (calendar.before(Calendar.getInstance())) {
                calendar.add(Calendar.DATE, 1);
            }
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        }
    };

    // 지도
    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;
        CameraPosition cameraPosition = new CameraPosition(myLatLng, 17);
        //배경 지도 선택
        naverMap.setMapType(NaverMap.MapType.Basic);

        naverMap.setCameraPosition(cameraPosition);
        Marker marker = new Marker();
        // 마커 표시
        marker.setPosition(new LatLng(35.604367, 127.283743));
        // 맵 표시
        marker.setMap(naverMap);
        // 너비
        marker.setWidth(Marker.SIZE_AUTO);
        // 높이
        marker.setHeight(Marker.SIZE_AUTO);
        // 내부 지도
        naverMap.setIndoorEnabled(true);

        naverMap.setOnMapClickListener(new NaverMap.OnMapClickListener() {
            @Override
            public void onMapClick(@NonNull PointF pointF, @NonNull LatLng latLng) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.kr/maps/place/%EC%A0%84%EB%B6%81119%EC%95%88%EC%A0%84%EC%B2%B4%ED%97%98%EA%B4%80/data=!4m5!3m4!1s0x35702a110a5eeebb:0x566eb26d9a76c6ba!8m2!3d35.604112!4d127.2837351"));
                startActivity(intent);
            }
        });
    }

    public static void scrollToView(View view, final NestedScrollView scrollView, int count){
        if (view != null && view != scrollView) {
            count += view.getTop();
            scrollToView((View) view.getParent(), scrollView, count);
        } else if (scrollView != null) {
            final int finalCount = count;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    scrollView.smoothScrollTo(0, finalCount);
                }
            }, 200);
        }
    }


    public void sendRequest1() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/reviewSearch";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                String review_data_string = response;
                String[] review_data = review_data_string.split("`");

                recyclerView = (RecyclerView) findViewById(R.id.recyclerview05);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false));

                adapter = new ReviewAdapter();

                for (int i = 0; i < review_data.length; i++) {
                    adapter.addItem(review_data[i]);
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

                params.put("prog_seq", prog_data[0]);
                Log.d("로그2보기", "dddd");
                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }


    // like 클릭이벤트
    public void sendRequest2() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/like";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {

                String res = response;
                // 0 있음, -1 없음
                if (res.equals("0")){
                    imgbtn_jjim.setImageResource(R.drawable.lovered);
                } else {
                    imgbtn_jjim.setImageResource(R.drawable.loveline);
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

                id = Login.getId();
                params.put("user_id", id);
                params.put("prog_seq", prog_data[0]);

                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }

    // like 있냐?
    public void sendRequest3() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/islike";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {

                String res = response;
                // 0 있음, -1 없음
                if (res.equals("0")){
                    imgbtn_jjim.setImageResource(R.drawable.lovered);
                } else {
                    imgbtn_jjim.setImageResource(R.drawable.loveline);
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

                id = Login.getId();
                params.put("user_id", id);
                params.put("prog_seq", prog_data[0]);

                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }





    // prog_seg관련 porg_plan_data
    public void sendRequest4() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/nowPlanSearch";
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {

                String res = response;
                Log.v("plan_data1", res);
                plan_data = res.split("`");
                Log.v("plan_data2", plan_data[0]);

                String time_text = "";
                for (int i = 0; i < plan_data.length; i++) {

                    String[] tmp = plan_data[i].split(";");
                    time_text += ("하는날\t: " + tmp[2] + "\n" + "시작일\t: " + tmp[3] + "\n" + "마감일\t: " + tmp[4] + "\n\n");

                }
                tv_time_text.setText(time_text);


//                String time_text_book = "";
//                for (int i = 0; i < plan_data.length; i++) {
//
//                    String[] tmp = plan_data[i].split(";");
//                    time_text_book += (tmp[2] + "/");
//
//                }
//                res_time_text = time_text_book.split("/");



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

                params.put("prog_seq", prog_data[0]);

                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }

}
