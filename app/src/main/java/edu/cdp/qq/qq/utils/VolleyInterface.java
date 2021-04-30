package edu.cdp.qq.qq.utils;

import android.content.Context;
import android.graphics.Bitmap;

import com.android.volley.Response;
import com.android.volley.VolleyError;

public abstract class VolleyInterface {
    public Context context;
    public static Response.Listener<String> listener;
    public static Response.ErrorListener errorListener;
    public static Response.Listener<Bitmap> imageListener;

    //定义接口的构造函数
    public VolleyInterface(Context context,Response.Listener listener,Response.ErrorListener errorListener,Response.Listener imageListener) {
        this.context = context;
        this.listener=listener;
        this.errorListener=errorListener;
        this.imageListener = imageListener;
    }

    //定义接口中成功的抽象方法
    public abstract void onMysuccess(String result);

    //请求成功的监听
    public Response.Listener<String> successListener(){
        listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMysuccess(s);
            }
        };

        return listener;
    }

    //定义接口中失败的抽象方法
    public abstract void onMyerror(VolleyError error);

    //请求失败的监听
    public Response.ErrorListener errorListener(){
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyerror(volleyError);
            }
        };

        return errorListener;
    }

    //定义接口请求图片的抽象方法
    public abstract void onImageSuccese(Bitmap bitmap);
    //图片请求成功的监听
    public Response.Listener<Bitmap> Imagelistener(){
        imageListener = new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                onImageSuccese(bitmap);
            }
        };
        return imageListener;
    }

}
