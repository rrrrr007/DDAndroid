package com.diucity.dingding.api;

import com.diucity.dingding.entity.Back.BasketBack;
import com.diucity.dingding.entity.Back.CheckBack;
import com.diucity.dingding.entity.Back.CreateBack;
import com.diucity.dingding.entity.Back.DescBack;
import com.diucity.dingding.entity.Back.InfoBack;
import com.diucity.dingding.entity.Back.ListBack;
import com.diucity.dingding.entity.Back.LoginBack;
import com.diucity.dingding.entity.Back.NormalBack;
import com.diucity.dingding.entity.Back.RequestBack;
import com.diucity.dingding.entity.Back.ScrapsBack;
import com.diucity.dingding.entity.Back.SummaryBack;
import com.diucity.dingding.entity.Back.SupplierBack;
import com.diucity.dingding.entity.Back.SystemBack;
import com.diucity.dingding.entity.Back.TaskBack;
import com.diucity.dingding.entity.Back.TodayBack;
import com.diucity.dingding.entity.Send.BasketBean;
import com.diucity.dingding.entity.Send.ChangeBean;
import com.diucity.dingding.entity.Send.CheckBean;
import com.diucity.dingding.entity.Send.CreateBean;
import com.diucity.dingding.entity.Send.DescBean;
import com.diucity.dingding.entity.Send.InfoBean;
import com.diucity.dingding.entity.Send.ListBean;
import com.diucity.dingding.entity.Send.LoginBean;
import com.diucity.dingding.entity.Send.NotifyBean;
import com.diucity.dingding.entity.Send.PaysetBean;
import com.diucity.dingding.entity.Send.RequestBean;
import com.diucity.dingding.entity.Send.ResetBean;
import com.diucity.dingding.entity.Send.ScrapsBean;
import com.diucity.dingding.entity.Send.SmsBean;
import com.diucity.dingding.entity.Send.SummaryBean;
import com.diucity.dingding.entity.Send.SupplierBean;
import com.diucity.dingding.entity.Send.TaskBean;
import com.diucity.dingding.entity.Send.TodayBean;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sushuai on 2016/3/25.
 */
public interface Api {
    //String BASEURL = "https://debug.diucity.com/recycler/";
    String BASEURL = "https://api.dinghs.com/recycler/";
    //String BASEURL = "http:/192.168.3.126:4443/recycler/";

    //String BASEURL = "https://zhoufeng.diucity.com:4443/recycler/";
    //String WEBURL = "http://192.168.3.18:8080/";
    String WEBURL = "https://api.dinghs.com/mobile/";


    @POST("sms.send")
    Observable<NormalBack> sms(@Query("sign") String sign, @Body SmsBean bean);

    @POST("login")
    Observable<LoginBack> login(@Query("sign") String sign, @Body LoginBean bean);

    @POST("password.login.reset")
    Observable<NormalBack> reset(@Query("sign") String sign, @Body ResetBean bean);

    @POST("password.login.change")
    Observable<NormalBack> change(@Query("sign") String sign, @Body ChangeBean bean);

    @POST("password.pay.set")
    Observable<NormalBack> payset(@Query("sign") String sign, @Body PaysetBean bean);

    @POST("quotation.scraps")
    Observable<ScrapsBack> scraps(@Query("sign") String sign, @Body ScrapsBean bean);

    @POST("quotation.today")
    Observable<TodayBack> today(@Query("sign") String sign, @Body TodayBean bean);

    @POST("task.list")
    Observable<TaskBack> task(@Query("sign") String sign, @Body TaskBean bean);

    @POST("recycler.basket.info")
    Observable<BasketBack> basket(@Query("sign") String sign, @Body BasketBean bean);

    @POST("wallet.summary")
    Observable<SummaryBack> summary(@Query("sign") String sign, @Body SummaryBean bean);

    @POST("bill.list")
    Observable<ListBack> list(@Query("sign") String sign, @Body ListBean bean);

    @POST("recycler.notices")
    Observable<SystemBack> notices(@Query("sign") String sign, @Body ListBean bean);

    @POST("order.create")
    Observable<CreateBack> create(@Query("sign") String sign, @Body CreateBean bean);

    @POST("payment.request")
    Observable<RequestBack> request(@Query("sign") String sign, @Body RequestBean bean);

    @POST("user.supplier")
    Observable<SupplierBack> supplier(@Query("sign") String sign, @Body SupplierBean bean);

    @POST("order.check")
    Observable<CheckBack> check(@Query("sign") String sign, @Body CheckBean bean);

    @POST("order.notify")
    Observable<NormalBack> notify(@Query("sign") String sign, @Body NotifyBean bean);

    @POST("order.information")
    Observable<InfoBack> info(@Query("sign") String sign, @Body InfoBean bean);

    @POST("order.desc")
    Observable<DescBack> desc(@Query("sign") String sign, @Body DescBean bean);

}
