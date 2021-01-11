package com.example.admin.mydiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.mydiary.NoteRecycleView.NoteRecyclerView;
import com.example.admin.mydiary.models.mNotes;

import java.util.ArrayList;

public class WriteNote extends AppCompatActivity {

    private DatabaseHelper db;
    private ArrayList<mNotes> arrayList = new ArrayList<>();
    EditText noteEditText;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_note);

        db = new DatabaseHelper(this);

        noteEditText=(EditText)findViewById(R.id.note_ET);
        save = (Button)findViewById(R.id.save_btn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getting the text and insert into the database
                String noteText = noteEditText.getText().toString();

                if (noteText.isEmpty()) {
                    Toast.makeText(WriteNote.this, "Please write your note first", Toast.LENGTH_SHORT).show();
                } else {

                    long id = db.insertNote(noteText);
                    if (id != -1) {
                        Toast.makeText(WriteNote.this, "Note Saved !!!", Toast.LENGTH_SHORT).show();
                        mNotes note = db.getNote(id);
                        arrayList.add(0, note);
                    }

                    Intent intent = new Intent(WriteNote.this, NoteRecyclerView.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }
}
