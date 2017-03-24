package com.diucity.dingding.api;


import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Network {
    private static MsgApi msgApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static MsgApi getApi() {
        if (msgApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(MsgApi.BASEURL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();

            msgApi = retrofit.create(MsgApi.class);
        }
        return msgApi;
    }
    public static Map getMap(String key){
        HashMap<String, String> map = new HashMap<>();
                map.put(key,"");
        return map;
    }

    public static Subscription subscribe(rx.Observable observable, Observer observer) {
        if (observable==null){
            return null;
        }
        Subscription subscribe = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return subscribe;
    }
}
