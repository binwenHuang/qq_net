package edu.cdp.qq.qq.bean;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import edu.cdp.qq.qq.Activity.DatabaseHelper;
import edu.cdp.qq.qq.bean.User;

public class UserService {
    private DatabaseHelper dbHelper;

    public UserService(Context context){
        dbHelper=new DatabaseHelper(context);
    }

    public boolean login(String user_id_get,String user_password_get){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="select * from users where id=? and pwd=?";
        Cursor cursor=sdb.rawQuery(sql, new String[]{user_id_get,user_password_get});
        if(cursor.moveToFirst()==true){
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean register(User user){
        SQLiteDatabase sdb=dbHelper.getReadableDatabase();
        String sql="insert into users(id,pwd) values(?,?)";
        Object obj[]={user.getId(),user.getPassword()};
        sdb.execSQL(sql, obj);
        return true;
    }
}