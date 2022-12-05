package com.example.naga0818;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentA fragmentA;
    private FragmentB fragmentB;
    private FragmentC fragmentC;
    private FragmentD fragmentD;
    private FragmentManager fm;
    private BottomNavigationView navi;

    private static String serverURL = "http://192.168.43.120:5000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();
        fragmentD = new FragmentD();

        fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.frame, fragmentA).commit();

        navi = findViewById(R.id.navi);
        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {   // 클릭시 MenuItem item 으로 객체 들어옴, 아이디로 판별

                int selectId = item.getItemId();    // item의 아이디값을 가져온다

                if (selectId == R.id.page1) {
                    fm.beginTransaction().replace(R.id.frame, fragmentA).commit();
                } else if (selectId == R.id.page2) {
                    fm.beginTransaction().replace(R.id.frame, fragmentB).commit();
                } else if (selectId == R.id.page3) {
                    fm.beginTransaction().replace(R.id.frame, fragmentC).commit();
                } else if (selectId == R.id.page4) {
                    fm.beginTransaction().replace(R.id.frame, fragmentD).commit();
                }

                return true;
            }
        });






    }

    public static String getServerURL() {
        return serverURL;
    }






}