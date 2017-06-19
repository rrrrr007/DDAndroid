package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class DescBack {


    /**
     * code : 0
     * data : {"order_id":75,"order_no":"1497580458086456004","scraps":[{"scrap_id":0,"name":"","unit":"","quantity":0,"amount":0,"price":0},{"scrap_id":0,"name":"","unit":"","quantity":0,"amount":0,"price":0},{"scrap_id":20003,"name":"易拉罐","unit":"个","quantity":2,"amount":1.6,"price":0.8},{"scrap_id":20001,"name":"纸板","unit":"斤","quantity":1,"amount":0.31,"price":0.31},{"scrap_id":2922,"name":"每单奖励","unit":"10%","quantity":1,"amount":0.06,"price":0.06}],"total_price":1.91,"trading_time":1497580475}
     * message :
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

    public static class DataBean {
        /**
         * order_id : 75
         * order_no : 1497580458086456004
         * scraps : [{"scrap_id":0,"name":"","unit":"","quantity":0,"amount":0,"price":0},{"scrap_id":0,"name":"","unit":"","quantity":0,"amount":0,"price":0},{"scrap_id":20003,"name":"易拉罐","unit":"个","quantity":2,"amount":1.6,"price":0.8},{"scrap_id":20001,"name":"纸板","unit":"斤","quantity":1,"amount":0.31,"price":0.31},{"scrap_id":2922,"name":"每单奖励","unit":"10%","quantity":1,"amount":0.06,"price":0.06}]
         * total_price : 1.91
         * trading_time : 1497580475
         */

        private int order_id;
        private String order_no;
        private double total_price;
        private double bonusTotal;
        private double itemTotal;
        private long trading_time;
        private List<ScrapsBean> scraps;

        public double getBonusTotal() {
            return bonusTotal;
        }

        public void setBonusTotal(double bonusTotal) {
            this.bonusTotal = bonusTotal;
        }

        public double getItemTotal() {
            return itemTotal;
        }

        public void setItemTotal(double itemTotal) {
            this.itemTotal = itemTotal;
        }

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

        public long getTrading_time() {
            return trading_time * 1000;
        }

        public void setTrading_time(long trading_time) {
            this.trading_time = trading_time;
        }

        public List<ScrapsBean> getScraps() {
            return scraps;
        }

        public void setScraps(List<ScrapsBean> scraps) {
            this.scraps = scraps;
        }

        public static class ScrapsBean {
            /**
             * scrap_id : 0
             * name :
             * unit :
             * quantity : 0
             * amount : 0
             * price : 0
             */

            private int scrap_id;
            private String name;
            private String unit;
            private double quantity;
            private double amount;
            private double price;

            public int getScrap_id() {
                return scrap_id;
            }

            public void setScrap_id(int scrap_id) {
                this.scrap_id = scrap_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public double getQuantity() {
                return quantity;
            }

            public void setQuantity(double quantity) {
                this.quantity = quantity;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }
        }
    }
}
