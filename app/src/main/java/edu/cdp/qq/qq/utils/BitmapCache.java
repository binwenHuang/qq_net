package edu.cdp.qq.qq.utils;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String,Bitmap> lruCache;

    //设置缓存的大小
    public BitmapCache(){
        int maxSize = 10 * 1024 * 1024;
        lruCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getRowBytes() * bitmap.getHeight();
            }
        };
    }

    //获取缓存中的数据
    public Bitmap getBitmap(String url) {
        Bitmap bitmap = lruCache.get(url);

        return bitmap;

    }


    //将图片放进缓存
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url, bitmap);

    }
}
