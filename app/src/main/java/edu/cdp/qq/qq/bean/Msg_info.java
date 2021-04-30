package edu.cdp.qq.qq.bean;

public class Msg_info {
    public static final int TYPE_reciver=0;
    public static final int TYPE_sender=1;
    private String content,time;
    private int type,id;

    public Msg_info(String content, String time, int type) {
        this.content = content;
        this.time = time;
        this.type = type;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
