package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/6/8 0008.
 */

public class NotifyBean {

    public NotifyBean(int recycler_id, String auth_code, int order_id, int payment_type) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(), auth_code);
        this.order_id = order_id;
        this.payment_type = payment_type;
    }

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_code;
    private int order_id;
    private int payment_type;

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

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }
}
