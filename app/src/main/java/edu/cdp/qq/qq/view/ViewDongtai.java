package edu.cdp.qq.qq.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import edu.cdp.qq.R;

public class ViewDongtai {
    private Context context;
    private LayoutInflater myLi;
    private TextView tvA;

    public ViewDongtai(Context context) {
        this.context = context;
        this.myLi = LayoutInflater.from(context);
    }

    public View getView(){
        return myLi.inflate(R.layout.tab_dongtai,null);
    }
}
