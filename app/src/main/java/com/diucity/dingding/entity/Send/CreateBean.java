package com.diucity.dingding.entity.Send;

import com.diucity.dingding.utils.SignUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/5/19 0019.
 */

public class CreateBean {
    public CreateBean(int recycler_id, String auth_code, int supplier_id, double longitude, double latitude, List<ScrapsBean> scraps) {
        this.timestamp = System.currentTimeMillis();
        this.nonce = SignUtils.getUUID();
        this.recycler_id = recycler_id;
        this.auth_code = SignUtils.authCode(getTimestamp(), auth_code);
        this.supplier_id = supplier_id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.scraps = scraps;
    }

    /**
     * timestamp : 373091747
     * nonce : beab54a1-fd7a-442f-bb26-201f5b98eb69
     * recycler_id : 1027301
     * auth_code : sjsj2010A20suycxx
     * supplier_id : 1027301
     * longitude : 23.1037
     * latitude : 38.3926
     * scraps : [{"scrap_id":1,"quantity":3.2},{"scrap_id":2,"quantity":15}]
     */

    private long timestamp;
    private String nonce;
    private int recycler_id;
    private String auth_code;
    private int supplier_id;
    private double longitude;
    private double latitude;
    private List<ScrapsBean> scraps;

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

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
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

    public List<ScrapsBean> getScraps() {
        return scraps;
    }

    public void setScraps(List<ScrapsBean> scraps) {
        this.scraps = scraps;
    }

    public static class ScrapsBean {
        public ScrapsBean(int scrap_id, double quantity) {
            this.scrap_id = scrap_id;
            this.quantity = quantity;
        }

        /**
         * scrap_id : 1
         * quantity : 3.2
         */

        private int scrap_id;
        private double quantity;

        public int getScrap_id() {
            return scrap_id;
        }

        public void setScrap_id(int scrap_id) {
            this.scrap_id = scrap_id;
        }

        public double getQuantity() {
            return quantity;
        }

        public void setQuantity(double quantity) {
            this.quantity = quantity;
        }
    }
}
