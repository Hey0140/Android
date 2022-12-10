package com.example.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.StringTokenizer;
import androidx.annotation.Nullable;

public class vocaDataBaseHelper extends SQLiteOpenHelper {
    public static final int vocaDataBase_Version = 1;
    public static final String dataBaseName = "vocaDB";
    public static final String vocaName = "vocaName";
    public static final String wordLang = "wordLang";
    public static final String meanLang = "meanLang";
    public static final String date = "vocaDate";


    public vocaDataBaseHelper(Context context) {
        super(context, dataBaseName, null, vocaDataBase_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String vocaSQL = "create table vocaDB (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                vocaName + ","+ wordLang + "," +meanLang+ ","+ date + ")";
        sqLiteDatabase.execSQL(vocaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_version, int new_version) {
        if(new_version == vocaDataBase_Version){
            sqLiteDatabase.execSQL("drop table if exists vocaDB");
            onCreate(sqLiteDatabase);
        }
    }


    public void addVoca(String vocaName, String wordLang, String meanLang, Date date){
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        String da = formatter.format(date);
        db.execSQL("INSERT INTO vocaDB  (vocaName, wordLang, meanLang ,vocaDate) values (?,?,?,?)", new String[]{vocaName, wordLang, meanLang, da});

        Log.i("add", "success");
        db.close();
    }

    public void updateVoca(int id, String vocaName, String wordLang,
                             String meanLang, Date date) {
        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy/MM/dd");
        String da = formatter.format(date);

        db.execSQL("UPDATE vocaDB " +
                "SET vocaName = '" + vocaName +
                "', wordLang = '"+ wordLang +
                "', meanLang = '" + meanLang+
                "', vocaDate = '" + da + "' Where _id = " + id);
        Log.i("update", "success");
        db.close();
    }

    public void deleteVoca(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from vocaDB Where _id = " + id + ";");
        db.close();
        Log.i("SQLite" , "삭제");
    }


    public LinkedList<MyVocabularyItem> showVoca(){
        LinkedList<MyVocabularyItem> vocalist = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select *" +
                "from vocaDB order by _id asc", null);
        while(c.moveToNext()){
            int id = c.getInt(0);
            String vocabularyName = c.getString(1);
            String word = c.getString(2);
            String wordMean = c.getString(3);

            vocalist.addLast(new MyVocabularyItem(vocabularyName, word, wordMean, id));
        }
        Log.i("read", "success");
        db.close();

        return vocalist;
    }
    public int showId(String vocaName, String wordLang, String meanLang){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * From vocaDB" +
                " Where vocaName = '" + vocaName +
                "'and wordLang = '"+ wordLang +
                "'and meanLang = '" + meanLang+"'", null);
        if(c.moveToNext()){
            int id = c.getInt(0);
            Log.i("showId", Integer.toString(id));
            db.close();
            return id;
        }
        else{
            db.close();
            return -1;
        }
    }

    public String showName(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * From vocaDB" +
                " Where _id = "+ id, null);
        if(c.moveToNext()){
            String name = c.getString(1);
            Log.i("showName", name);
            db.close();
            return name;
        }
        else{
            db.close();
            return "";
        }
    }
}

