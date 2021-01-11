package com.example.admin.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.admin.mydiary.models.DiaryModel;
import com.example.admin.mydiary.models.mNotes;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "MyDiary_db";

    public static final String TABLE_NOTE = "notes";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOTE = "note";

    // Create table SQL query
    public static final String CREATE_TABLE_NOTE =
            "CREATE TABLE " + TABLE_NOTE + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NOTE + " TEXT"
                    + ")";


    public static final String TABLE_DIARY = "diary_table";
    public static final String COLUMN_ID_DIARY = "d_id";
    public static final String COLUMN_DIARY_ENTRY = "d_entry";
    public static final String COLUMN_DIARY_SUBJECT = "d_subject";
    public static final String COLUMN_DIARY_DATE = "d_date";

    public static final String CREATE_TABLE_DIARY =
            "CREATE TABLE " + TABLE_DIARY + "("
                    + COLUMN_ID_DIARY + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DIARY_ENTRY + " TEXT, " + COLUMN_DIARY_SUBJECT + " TEXT, " + COLUMN_DIARY_DATE + " TEXT"
                    + ")";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_User_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";

    public static final String CREATE_TABLE_USERS =
            "CREATE TABLE " + TABLE_USERS + "("
                    + COLUMN_User_ID + " TEXT,"
                    + COLUMN_NAME+ " TEXT,"
                    + COLUMN_EMAIL + " TEXT"
                    + ")";


    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_NOTE);
        db.execSQL(CREATE_TABLE_DIARY);
        db.execSQL(CREATE_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DIARY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);

        // Create tables again
        onCreate(db);
    }

    public long insertNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE, note);

        // insert row
        long id = db.insert(TABLE_NOTE, null, values);
        db.close();
        return id;
    }

    public List<mNotes> getallnotes() {
        List<mNotes> notes = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                mNotes note = new mNotes();
                note.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                note.setNote(cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
                notes.add(note);
            } while (cursor.moveToNext());
        }

        // close db connection
        cursor.close();
        return notes;
    }

    public void removeNote(String noteId) {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete()

        String Query = String.format("DELETE FROM notes WHERE id='%s'", noteId);
        db.execSQL(Query);

    }

    public mNotes getNote(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select id, note from notes where id=" + String.valueOf(id);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        mNotes note = new mNotes(
                cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(COLUMN_NOTE)));
        // close the db connection
        cursor.close();
        return note;
    }

    public long insertDiaryEntry(String entry, String date, String subject) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_DIARY_ENTRY, entry);
        values.put(COLUMN_DIARY_SUBJECT, subject);
        values.put(COLUMN_DIARY_DATE, date);
        // insert row
        long id = db.insert(TABLE_DIARY, null, values);
        db.close();
        return id;
    }

    public List<DiaryModel> getalldiaryentries() {
        List<DiaryModel> model = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_DIARY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list

        if (cursor.moveToFirst()) {
            do {
                DiaryModel dmodel = new DiaryModel();
                dmodel.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID_DIARY)));
                dmodel.setEntry(cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_ENTRY)));
                dmodel.setSubject(cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_SUBJECT)));
                dmodel.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_DATE)));
                model.add(dmodel);
            } while (cursor.moveToNext());
        }

        if(model.size() > 0 ){
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("DETAILS");
            for(DiaryModel d : model){
                ref.push().setValue(d);
            }
        }
        // close db connection
        cursor.close();
        return model;
    }

    public void removeDiaryEntry(String EntryId) {

        SQLiteDatabase db = this.getWritableDatabase();
        //db.delete()

        String Query = String.format("DELETE FROM diary_table WHERE d_id='%s'", EntryId);
        db.execSQL(Query);

    }

    public DiaryModel getDiaryEntry(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "Select d_id, d_entry, d_subject, d_date from diary_table where d_id=" + String.valueOf(id);
        Cursor cursor = db.rawQuery(query, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        DiaryModel note = new DiaryModel(
                cursor.getString(cursor.getColumnIndex(COLUMN_ID_DIARY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_ENTRY)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_SUBJECT)),
                cursor.getString(cursor.getColumnIndex(COLUMN_DIARY_DATE)));
        // close the db connection
        cursor.close();
        return note;
    }


}
