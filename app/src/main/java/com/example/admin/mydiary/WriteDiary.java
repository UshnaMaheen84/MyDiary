package com.example.admin.mydiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.mydiary.DiaryRecycleView.DiaryRecyclerView;
import com.example.admin.mydiary.models.DiaryModel;

import java.util.ArrayList;

public class WriteDiary extends AppCompatActivity {

    EditText date, subject, entry;
    Button saveBtn;
    private DatabaseHelper db;
    private ArrayList<DiaryModel> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_diary);

        db = new DatabaseHelper(this);

        date = (EditText) findViewById(R.id.date_ET);
        subject = (EditText) findViewById(R.id.subject_ET);
        entry = (EditText) findViewById(R.id.diary_ET);
        saveBtn = (Button) findViewById(R.id.save_btn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dateText, subjectText, entryText;

                dateText = date.getText().toString();
                subjectText = subject.getText().toString();
                entryText = entry.getText().toString();

                if (dateText.isEmpty() && entryText.isEmpty()) {
                    Toast.makeText(WriteDiary.this, "Please write your diary and date first", Toast.LENGTH_SHORT).show();
                } else {
                    long id = db.insertDiaryEntry(entryText, dateText, subjectText);
                    if (id != -1) {
                        DiaryModel model = db.getDiaryEntry(id);
                        arrayList.add(0, model);
                    }

                }

                Intent intent = new Intent(WriteDiary.this, DiaryRecyclerView.class);
                startActivity(intent);
                finish();

            }
        });
    }
}
