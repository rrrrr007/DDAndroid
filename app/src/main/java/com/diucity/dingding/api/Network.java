package com.diucity.dingding.api;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

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
    private static Api api;
    private static OkHttpClient okHttpClient;
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
        private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static Api getApi() {
        if (okHttpClient == null) {
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[0];
                }
            };

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");

                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (KeyManagementException e) {
                e.printStackTrace();
            }
            HostnameVerifier DO_NOT_VERIFY = (hostname, session) -> true;
            okHttpClient = new OkHttpClient.Builder()
                    .sslSocketFactory(sslContext.getSocketFactory())
                    .hostnameVerifier(DO_NOT_VERIFY)
                    .build();
        }
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Api.BASEURL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            api = retrofit.create(Api.class);


        }
        return api;
    }

    public static Subscription subscribe(rx.Observable observable, Observer observer) {
        if (observable == null) {
            return null;
        }
        Subscription subscribe = observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return subscribe;
    }
}
