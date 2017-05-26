package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class InfoBean {

    public InfoBean(int order_id) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.order_id = order_id;
    }

    private long timestamp;
    private String nonce;
    private int order_id;

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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }
}
