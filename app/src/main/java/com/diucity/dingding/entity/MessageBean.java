package com.diucity.dingding.entity;


/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class MessageBean {
    public MessageBean(long timestamp, String targetid) {
        this.timestamp = timestamp;
        this.targetid = targetid;
    }

    private long timestamp;
    private String targetid;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getTargetid() {
        return targetid;
    }

    public void setTargetid(String targetid) {
        this.targetid = targetid;
    }
}

