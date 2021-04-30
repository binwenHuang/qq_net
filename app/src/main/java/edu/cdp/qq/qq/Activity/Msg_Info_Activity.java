package edu.cdp.qq.qq.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.adapter.MyRecylerViewAdapter;
import edu.cdp.qq.qq.bean.Msg_info;

@RequiresApi(api = Build.VERSION_CODES.N)
public class Msg_Info_Activity extends AppCompatActivity{

    private String headId,username;
    private TextView tvTitle;
    private ImageView ivBack,ivhead;
    private List<Msg_info> list;
    private int type;
    private TextView tv_msg_left;
    private RecyclerView recyclerView;
    private MyRecylerViewAdapter recyclerViewAdapter;
    private EditText edtMsg;
    private Button btnSend;
    private SwipeRefreshLayout swipeRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private int lastVisibleItem;
    private boolean flag =true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg__info);
        getMsginfo();
        initview();
        initMsgInfo();
        bindadapter();
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerViewAdapter = new MyRecylerViewAdapter(list,this,headId);
        recyclerView.setAdapter(recyclerViewAdapter);

    }


    //获取传过来的头像和名称
    private void getMsginfo(){
        Intent intent=getIntent();
        headId=intent.getStringExtra("headId");
        username=intent.getStringExtra("name");
        Log.i("2233", "getMsginfo: "+headId+username);
    }

    private  void  initview(){

        edtMsg = findViewById(R.id.edt_msg);

        recyclerView=findViewById(R.id.recy_msg);

        //上拉刷新添加监听
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(lastVisibleItem+1 == recyclerViewAdapter.getItemCount() && flag == true){
                    recyclerViewAdapter.changeState(1);
                    flag = false;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recyclerViewAdapter.changeState(0);
                            List<Msg_info> oldList = getNewData();
                            for(int i=0;i<oldList.size();i++){
                                list.add(list.size(),oldList.get(i));
                            }
                        }
                    }, 3000);

                }

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = linearLayoutManager.findLastCompletelyVisibleItemPosition();
            }
        });


        tvTitle=findViewById(R.id.tv_title);
        tvTitle.setText(username);

        ivhead = findViewById(R.id.iv_head_left);
        ivBack=findViewById(R.id.iv_back);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //实例化发送按钮并设置监听
        btnSend=findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMsg();
            }
        });

        //下拉刷新的监听
        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
                List<Msg_info> oldList = getOldData();
                for(int i=0;i<oldList.size();i++){
                    list.add(0,oldList.get(i));
                    recyclerViewAdapter.notifyItemChanged(0);
                }
                recyclerView.scrollToPosition(0);
                swipeRefreshLayout.setEnabled(false);
            }
        });



    }




    //获取聊天信息
    private  void initMsgInfo(){
        list=new ArrayList<>();
        list.add(new Msg_info("你好","14：10",0));
        list.add(new Msg_info("你好","14：10",0));
        list.add(new Msg_info("你好","14：10",1));
        list.add(new Msg_info("你好","14：10",0));
        list.add(new Msg_info("你好","14：10",1));
        list.add(new Msg_info("你好","14：10",0));
        list.add(new Msg_info("你好","14：10",1));
        list.add(new Msg_info("你好","14：10",0));
    }


    private List<Msg_info> getOldData(){
        List<Msg_info> list = new ArrayList<>();
        list.add(new Msg_info("好好学习","14：00",0));
        list.add(new Msg_info("天天向上","14：01",1));
        list.add(new Msg_info("冲冲冲","14：03",0));
        list.add(new Msg_info("哈喽","14：08",1));
        return list;
    }

    private List<Msg_info> getNewData(){
        List<Msg_info> list = new ArrayList<>();
        list.add(new Msg_info("1","14：00",0));
        list.add(new Msg_info("2","14：01",1));
        list.add(new Msg_info("sfsfsf","14：03",0));
        list.add(new Msg_info("353535","14：08",1));
        return list;
    }

    private void bindadapter(){
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new MyRecylerViewAdapter(list,this, headId);
        recyclerView.setAdapter(recyclerViewAdapter);


    }

    private void addMsg(){
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR);
        final int minute = c.get(Calendar.MINUTE);
        String strMsg;
        strMsg=edtMsg.getText().toString().trim();
        if (!"".equals(strMsg)){
            //获取发送的内容以及时间
            Msg_info msg1 = new Msg_info(strMsg,hour+":"+minute,Msg_info.TYPE_sender);
            list.add(msg1);
            recyclerViewAdapter.notifyItemChanged(list.size()-1);
            recyclerView.scrollToPosition(list.size()-1);
            edtMsg.setText("");
        }
    }


}