package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class BasketBean {
    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_code;

    public BasketBean(int recycler_id, String auth_code) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(),auth_code);
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

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }
}
