package com.diucity.dingding.api;

import com.diucity.dingding.entity.Back.*;
import com.diucity.dingding.entity.Send.*;

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
    String BASEURL = "https://recycleapi.dev.local:3030/";

    @POST("sms.send&sign={sign}")
    Observable<NormalBack> sms(@Path("sign") String sign, @Body SmsBean bean);

    @POST("login&sign={sign}")
    Observable<LoginBack> login(@Path("sign") String sign, @Body LoginBean bean);

    @POST("password.login.reset&sign={sign}")
    Observable<NormalBack> reset(@Path("sign") String sign, @Body ResetBean bean);

    @POST("password.login.change&sign={sign}")
    Observable<NormalBack> change(@Path("sign") String sign, @Body ChangeBean bean);

    @POST("password.pay.set&sign={sign}")
    Observable<NormalBack> payset(@Path("sign") String sign, @Body PaysetBean bean);

    @POST("quotation.scraps&sign={sign}")
    Observable<ScrapsBack> scraps(@Path("sign") String sign, @Body ScrapsBean bean);

    @POST("quotation.today&sign={sign}")
    Observable<TodayBack> today(@Path("sign") String sign, @Body TodayBean bean);

    @POST("task.list&sign={sign}")
    Observable<TaskBack> task(@Path("sign") String sign, @Body TaskBean bean);

    @POST("recycler.basket.info&sign={sign}")
    Observable<BasketBack> basket(@Path("sign") String sign, @Body BasketBean bean);

    @POST("wallet.summary&sign={sign}")
    Observable<SummaryBack> summary(@Path("sign") String sign, @Body SummaryBean bean);

    @POST("bill.list&sign={sign}")
    Observable<Object> list(@Path("sign") String sign, @Body ListBean bean);

    @POST("recycler.notices&sign={sign}")
    Observable<Object> notices(@Path("sign") String sign, @Body ListBean bean);

}
