package com.example.admin.mydiary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewDiaryEntry extends AppCompatActivity {

    TextView date, subject, entry;
    String dateText, subjectText, entryText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_diary_entry);

        date = findViewById(R.id.date_T);
        entry = findViewById(R.id.diary_T);
        subject = findViewById(R.id.subject_T);

        dateText = getIntent().getExtras().getString("Date");
        subjectText = getIntent().getExtras().getString("Subject");
        entryText = getIntent().getExtras().getString("Entry");

        date.setText(dateText);
        subject.setText(subjectText);
        entry.setText(entryText);
    }
}
