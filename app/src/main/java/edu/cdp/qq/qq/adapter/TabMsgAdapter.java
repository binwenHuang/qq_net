package edu.cdp.qq.qq.adapter;

import android.app.MediaRouteButton;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.Activity.BaseActivity;
import edu.cdp.qq.qq.Activity.LoadingActivity;
import edu.cdp.qq.qq.Activity.MainActivity;
import edu.cdp.qq.qq.Activity.Msg_Info_Activity;
import edu.cdp.qq.qq.bean.TabMsg;
import edu.cdp.qq.qq.view.ViewMsg;

public class TabMsgAdapter extends BaseAdapter {
    private ArrayList<TabMsg> tabMsgs;
    private LayoutInflater myli;
    private Context context;

    public TabMsgAdapter(ArrayList<TabMsg> tabMsgs,Context context) {
        this.tabMsgs=tabMsgs;
        this.context=context;
        this.myli=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return tabMsgs.size();
    }

    @Override
    public Object getItem(int i) {
        return tabMsgs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup parent) {
        final TabMsg tabMsg=tabMsgs.get(i);
        ViewHolder holder = null;
        if(view == null){
            view=myli.inflate(R.layout.tab_msg_item,null);
            holder = new ViewHolder();
            holder.ivhead=view.findViewById(R.id.iv_head);
            holder.tvusername=view.findViewById(R.id.tv_friend_name);
            holder.tvmsgtime=view.findViewById(R.id.tv_msg_time);
            holder.tvmsg=view.findViewById(R.id.tv_friend_msg);
            holder.tvmsgnum=view.findViewById(R.id.tv_msg_count);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }

        holder.ivhead.setImageDrawable(getHead(tabMsg.getUserId()));
        holder.tvusername.setText(tabMsg.getUserName());
        holder.tvmsgtime.setText(tabMsg.getUserTime());
        holder.tvmsg.setText(tabMsg.getUserMsg());
        holder.tvmsgnum.setText(tabMsg.getUserNum());

        final ViewHolder finalHolder = holder;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击好友消息框后，进入消息界面，同时将消息提醒清空
                finalHolder.tvmsgnum.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(context, Msg_Info_Activity.class);
                intent.putExtra("headId",tabMsg.getUserId());
                intent.putExtra("name",tabMsg.getUserName());
                context.startActivity(intent);
            }
        });

        //设置对话框的长按监听
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showPopWindows(v);
                return false;
            }
        });
        return view;
    }

    //长按时显示提示框
    private void showPopWindows(View v) {
        final View mPopView = LayoutInflater.from(context).inflate(R.layout.popup, null);
        final PopupWindow mPopWindow = new PopupWindow(mPopView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        //获取弹窗的长宽度
        mPopView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWidth = mPopView.getMeasuredWidth();
        int popupHeight =  mPopView.getMeasuredHeight();
        //获取父控件的位置
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        // 设置位置
        mPopWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1]
                - popupHeight);
        mPopWindow.update();
        mPopView.findViewById(R.id.tv_copy_txt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置删除会话的操作
                if (mPopWindow != null) {
                    Toast.makeText(context, "删除会话", Toast.LENGTH_LONG).show();
                    tabMsgs.remove(1);
                    notifyDataSetChanged();
                    mPopWindow.dismiss();
                }
            }
        });
    }

    //获取头像
    private Drawable getHead(String id){
        int resid =context.getResources().getIdentifier(id,"drawable",context.getPackageName());
        Drawable d = context.getResources().getDrawable(resid);
        return d;
    }

    public static class ViewHolder{
        public ImageView ivhead;
        public  TextView tvusername,tvmsg,tvmsgtime,tvmsgnum;
    }

}
