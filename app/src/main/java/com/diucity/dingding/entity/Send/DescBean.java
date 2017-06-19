package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class DescBean {

    public DescBean(int recycler_id) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
    }

    /**
     * timestamp : 373091747
     * nonce : beab54a1-fd7a-442f-bb26-201f5b98eb69
     * recycler_id : 100005
     */

    private long timestamp;
    private String nonce;
    private int recycler_id;

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
