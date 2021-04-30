package edu.cdp.qq.qq.utils;

import android.app.DownloadManager;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import edu.cdp.qq.qq.MyApplication;

public class VolleyManager {
    private static RequestQueue requestQueue;
    //超时时间 30s
    private final static int TIME_OUT = 30000;

    private static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            synchronized (VolleyManager.class) {
                if (requestQueue == null) {
                    //使用全局context对象
                    requestQueue = Volley.newRequestQueue(MyApplication.getContext());
                }
            }
        }
        return requestQueue;
    }

   public static <T> void addRequest(RequestQueue requestQueue, Request<T> request){
       request.setShouldCache(true);
       request.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
               DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
               DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
       requestQueue.add(request);
   }

   //返回json对象
    /**
     * 1. int 类型 用于指定请求的方式（如GET或者POST）
     * 2. String类型 用于指定网络请求要连接的网址
     * 3. Listener类型 ,接收网络响应的接口，即只要得到本次请求对应的返回结果
     * 就会运行此接口中的onResponse方法
     * 4： ErrorListener类型， 用于接收当网络请求的过程中一旦发生了什么错误，
     * 就会调用本接口中的onErrorResponse方法
     * */
   public static void sendJsonObjectRequest(int method,String url,
                                            final Response.Listener<JSONObject> listener,
                                            final Response.ErrorListener errorListener){
       try {
           JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(method,
                   url,listener, errorListener) {
               @Override
               public Map<String, String> getHeaders() throws AuthFailureError {
                   Map<String, String> headers = new HashMap<>();
                   headers.put("username", "value1");
                   headers.put("password", "value2");
                   //headers.put("accept-encoding", "utf-8");
                   return headers;
               }
           };
           addRequest(getRequestQueue(), jsonObjectRequest);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
    //返回String对象
    public static void sendStringRequest(int method, String url,
                                         final Map<String, String> params,
                                         final Response.Listener<String> listener,
                                         final Response.ErrorListener errorListener) {
        try {
            StringRequest stringRequest = new StringRequest(method, url, listener, errorListener) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    return params;
                }
            };
            addRequest(getRequestQueue(), stringRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

