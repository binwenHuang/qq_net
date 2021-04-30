package edu.cdp.qq.qq.Activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.bean.User;
import edu.cdp.qq.qq.bean.UserService;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{
    EditText username;
    EditText password;
    Button register;

    @Override
    protected void intViews() {
        username=(EditText) findViewById(R.id.usernameRegister);
        password=(EditText) findViewById(R.id.passwordRegister);
        register=(Button) findViewById(R.id.Register);
        setTitleVisiable("新用户注册");

        register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String name=username.getText().toString().trim();
                String pass=password.getText().toString().trim();

                UserService uService=new UserService(RegisterActivity.this);
                User user=new User(name,pass);

                user.setId(name);
                user.setPassword(pass);

                uService.register(user);
                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(RegisterActivity.this,LoadingActivity.class);
                intent.putExtra("id",name);
                intent.putExtra("pwd", pass);
                startActivity(intent);
                finish();
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_zhuce;
    }
}