package edu.cdp.qq.qq.utils;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class Params {
    public static HashMap<String , String> login(String acount, String pass){
        Map<String,String> map = new HashMap<String, String>();
        map.put("user_name",acount);
        map.put("password",pass);
        return (HashMap<String,String>)map;
    }
}
