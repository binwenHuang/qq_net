package edu.cdp.qq.qq.view;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.PopupWindow;

import java.util.ArrayList;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.adapter.TabMsgAdapter;
import edu.cdp.qq.qq.bean.TabMsg;

public class ViewMsg{
        private Context context;
        private LayoutInflater myLi;
        private ListView lvmsg;

        public ViewMsg(Context context) {
            this.context = context;
            this.myLi = LayoutInflater.from(context);
        }

        public View getView() {
            View v = myLi.inflate(R.layout.tab_msg, null);
            lvmsg=v.findViewById(R.id.lv_msg);
            getMsg();
            return v;
        }

        private void getMsg(){
            ArrayList<TabMsg> tabMsgs=new ArrayList<>();
            tabMsgs.add(new TabMsg("h1","shfas","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h2","srgddd","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h3","seri","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h4","lisa","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h5","冲红","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h6","蓝天","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h7","白云","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h8","旷野","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h9","小明","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h10","相亲相爱一家人","下午上课","13：00","2"));
            tabMsgs.add(new TabMsg("h1","张三","下午上课","13：00","2"));
            TabMsgAdapter tabMsgAdapter=new TabMsgAdapter(tabMsgs,context);
            lvmsg.setAdapter(tabMsgAdapter);

        }



}
