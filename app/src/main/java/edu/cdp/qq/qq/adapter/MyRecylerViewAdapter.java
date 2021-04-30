package edu.cdp.qq.qq.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.bean.Msg_info;

public class MyRecylerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Msg_info> list;
    private LayoutInflater myli;
    private Context context;
    private String headId;

    //标识加载的是哪个
    static final int TYPE_ITEM=0;
    static final int TYPE_FOOT=1;
    //默认布局，不显示刷新加载
    int foot_state= 0;

    public int getItemViewType(int position){
        if (position+1==getItemCount()){
            return TYPE_FOOT;
        }else {
            return TYPE_ITEM;
        }
    }

    public MyRecylerViewAdapter(List<Msg_info> list, Context context, String headId) {
        this.list = list;
        this.myli = LayoutInflater.from(context);
        this.context = context;
        this.headId = headId;
    }


    @NonNull
    //根据viewtype判断加载对象
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM){
            View itemview= myli.inflate(R.layout.msg_info_item,null);
            return new MyViewHolder(itemview);
        }else if (viewType == TYPE_FOOT){
            View itemview= myli.inflate(R.layout.msg_item_foot,null);
            return new FootViewHolder(itemview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            //绑定消息数据
            MyViewHolder itemHolder = (MyViewHolder)holder;
            Msg_info msg_info=list.get(position);
            if (msg_info.getType()==Msg_info.TYPE_reciver){
                itemHolder.llleft.setVisibility(View.VISIBLE);
                itemHolder.llright.setVisibility(View.GONE);
                itemHolder.tvmsgleft.setText(msg_info.getContent());
                itemHolder.tvtimeleft.setText(msg_info.getTime());
                itemHolder.ivHdeadleft.setImageDrawable(getHead(headId));
            }
            else {
                itemHolder.llleft.setVisibility(View.GONE);
                itemHolder.llright.setVisibility(View.VISIBLE);
                itemHolder.tvmsgright.setText(msg_info.getContent());
                itemHolder.tvtimeright.setText(msg_info.getTime());
            }
        }else if(holder instanceof FootViewHolder){
            //加载
            FootViewHolder footViewHolder =(FootViewHolder)holder;
            switch (foot_state){
                case 0:
                    footViewHolder.mprogressBar.setVisibility(View.GONE);
                    footViewHolder.tv_state.setText("");
                    break;
                case 1:
                    footViewHolder.mprogressBar.setVisibility(View.VISIBLE);
                    footViewHolder.tv_state.setText("正在加载.....");
                    break;
            }

        }
    }

    public void changeState(int state){
        this.foot_state = state;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size()+1:0;
    }


    //消息的View Holder
    static class MyViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout llleft,llright;

        private ImageView ivHdeadleft,ivHeadright;
        private TextView tvtimeleft,tvtimeright,tvmsgleft,tvmsgright;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            llleft=itemView.findViewById(R.id.RL_left);
            tvtimeleft=itemView.findViewById(R.id.tv_time_left);
            ivHdeadleft=itemView.findViewById(R.id.iv_head_left);
            tvmsgleft=itemView.findViewById(R.id.TV_msg_left);

            llright=itemView.findViewById(R.id.RL_right);
            tvtimeright=itemView.findViewById(R.id.tv_time_right);
            ivHeadright=itemView.findViewById(R.id.iv_head_right);
            tvmsgright=itemView.findViewById(R.id.TV_msg_right);
        }
    }

    //上拉刷新的viewHolder
    static class FootViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar mprogressBar;
        private TextView tv_state;

        public FootViewHolder(@NonNull View itemView) {
            super(itemView);
            mprogressBar=(ProgressBar)itemView.findViewById(R.id.pd_bar);
            tv_state=(TextView)itemView.findViewById(R.id.tv_load);

        }
    }

    private Drawable getHead(String id){
        int resid =context.getResources().getIdentifier(id,"drawable",context.getPackageName());
        Drawable d = context.getResources().getDrawable(resid);
        return d;
    }

}
