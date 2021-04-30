package edu.cdp.qq.qq.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.cdp.qq.R;

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
   protected ImageView ivback;
   protected TextView tvtitle;
   protected TextView tvahandler;
   protected TextView tvXY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.activity_base);
        setContentView(getLayout());
        intViews();
    }

    protected abstract void intViews();

    protected abstract int getLayout();

    protected void setTitleVisiable(String title){
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.activity_base);
        ivback=findViewById(R.id.iv_back);
        tvtitle=findViewById(R.id.tv_title);
        tvahandler=findViewById(R.id.tv_handler);
        tvahandler.setVisibility(View.INVISIBLE);
        tvtitle.setText(title);
        ivback.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.iv_back:
                finish();
                break;


        }
    }

}
