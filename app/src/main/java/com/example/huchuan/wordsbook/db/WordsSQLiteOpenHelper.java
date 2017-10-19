package com.example.huchuan.wordsbook.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by huchuan on 2017/10/18.
 */

public class WordsSQLiteOpenHelper extends SQLiteOpenHelper {

    private String CREATE_WORDS = "create table Words(id Integer primary key autoincrement," +
            "word text,BrE text,BrEmp3 text,AmE text,AmEmp3 text,defs text,sams text)";

    public WordsSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_WORDS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}