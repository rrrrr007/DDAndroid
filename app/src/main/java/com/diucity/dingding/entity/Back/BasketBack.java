package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class BasketBack {


    private int code;
    private Data data;
    private String message;

    public double getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class Data {
        private List<DataBean> data;

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {

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
}
