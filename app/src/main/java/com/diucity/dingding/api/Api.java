package com.diucity.dingding.api;


import com.diucity.dingding.entity.Back.LoginBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.entity.Send.PaysetBean;
import com.diucity.dingding.entity.Send.ResetBean;
import com.diucity.dingding.entity.Send.ScrapsBean;
import com.diucity.dingding.entity.Send.SmsBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sushuai on 2016/3/25.
 */
public interface Api {
    //String BASEURL = "http://www.diucity.com:1601/api/";
    //String BASEURL = "https://api.dev.intranet/";
    String BASEURL = "https://zhoufeng.diucity.com:4443/";

    @POST("sms.send&sign={sign}")
    Observable<NormalBack> sms(@Path("sign") String sign, @Body SmsBean bean);

    @POST("login&sign={sign}")
    Observable<LoginBack> login(@Path("sign") String sign, @Body LoginBean bean);

    @POST("password.login.reset&sign={sign}")
    Observable<NormalBack> reset(@Path("sign") String sign, @Body ResetBean bean);

    @POST("password.pay.set&sign={sign}")
    Observable<NormalBack> payset(@Path("sign") String sign, @Body PaysetBean bean);

    @POST("quotation.scraps&sign={sign}")
    Observable<NormalBack> scraps(@Path("sign") String sign, @Body ScrapsBean bean);

    @POST("quotation.today&sign={sign}")
    Observable<NormalBack> today(@Path("sign") String sign, @Body ScrapsBean bean);
}
