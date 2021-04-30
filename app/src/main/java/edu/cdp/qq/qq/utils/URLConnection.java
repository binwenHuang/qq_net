package edu.cdp.qq.qq.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

/**
 * 作者： Duan on 2016/6/4 0004.
 */
public class URLConnection {
    private static String TAG="";
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public URLConnection() {

    }

    /**
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params, String method)
            throws Exception {
        Log.i("", "执行了net");

        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if (method == null || method.equals("GET")) {
                strUrl = strUrl + "?" + urlencode(params);
            }
            Log.i(TAG, "strUrl:" + strUrl);
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if (method == null || method.equals("GET")) {
                conn.setRequestMethod("GET");
            } else {
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();

            if (params != null && method.equals("POST")) {
                DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                out.writeBytes(urlencode(params));
            }
            Integer state = conn.getResponseCode();//查询请求状态信息
            Log.i(TAG, "state: " + state);
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            //rs = rs.substring(rs.indexOf("{"), rs.indexOf("}") + 1);
           // rs = rs.substring(rs.indexOf("["), rs.indexOf("]") + 1);
            System.out.println("信息测试" + rs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    // 将map型转为请求参数型urlencode
    public static String urlencode(Map<String, Object> data) {
        System.out.println("执行了urlencode");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=")
                        .append(URLEncoder.encode(i.getValue() + "", "UTF-8"))
                        .append("&");
                System.out.println("sb: " + sb);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        System.out.println("sb: " + sb.toString());
        return sb.toString();
    }
}
