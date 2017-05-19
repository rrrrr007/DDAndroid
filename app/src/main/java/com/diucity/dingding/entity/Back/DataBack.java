package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class DataBack {


    /**
     * url : http://121.15.180.66:801/NetPayment/BaseHttp.dll?MB_EUserPay
     * body : {"version":"1.0","charset":"UTF-8","sign":"88E788D3B53126DCB687C349668AB106527073CE5ACE3C1669B9638FC0B9007D","signType":"SHA-256","reqData":{"agrNo":"100000","amount":"0.01","branchNo":"0028","date":"20170519","dateTime":"20170519102318","expireTimeSpan":"30","merchantNo":"000019","merchantSerialNo":"1495006478496133170","orderNo":"8496133170","payNoticeUrl":"http://pay.diucity.com:8090/callback/payment/cmb","returnUrl":"","signNoticeUrl":"http://pay.diucity.com:8090/callback/sign"}}
     */

    private String url;
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
