package com.example.naga0818;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Postscript extends AppCompatActivity {

    private Button btn_cancle, btn_save;
    private TextView tv_place, tv_view_result;
    private Button tv_date;
    private ImageView img_review;
    private EditText edt_review;
    private final int GET_GALLERY_IMAGE = 200;
    private static String IP_ADDRESS = "서버 Ip 주소";
    private static String TAG = "pyreviewup";
    private AlertDialog dialog;
    EditText mEditTextreview;
    TextView mTextViewResult;
    private ArrayList<Uri> imageListUri = new ArrayList<>();
    RecyclerView recyclerView;
    MultiImageAdapter adapter;
    private String value;

    private RequestQueue queue;
    private StringRequest stringRequest;

    private String[] intent_name_seq;
    private static String id;

    private ArrayAdapter<String> adapter1;
    private ArrayList<String> time_data = new ArrayList<String>();
    private int position;

    private String prog_seq;
    private String[] plan_data;

    private String server_url = MainActivity.getServerURL();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postscript);

        btn_cancle = findViewById(R.id.btn_cancle);
        btn_save = findViewById(R.id.btn_save);
        tv_place = findViewById(R.id.tv_place);
        tv_date = findViewById(R.id.tv_date);
        tv_view_result = findViewById(R.id.tv_view_result);
        img_review = findViewById(R.id.img_review);
        edt_review = findViewById(R.id.edt_review);
        mEditTextreview = (EditText) findViewById(R.id.edt_review);
        mTextViewResult = (TextView) findViewById(R.id.tv_view_result);
        mTextViewResult.setMovementMethod(new ScrollingMovementMethod());



        Intent intent1 = getIntent();

        String prog_name = intent1.getStringExtra("prog_name");
        prog_seq = intent1.getStringExtra("prog_seq");
        plan_data = intent1.getStringArrayExtra("plan_data");

        String result = prog_name + "/";
        result += prog_seq;

        Log.v("정보확인", result);

        intent_name_seq = result.split("/");

        // 프로그램명
        tv_place.setText(intent_name_seq[0]);


        // 상세페이지에서 프로그램 날짜 받아오기
        String time_text = "";
        for (int i = 0; i < plan_data.length; i++) {

            String[] tmp = plan_data[i].split(";");
            time_text += (tmp[2] + "\n");

        }
        tv_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeChoiceData.class);
                intent.putExtra("plan_data", plan_data);

                startActivityForResult(intent, 101);
            }
        });


