package edu.cdp.qq.qq.utils;

public class ConstentUtils {
    //服务器IP地址
    //public static String SERVER_ADDRESS="192.168.169.108";
    public static String SERVER_ADDRESS="172.17.163.177";
    //图片请求的ip地址
    public static String SERVER_Image_ADDRESS="172.17.163.177";

    //端口号
    public static int SERVER_PORT=8000;

    //登录对应的API接口地址
    public final static String LOGINAPI="http://" + SERVER_ADDRESS + ":8005/myservice/login";

    public final static String IMAGEAPI="http://" + SERVER_Image_ADDRESS + ":8005/myservice/img/sa.png";

}
