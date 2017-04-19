package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;
import com.diucity.dingding.utils.VersonUtils;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class LoginBean {
    private long timestamp;
    private String nonce;
    private String mobile;
    private String auth_login_code;
    private int device_type;
    private String device_model;
    private String client_version;

    public LoginBean(String mobile, String auth_login_code, String client_version) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.mobile = mobile;
        this.auth_login_code = SignUtils.loginCode(getTimestamp(),auth_login_code);
        this.device_type = 11;
        this.device_model = VersonUtils.getSystemModel();
        this.client_version = client_version;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAuth_login_code() {
        return auth_login_code;
    }

    public int getDevice_type() {
        return device_type;
    }

    public void setDevice_type(int device_type) {
        this.device_type = device_type;
    }

    public String getDevice_model() {
        return device_model;
    }

    public void setDevice_model(String device_model) {
        this.device_model = device_model;
    }

    public String getClient_version() {
        return client_version;
    }

    public void setClient_version(String client_version) {
        this.client_version = client_version;
    }
}
