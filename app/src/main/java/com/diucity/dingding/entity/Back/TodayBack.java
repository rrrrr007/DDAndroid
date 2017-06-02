package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/4/26 0026.
 */

public class TodayBack {


    private int code;
    private DataBean data;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private List<ScrapsBean> scraps;

        public List<ScrapsBean> getScraps() {
            return scraps;
        }

        public void setScraps(List<ScrapsBean> scraps) {
            this.scraps = scraps;
        }

        public static class ScrapsBean {


            private int scrap_id;
            private double buy_price;
            private double sell_price;

            public int getScrap_id() {
                return scrap_id;
            }

            public void setScrap_id(int scrap_id) {
                this.scrap_id = scrap_id;
            }

            public double getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(double buy_price) {
                this.buy_price = buy_price;
            }

            public double getSell_price() {
                return sell_price;
            }

            public void setSell_price(double sell_price) {
                this.sell_price = sell_price;
            }
        }
    }
}
