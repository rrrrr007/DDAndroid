package com.diucity.dingding.entity.Send;


import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class ScrapsBean {
    private long timestamp;
    private String nonce;
    private int recycler_id;

    public ScrapsBean(int recycler_id) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public int getRecycler_id() {
        return recycler_id;
    }

    public void setRecycler_id(int recycler_id) {
        this.recycler_id = recycler_id;
    }
}
