package edu.cdp.qq.qq.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import edu.cdp.qq.R;

public class ViewUser {
    private Context context;
    private LayoutInflater myLi;

    public  ViewUser(Context context){
        this.context = context;
        this.myLi = LayoutInflater.from(context);
    }

    public View getView(){
        return myLi.inflate(R.layout.tab_user,null);
    }

}
