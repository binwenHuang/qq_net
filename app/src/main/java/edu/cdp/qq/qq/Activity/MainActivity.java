package edu.cdp.qq.qq.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;


import java.util.ArrayList;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.adapter.VpAdapter;
import edu.cdp.qq.qq.view.ViewDongtai;
import edu.cdp.qq.qq.view.ViewMsg;
import edu.cdp.qq.qq.view.ViewUser;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView headself;
    private ViewUser viewUser;
    private ViewMsg viewMsg;
    private ViewDongtai viewDongtai;
    private ViewPager vpContent;
    private LinearLayout lluser,llmsg,lldongtai;
    private ImageView ivtabmsg,ivtabuser,ivtabdongtai;
    private TextView tvtabmsg,tvtabuser,tvtabdongtai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inintview();
        //进入时应该在消息页面，给一个初始值0用于初始化主窗口的导航栏
        setStatus(0);
    }

    private void inintview(){
        vpContent = findViewById(R.id.vp_content);
        getView();
        gettab();
    }

    //获取视图
    private void getView(){
        viewUser = new ViewUser(this);
        View v1 = viewUser.getView();
        viewMsg = new ViewMsg(this);
        View v2 = viewMsg.getView();
        viewDongtai = new ViewDongtai(this);
        View v3 = viewDongtai.getView();

        ArrayList<View> views = new ArrayList<View>();
        views.add(v2);
        views.add(v1);
        views.add(v3);
        VpAdapter vpAdapter=new VpAdapter(views);
        vpContent.setAdapter(vpAdapter);

        //设置滑动监听事件，滑动时改变主导航栏的样式
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //设置首先显示为消息页面
        vpContent.setCurrentItem(0);


    }

    //获取并初始化主窗口的样式，添加监听
    private void gettab(){
        headself = findViewById(R.id.head_self);
        headself.setOnClickListener(this);

        llmsg=findViewById(R.id.ll_msg);
        llmsg.setOnClickListener(this);
        lluser=findViewById(R.id.ll_user);
        lluser.setOnClickListener(this);
        lldongtai=findViewById(R.id.ll_dongtai);
        lldongtai.setOnClickListener(this);

        ivtabmsg=findViewById(R.id.iv_msg);
        ivtabuser=findViewById(R.id.iv_user);
        ivtabdongtai=findViewById(R.id.iv_dongtai);

        tvtabmsg=findViewById(R.id.tv_tab_msg);
        tvtabuser=findViewById(R.id.tv_tab_user);
        tvtabdongtai=findViewById(R.id.tv_tab_dongtai);

    }

    //设置监听
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ll_msg:
                vpContent.setCurrentItem(1);
                setStatus(1);
                break;
            case R.id.ll_user:
                vpContent.setCurrentItem(0);
                setStatus(0);
                break;
            case R.id.ll_dongtai:
                vpContent.setCurrentItem(2);
                setStatus(2);
                break;
            case R.id.head_self:
                Intent intent = new Intent(MainActivity.this,SetingActivity.class);
                startActivity(intent);
                break;
        }
    }

    //根据窗口设置主导航栏的样式
    private void setStatus(int index){
        clearstatus();
        switch (index){
            case 0:
                ivtabmsg.setImageResource(R.drawable.tab_msg_pressed);
                tvtabmsg.setTextColor(Color.parseColor("#0097f7"));
                break;
            case 1:
                ivtabuser.setImageResource(R.drawable.tab_find_frd_pressed);
                tvtabuser.setTextColor(Color.parseColor("#0097f7"));
                break;
            case 2:
                ivtabdongtai.setImageResource(R.drawable.tab_dt_pressed);
                tvtabdongtai.setTextColor(Color.parseColor("#0097f7"));
                break;
        }
    }

    //将样式还原
    private void clearstatus(){
        ivtabmsg.setImageResource(R.drawable.tab_msg_normal);
        tvtabmsg.setTextColor(Color.parseColor("#666666"));
        ivtabuser.setImageResource(R.drawable.tab_find_frd_normal);
        tvtabuser.setTextColor(Color.parseColor("#666666"));
        ivtabdongtai.setImageResource(R.drawable.tab_dt_normal);
        tvtabdongtai.setTextColor(Color.parseColor("#666666"));
    }

}