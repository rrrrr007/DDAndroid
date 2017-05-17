package com.diucity.dingding.entity.Send;


import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class XXBean {

    public XXBean(String mobile, String auth_login_code) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.mobile = mobile;
        this.auth_login_code = auth_login_code;
    }

    /**
     * timestamp : 373091747
     * nonce : beab54a1-fd7a-442f-bb26-201f5b98eb69
     * mobile : 13800138000
     * auth_login_code : sjsj2010A20suycxx
     */

    private long timestamp;
    private String nonce;
    private String mobile;
    private String auth_login_code;

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuth_login_code() {
        return auth_login_code;
    }

    public void setAuth_login_code(String auth_login_code) {
        this.auth_login_code = auth_login_code;
    }
}
