package com.example.sqliteandroidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void insertDataToDb() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", "John Doe");

        long id = db.insert("MyTable", null, values);
        db.close();

        Log.d("MainActivity", "Inserted row with id: " + id);
    }

    private void readDatatFromDB() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {"ID", "Name"};
        Cursor cursor = db.query("MyTable", projection, null, null, null, null, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("ID"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("Name"));
            Log.d("MainActivity", "ID: " + id + " Name: " + name);
        }

        cursor.close();
        db.close();
    }
    private void updateDataDB(){
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", "Jane Doe");

        int count = db.update("MyTable", values, "ID = ?", new String[] { "1" });
        db.close();

        Log.d("MainActivity", "Updated " + count + " rows");
    }
    private void deleteDataDB(){
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int count = db.delete("MyTable", "ID = ?", new String[] { "1" });
        db.close();

        Log.d("MainActivity", "Deleted " + count + " rows");
    }

}