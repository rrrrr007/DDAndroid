package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class ListBean {

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_code;
    private int bill_id;
    private int bill_count;
    private int notice_id;
    private int notice_count;

    public ListBean(int recycler_id, String taken, int bill_id, int bill_count) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(), taken);
        this.bill_id = bill_id;
        this.bill_count = bill_count;
    }

    public ListBean(int recycler_id, String taken, int notice_id, int notice_count,int type) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(), taken);
        this.notice_id = notice_id;
        this.notice_count = notice_count;
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

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
    }

    public int getBill_count() {
        return bill_count;
    }

    public void setBill_count(int bill_count) {
        this.bill_count = bill_count;
    }

    public int getNotice_id() {
        return notice_id;
    }

    public void setNotice_id(int notice_id) {
        this.notice_id = notice_id;
    }

    public int getNotice_count() {
        return notice_count;
    }

    public void setNotice_count(int notice_count) {
        this.notice_count = notice_count;
    }
}
