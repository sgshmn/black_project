package com.example.naga0818;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainTopAdapter extends RecyclerView.Adapter<MainTopAdapter.ViewHolder>  {

    //ListView 에 보여줄 데이터 어댑터 안에 보관!
    private ArrayList<MainTopVo> items1 =  new ArrayList<MainTopVo>();

    private RequestQueue queue;
    private StringRequest stringRequest;

    private String[] prog_data;

    @NonNull
    @Override
    public MainTopAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.mainxmltop10, viewGroup, false);
        return new MainTopAdapter.ViewHolder(itemView);
        }

    public void addItem(MainTopVo item){
        this.items1.add(item);
    }

    public void setItems(ArrayList<MainTopVo> items) {
        this.items1 = items;
    }

    public MainTopVo getItem(int position){
        return items1.get(position);
    }

    public void setItem(int position, MainTopVo item) {
        items1.set(position, item);
    }

    @Override
    public void onBindViewHolder(@NonNull MainTopAdapter.ViewHolder viewHolder, int position) {
        MainTopVo item = items1.get(position);
        viewHolder.setItem(item);
    }
    @Override
    public int getItemCount() {
        return items1.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView1;
        TextView textView1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView1 = itemView.findViewById(R.id.main_top_img);
            textView1 = itemView.findViewById(R.id.main_top_title);

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String data = "";
                    int pos = getAdapterPosition();
     //               Toast.makeText(v.getContext(), "cilck : " + pos, Toast.LENGTH_SHORT).show();
                    if (pos != RecyclerView.NO_POSITION){
                        // cilck event
                    }

                    Intent intent = new Intent(v.getContext(), ListDetail.class);
                    v.getContext().startActivity(intent);

//                    if(pos == 0){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    }else if (pos == 1){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 2){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 3){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 4){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 5){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 6){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 7){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 8){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    } else if (pos == 9){
//                        Intent intent = new Intent(v.getContext(), ListDetail.class);
//                        v.getContext().startActivity(intent);
//                    }


                }
            });



        }
        public void setItem(MainTopVo item){
            textView1.setText(item.getName());
            imageView1.setImageDrawable(item.getImg());
        }
        public void setImage(int resid){
            imageView1.setImageResource(resid);
        }

    }

//    public void sendRequest() {
//
//        // Volley Lib 새로운 요청객체 생성
//  //      queue = Volley.newRequestQueue(getApplicationContext());
//        // 서버에 요청할 주소
//        String url = "http://192.168.21.62:5000/progSieve";
//        // 요청 문자열 저장
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            // 응답데이터를 받아오는 곳
//            @Override
//            public void onResponse(String response) {
//                Log.v("theme",response);
////
////                // theme
////                prog_data_strings = response.split("`");
////
////                Log.d("prog_split", prog_data_strings[0]);
////
////                adapter = new List2Adapter();
////
////                for (int i = 0; i < prog_data_strings.length; i++) {
////                    adapter.addItem(prog_data_strings[i]);
////                }
////                recyclerView.setAdapter(adapter);
//
//            }
//        }, new Response.ErrorListener() {
//            // 서버와의 연동 에러시 출력
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        }) {
//            @Override //response를 UTF8로 변경해주는 소스코드
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                try {
//                    String utf8String = new String(response.data, "UTF-8");
//                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
//                } catch (UnsupportedEncodingException e) {
//
//                    return Response.error(new ParseError(e));
//                } catch (Exception e) {
//                    return Response.error(new ParseError(e));
//                }
//            }
//            // 보낼 데이터를 저장하는 곳
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//
//                params.put("theme", theme);
//
//                return params;
//            }
//        };
//        String TAG = "black";
//        stringRequest.setTag(TAG);
//        queue.add(stringRequest);
//    }
//

}
