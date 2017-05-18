package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class ChangeBean {

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_login_code;
    private String login_code;

    public ChangeBean(int recycler_id, String auth_login_code, String login_code) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_login_code = SignUtils.loginCode(getTimestamp(),auth_login_code);
        this.login_code = login_code;
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

    public String getAuth_login_code() {
        return auth_login_code;
    }

    public void setAuth_login_code(String auth_login_code) {
        this.auth_login_code = auth_login_code;
    }

    public String getLogin_code() {
        return login_code;
    }

    public void setLogin_code(String login_code) {
        this.login_code = login_code;
    }
}
