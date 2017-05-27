package com.diucity.dingding.entity.Back;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class CreateBack implements Serializable {

    /**
     * code : 0
     * data : {"order_id":3729176,"order_no":"20170301020","total_price":327.15,"scraps":[{"scrap_id":1,"unit_price":0.46,"quantity":3.2,"total_price":1.42},{"scrap_id":2,"unit_price":0.46,"quantity":3.2,"total_price":1.42}]}
     */

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

    public static class DataBean implements Serializable {
        /**
         * order_id : 3729176
         * order_no : 20170301020
         * total_price : 327.15
         * scraps : [{"scrap_id":1,"unit_price":0.46,"quantity":3.2,"total_price":1.42},{"scrap_id":2,"unit_price":0.46,"quantity":3.2,"total_price":1.42}]
         */

        private int order_id;
        private String order_no;
        private double total_price;
        private List<ScrapsBean> scraps;

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public double getTotal_price() {
            return total_price;
        }

        public void setTotal_price(double total_price) {
            this.total_price = total_price;
        }

        public List<ScrapsBean> getScraps() {
            return scraps;
        }

        public void setScraps(List<ScrapsBean> scraps) {
            this.scraps = scraps;
        }

        public static class ScrapsBean implements Serializable {
            /**
             * scrap_id : 1
             * unit_price : 0.46
             * quantity : 3.2
             * total_price : 1.42
             */

            private int scrap_id;
            private double unit_price;
            private double quantity;
            private double total_price;

            public int getScrap_id() {
                return scrap_id;
            }

            public void setScrap_id(int scrap_id) {
                this.scrap_id = scrap_id;
            }

            public double getUnit_price() {
                return unit_price;
            }

            public void setUnit_price(double unit_price) {
                this.unit_price = unit_price;
            }

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public double getTotal_price() {
                return total_price;
            }

            public void setTotal_price(double total_price) {
                this.total_price = total_price;
            }
        }
    }
}
