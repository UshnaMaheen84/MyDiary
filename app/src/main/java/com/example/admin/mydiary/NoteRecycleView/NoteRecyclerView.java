package com.example.admin.mydiary.NoteRecycleView;

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
import com.example.admin.mydiary.WriteNote;
import com.example.admin.mydiary.models.mNotes;

import java.util.ArrayList;

public class NoteRecyclerView extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NoteAdapter myAdapter;
    private ArrayList<mNotes> arrayList = new ArrayList<>();
    private FloatingActionButton fab;
    private String note;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_recycler_view);

        db = new DatabaseHelper(this);


        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList.addAll(db.getallnotes());
        myAdapter = new NoteAdapter(arrayList, this);
        recyclerView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteRecyclerView.this, WriteNote.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
