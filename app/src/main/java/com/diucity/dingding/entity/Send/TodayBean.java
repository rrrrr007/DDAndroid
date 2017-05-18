package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class TodayBean {

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private double longitude;
    private double latitude;

    public TodayBean(int recycler_id, double longitude, double latitude) {
        this.timestamp = System.currentTimeMillis();
        this.nonce =  SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.longitude = longitude;
        this.latitude = latitude;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
