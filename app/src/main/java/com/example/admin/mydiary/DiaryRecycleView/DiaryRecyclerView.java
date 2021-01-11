package com.example.admin.mydiary.DiaryRecycleView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.mydiary.DatabaseHelper;
import com.example.admin.mydiary.R;
import com.example.admin.mydiary.WriteDiary;
import com.example.admin.mydiary.models.DiaryModel;

import java.util.ArrayList;

public class DiaryRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DiaryAdapter myAdapter;
    private ArrayList<DiaryModel> arrayList = new ArrayList<>();
    private FloatingActionButton fab;
    private DatabaseHelper db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_recycler_view);

        db = new DatabaseHelper(this);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.addAll(db.getalldiaryentries());
        myAdapter = new DiaryAdapter(arrayList, this);
        recyclerView.setAdapter(myAdapter);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiaryRecyclerView.this, WriteDiary.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
