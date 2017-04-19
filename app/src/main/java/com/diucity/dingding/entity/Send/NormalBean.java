package com.diucity.dingding.entity.Send;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class NormalBean {
    private long timestamp;
    private String nonce;

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
}
