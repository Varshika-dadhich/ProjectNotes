package com.example.projectnotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class
Database extends SQLiteOpenHelper {
    public static final String database_name="NOTES";
    public static final String table_name="table1";
    public static final String col1_name="id";
    public static final String col2_name="Title";
    public static final String col3_name="Description";
public static final int database_version=2;

    public Database(Context context) {
        super(context,database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name +
                " (" + col1_name + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                col2_name + " TEXT, " +
                col3_name + " TEXT);";

        db.execSQL(query);

//        SQLiteDatabase.execSQL("CREATE TABLE "+table_name+ "("+col1_name+" INTEGER PRIMARY KEY AUTOINCREMENT,"+col2_name+ "TEXT,"+col3_name +"TEXT");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }
    public long addNote(Modelclass note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(col2_name,note.getTitle());
        v.put(col3_name,note.getDesc());

        // inserting data into db
        long ID = db.insert(table_name,null,v);
        return  ID;
    }

    public Modelclass getNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] query = new String[] {col1_name,col2_name,col3_name};
        Cursor cursor=  db.query(table_name,query,col1_name+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
int count=cursor.getCount();
        System.out.println("The count is "+count);
        return new Modelclass(

              Long.parseLong(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2));

    }

    public ArrayList<Modelclass> getAllNotes(){
        ArrayList<Modelclass> allNotes = new ArrayList<>();
        String query = "SELECT * FROM " + table_name+" ORDER BY "+col1_name+" DESC";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                Modelclass note = new Modelclass();
                note.setId(Long.parseLong(cursor.getString(0)));
                note.setTitle(cursor.getString(1));
               note.setDesc(cursor.getString(2));
                allNotes.add(note);
            }while (cursor.moveToNext());
        }

        return allNotes;

    }

    public int editNote(Modelclass note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues c = new ContentValues();
        Log.d("Edited", "Edited Title: -> "+ note.getTitle() + "\n ID -> "+note.getId());
        c.put(col2_name,note.getTitle());
        c.put(col3_name,note.getDesc());
        return db.update(table_name,c,col1_name+"=?",new String[]{String.valueOf(note.getId())});
    }



    void deleteNote(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_name,col1_name+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
