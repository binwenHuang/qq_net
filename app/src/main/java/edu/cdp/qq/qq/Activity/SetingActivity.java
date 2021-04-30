package edu.cdp.qq.qq.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import edu.cdp.qq.R;

public class SetingActivity extends BaseActivity implements View.OnClickListener {
    private TableRow tbexit;


    @Override
    protected void intViews() {
        tbexit=findViewById(R.id.exit);
        tbexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //清空状态文件
                SharedPreferences sp=getSharedPreferences("loding_success",MODE_PRIVATE);
                if(sp!=null) {
                    sp.edit().clear().commit();
                    Toast.makeText(SetingActivity.this, "成功退出账号", Toast.LENGTH_LONG).show();
                }
                Intent intent = new Intent(SetingActivity.this,LoadingActivity.class);
                startActivity(intent);
                finish();
            }
        });
        setTitleVisiable("个人中心");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_seting;
    }
}