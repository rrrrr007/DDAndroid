package com.diucity.dingding.api;



import com.diucity.dingding.entity.MessageBack;

import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by sushuai on 2016/3/25.
 */
public interface MsgApi {
    String BASEURL = "http://www.diucity.com:1601/api/";
    @POST("message/msgs")
    Observable<MessageBack> msg(@QueryMap Map<String, String> param);
}
