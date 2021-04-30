package edu.cdp.qq.qq.Activity;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

import edu.cdp.qq.R;

import static android.view.View.getDefaultSize;

public class VideoActicity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_acticity);
        playVideo();
    }






    private void playVideo(){
        VideoView videoView = (VideoView)findViewById(R.id.videoView);

        //加载指定的视频文件
        String path = Environment.getExternalStorageDirectory().getPath()+"/Pictures/视频.mp4";
        videoView.setVideoPath(path);

        //创建MediaController对象
        MediaController mediaController = new MediaController(this);

        //VideoView与MediaController建立关联
        videoView.setMediaController(mediaController);

        //让VideoView获取焦点
        videoView.requestFocus();
    }



}