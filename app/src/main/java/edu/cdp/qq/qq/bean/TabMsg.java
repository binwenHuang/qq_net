package edu.cdp.qq.qq.bean;

public class TabMsg {
    private String UserId,UserName,UserMsg,UserTime,UserNum;

    public TabMsg(String userId, String userName, String userMsg, String userTime, String userNum) {
        UserId = userId;
        UserName = userName;
        UserMsg = userMsg;
        UserTime = userTime;
        UserNum = userNum;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserMsg() {
        return UserMsg;
    }

    public void setUserMsg(String userMsg) {
        UserMsg = userMsg;
    }

    public String getUserTime() {
        return UserTime;
    }

    public void setUserTime(String userTime) {
        UserTime = userTime;
    }

    public String getUserNum() {
        return UserNum;
    }

    public void setUserNum(String userNum) {
        UserNum = userNum;
    }
}
