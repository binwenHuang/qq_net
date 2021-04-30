package edu.cdp.qq.qq.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

import edu.cdp.qq.R;
import edu.cdp.qq.qq.MyApplication;

public class VolleyRequest {
    private static StringRequest stringRequest;
//    private static Context context;


    //get请求
    public static void requestGet(String url,String tag,VolleyInterface vif){
        MyApplication.getHttpQueue().cancelAll(tag);
        StringRequest stringRequest= new StringRequest(Request.Method.GET,url,vif.successListener(),vif.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueue().add(stringRequest);
        MyApplication.getHttpQueue().start();
    }

    //POST请求
    public static void requestPost(String url, String tag, final Map<String ,String> params, VolleyInterface vif){
        MyApplication.getHttpQueue().cancelAll(tag);
        StringRequest stringRequest= new StringRequest(Request.Method.POST,url,vif.successListener(),vif.errorListener()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        stringRequest.setTag(tag);
        MyApplication.getHttpQueue().add(stringRequest);
        MyApplication.getHttpQueue().start();
    }


    //图片请求,下载的操作
    public static void requestImage(String url, String tag,VolleyInterface vif){
        MyApplication.getHttpQueue().cancelAll(tag);
        ImageRequest imageRequest = new ImageRequest(url,vif.Imagelistener(),
        0,0, Bitmap.Config.RGB_565,vif.errorListener()){
        };
        imageRequest.setTag(tag);
        MyApplication.getHttpQueue().add(imageRequest);
        MyApplication.getHttpQueue().start();
    }


    //使用缓存机制加载图片
    public static void getCacheImage(String url,String tag, ImageView iv){
        MyApplication.getHttpQueue().cancelAll(tag);
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueue(), new BitmapCache());
        ImageLoader.ImageListener listener =ImageLoader.getImageListener(iv, R.drawable.h1,R.drawable.h1);
        imageLoader.get(url,listener);
    }


}
