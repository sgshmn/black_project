package com.example.naga0818;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class TimeChoiceData extends AppCompatActivity {

    private ListView timeChoData;
    private Button btn_timechodate;
    private ArrayAdapter<String> adapter;
    String value;
    private ArrayList<String> time_date = new ArrayList<String>();
    private String[] plan_data;
    private int posi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_choice_data);

        btn_timechodate = findViewById(R.id.btn_timechodate);
        timeChoData = findViewById(R.id.timeChoData);

        Intent intent = getIntent();
        plan_data = intent.getStringArrayExtra("plan_data");

        for (int i = 0; i < plan_data.length; i++) {

            String[] tmp = plan_data[i].split(";");
            time_date.add(tmp[2]);

        }

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, time_date);

        timeChoData.setAdapter(adapter);

        timeChoData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                value = time_date.get(position);
                posi = position;
            }
        });


        btn_timechodate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();

                intent.putExtra("value", value);
//                String[] tmp = plan_data[posi].split(";");
                intent.putExtra("position", posi);


                setResult(102, intent);
                finish();
            }
        });







    }
}