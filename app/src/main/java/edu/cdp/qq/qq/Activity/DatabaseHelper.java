package edu.cdp.qq.qq.Activity;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name="loading_info.db";
    static int dbVersion=1;

    public DatabaseHelper(Context context){
        super(context,name, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建一个表
        db.execSQL("CREATE TABLE users(id CHAR PRIMARY KEY UNIQUE NOT NULL,pwd CHAR NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