//        tv_date.setText(time_text);



        // 달력 선택 날짜 표시
        Intent intent = getIntent();
        String reviewDay = intent.getStringExtra("checkDay");
 //       tv_date.setText(reviewDay);
        // 취소 버튼 클릭
        btn_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Postscript.InsertData task = new InsertData();
                // 대화상자 알림창
                AlertDialog.Builder builder = new AlertDialog.Builder(Postscript.this);
                dialog = builder.setMessage("리뷰작성이 취소되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
                dialog.show();
                return;
            }
        });
        // 확인 버튼 클릭
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sendRequest1();

                Postscript.InsertData task = new InsertData();
                // 대화상자 알림창
                AlertDialog.Builder builder = new AlertDialog.Builder(Postscript.this);
                dialog = builder.setMessage("리뷰작성이 완료되었습니다.").setNegativeButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).create();
                dialog.show();
                return;
            }
        });
        // 이미지 버튼 클릭 (갤러리 앱 실행)
        img_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                // 사진 여러장 선택 가능할 수 있도록 하는 기능
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setDataAndType(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent.createChooser(intent, "사진 최대 10장 선택 가능"), GET_GALLERY_IMAGE);
            }
        });
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 날짜 데이터 가져오기
        if(requestCode == 101 && resultCode == 102) {
            value = data.getStringExtra("value");
            position = data.getIntExtra("position", 0);
            Log.v("결과값", value);

            tv_date.setText(value);
        }


        // 리뷰 사3진 불러오기
        // 갤러리 화면에서 선택한 이미지를 비트맵으로 변환하고 뷰에 셋팅
        if (requestCode == GET_GALLERY_IMAGE && resultCode == RESULT_OK) {
            if (data == null) {            // 어떤 이미지도 선택하지 않은 경우
                Toast.makeText(getApplicationContext(), "이미지를 선택하지 않았습니다.", Toast.LENGTH_SHORT).show();
            } else {          // 이미지를 선택한 경우
                if (data.getClipData() == null) {           // 단일 선택
                    Log.e("single choice", String.valueOf(data.getData()));
                    Uri selectedImageUri = data.getData();
                    imageListUri.add(selectedImageUri);
                    Bitmap selPhoto = null;
                    // 리사이클러뷰와 연결
                    adapter = new MultiImageAdapter(imageListUri, getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
                    try {
                        selPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    BitMapToString(selPhoto);
                } else {            // 다중 선택
                    ClipData clipData = data.getClipData();
                    Log.e("clipData", String.valueOf(clipData.getItemCount()));
                    if (clipData.getItemCount() > 10) {
                        Toast.makeText(getApplicationContext(), "사진은 10장까지 선택 가능합니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("multiple choice", String.valueOf(clipData.getItemCount()));
                        for (int i = 0; i < clipData.getItemCount(); i++) {
                            Uri selectedImageUri = clipData.getItemAt(i).getUri();  // 선택한 이미지들의 uri를 가져온다.
                            try {
                                imageListUri.add(selectedImageUri);  //uri를 list에 담는다.
                                Bitmap selPhoto = null;
                                try {
                                    selPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                BitMapToString(selPhoto);
                            } catch (Exception e) {
                                Log.e(TAG, "File select error", e);
                            }
                        }
                        adapter = new MultiImageAdapter(imageListUri, getApplicationContext());
                        recyclerView.setAdapter(adapter);   // 리사이클러뷰에 어댑터 세팅
                        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));     // 리사이클러뷰 수평 스크롤 적용
                    }
                }
            }
        }
    }
    public void BitMapToString(Bitmap bitmap){          // BitMap  ->  ToString 변환
        ByteArrayOutputStream stream=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, stream);    //bitmap compress
        byte [] arr = stream.toByteArray();
        String image= Base64.encodeToString(arr, Base64.DEFAULT);
        try{
            String temp = URLEncoder.encode(image, "utf-8");
        }catch (Exception e){
            Log.e("exception",e.toString());
        }
    }


    // 데이터 비동기 실행 시스템, AsyncTask 실행(제네릭클래스타입< Params, Progress, Result>)
    public class InsertData extends AsyncTask<String, Void, String> {
        // ProgressDialog(프로그레스 다이얼로그) 구현
        ProgressDialog progressDialog;
        //        public InsertData(Context context) {
//            super();
//        }
        @Override     // 작업시작, ProgressDialog 객체를 생성하고 시작합니다. (UI 화면을 갱신 가능)
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(Postscript.this, "Please Wait", null, true, true);
        }
        @Override   // 종료, ProgressDialog 종료 기능을 구현합니다.
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            mTextViewResult.setText(result);
            Log.d(TAG, "POST response  - " + result);
            //mTextViewResult.setText("종료확인");
        }
        @Override     // 진행중, ProgressDialog 의 진행 정도를 표현해 줍니다.
        protected String doInBackground(String... strings) {
            return null;
        }
    }


    public void sendRequest1() {

        // Volley Lib 새로운 요청객체 생성
        queue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = server_url + "/reviewSearch";   // 이따 변겨어어ㅇㅇㅁㅈㄷㄹㄷ
        // 요청 문자열 저장
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                // 받아온 값
                String res = response;



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

                String[] tmp = plan_data[position].split(";");

                Log.v("제발확인좀", intent_name_seq[1]);

                params.put("user_id", id);
                params.put("prog_seq", prog_seq);
                params.put("plan_seq", (tmp[0]));
                params.put("content", edt_review.getText().toString());
                
                return params;
            }
        };
        String TAG = "black";
        stringRequest.setTag(TAG);
        queue.add(stringRequest);

    }

//    public static String getData() {
//        return value;
//    }

}
