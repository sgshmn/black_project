package com.example.naga0818;



import android.app.Person;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class List1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list1xml);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        List1Adapter adapter = new List1Adapter();

        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img_punch),"강성관","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img_punch),"d","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img_punch),"강dsa성관","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img7),"dsa","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img7),"강d성관","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img7),"dsadsa","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img7),"dsadsa","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img7),"dsadsa","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img6),"dsadsa","010-0001-1111"));
        adapter.addItem(new List1vo(ContextCompat.getDrawable(this,R.drawable.img6),"dsadsa","010-0001-1111"));

        recyclerView.setAdapter(adapter);


    }
}