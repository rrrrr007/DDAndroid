package com.diucity.dingding.entity.Back;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class WXBack {


    /**
     * appid : wx2920e5b3cf5c3b39
     * noncestr : 960a66476f74e5b3df64020c02c8fecc
     * package : Sign=WXPay
     * partnerid : 1473325502
     * prepayid : wx20170519115617201616
     * sign : 308AF328193DD892D38485C990E64687
     * timestamp : 1495166177
     */

    private String appid;
    private String noncestr;
    @SerializedName("package")
    private String packageX;
    private String partnerid;
    private String prepayid;
    private String sign;
    private String timestamp;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
