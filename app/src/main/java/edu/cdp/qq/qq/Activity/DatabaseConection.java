package edu.cdp.qq.qq.Activity;


import android.app.Activity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConection extends Activity {
    static String DRIVER="com.mysql.jdbc.Driver";
    static String URL="jdbc:mysql://172.17.149.74:3306/good_inout";
    static String USER="root";
    static String PSW="111111";

    //多次需要与数据库连接
    ResultSet rs;
    Connection conn;

    //数据库查询语句对象
    PreparedStatement ps;

    DatabaseConection()
    {
        try
        {
            Class.forName(DRIVER);
            conn=DriverManager.getConnection(URL,USER,PSW);
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //封装sql查询语句对象
    void prepare(String sql)
    {
        try{
            ps= conn.prepareStatement(sql);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    void shezifu(int index,String value)
    {
        try{
            ps.setString(index,value);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    //返回查询结果，执行查询
    ResultSet qurrey()
    {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    int update()
    {
        System.out.println(ps.toString());
        try {
            return ps.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
    }

    void close()
    {
        try{
            conn.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
