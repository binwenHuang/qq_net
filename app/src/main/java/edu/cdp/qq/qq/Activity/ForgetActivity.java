package edu.cdp.qq.qq.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.se.omapi.Session;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;
import java.util.Random;

import edu.cdp.qq.R;

public class ForgetActivity extends BaseActivity implements View.OnClickListener {
    EditText edt_yanzhen,edt_id,newPwd;
    TextView tv_yz;
    private DatabaseHelper dbHelper;
    private Button btn_put;


    @Override
    protected void intViews() {
        tv_yz= findViewById(R.id.yanzhen);
        //生成随机数
        int flag = new Random().nextInt(999999);
        for (int i = 0; i <100; i++){
            if (flag < 100000) {
                flag += 100000;
            }
        }
        tv_yz.setText(""+flag);
        btn_put = findViewById(R.id.putup);
        dbHelper=new DatabaseHelper(this);
        setTitleVisiable("忘记密码");

        //设置监听
        btn_put.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        yanZhen();
                        Looper.loop();
                    }
                }).start();
            }
        });

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_forget;
    }

    //验证判断，保存新密码
    private void yanZhen(){
        edt_yanzhen = findViewById(R.id.yanzhen_input);
        edt_id= findViewById(R.id.user_id);
        String id_input = edt_id.getText().toString().trim();

        newPwd = findViewById(R.id.pwd_new);
        String new_pwd = newPwd.getText().toString().trim();

        String yanzhen_give = tv_yz.getText().toString().trim();
        String yanzhen_input = edt_yanzhen.getText().toString().trim();

        if(yanzhen_give.equals(yanzhen_input)){
            SQLiteDatabase sdb=dbHelper.getReadableDatabase();
            String sql="select * from users where id=?";
            Cursor cursor=sdb.rawQuery(sql, new String[]{id_input});
            if(cursor.getCount()!=0){
                try {
                    String sql1="update users set pwd=? where id=?";
                    Object obj[]={new_pwd,id_input};
                    sdb.execSQL(sql1, obj);
                    Toast.makeText(ForgetActivity.this, "更新成功!!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(ForgetActivity.this,LoadingActivity.class);
                    intent.putExtra("id",id_input);
                    intent.putExtra("pwd", new_pwd);
                    startActivity(intent);
                    finish();

                } catch (SQLException e) {
                    Toast.makeText(ForgetActivity.this, "更新失败!!!", Toast.LENGTH_LONG).show();
                }


            }else {
                Toast.makeText(ForgetActivity.this, "账户或验证有误!!!", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(ForgetActivity.this, "账户或验证有误!!!", Toast.LENGTH_LONG).show();
        }

    }

}