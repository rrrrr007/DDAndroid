package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class SupplierBean {


    public SupplierBean(int recycler_id, String auth_code, int supplier_id) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(),auth_code);
        this.supplier_id = supplier_id;
    }

    /**
     * timestamp : 373091747
     * nonce : beab54a1-fd7a-442f-bb26-201f5b98eb69
     * recycler_id : 1027301
     * auth_code : sjsj2010A20suycxx
     * supplier_id : 1280301
     */

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_code;
    private int supplier_id;

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

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }
}
