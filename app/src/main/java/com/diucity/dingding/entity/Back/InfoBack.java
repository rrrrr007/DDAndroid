package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26 0026.
 */

public class InfoBack {

    /**
     * code : 0.0
     * data : {"amount":1.4,"bonus":0,"bonuses":[],"order_id":610,"order_no":"1495769174934710842","scraps":[{"scrap_id":398,"name":"","unit":"","quantity":1,"amount":0.2},{"scrap_id":399,"name":"","unit":"","quantity":2,"amount":0.6},{"scrap_id":400,"name":"","unit":"","quantity":3,"amount":0.6000000000000001}],"supplier_icon":"http://wx.qlogo.cn/mmopen/vi_32/dTyEibufUO2hTjbA0vF4cByND3N9vIxiac32C2uvibqo6KcsfPpeXpfd89ldWOhLibyQqic1ZiaSgHLUck8xhpvDydxQ/0","supplier_id":100003,"supplier_name":"西门大官银","time":1.49576918E9,"type":1}
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


        private double amount;
        private double bonus;
        private double order_id;
        private String order_no;
        private String supplier_icon;
        private double supplier_id;
        private String supplier_name;
        private long time;
        private double type;
        private List<?> bonuses;
        private List<ScrapsBean> scraps;

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public double getBonus() {
            return bonus;
        }

        public void setBonus(double bonus) {
            this.bonus = bonus;
        }

        public double getOrder_id() {
            return order_id;
        }

        public void setOrder_id(double order_id) {
            this.order_id = order_id;
        }

        public String getOrder_no() {
            return order_no;
        }

        public void setOrder_no(String order_no) {
            this.order_no = order_no;
        }

        public String getSupplier_icon() {
            return supplier_icon;
        }

        public void setSupplier_icon(String supplier_icon) {
            this.supplier_icon = supplier_icon;
        }

        public double getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(double supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSupplier_name() {
            return supplier_name;
        }

        public void setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
        }

        public long getTime() {
            return time * 1000;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public double getType() {
            return type;
        }

        public void setType(double type) {
            this.type = type;
        }

        public List<?> getBonuses() {
            return bonuses;
        }

        public void setBonuses(List<?> bonuses) {
            this.bonuses = bonuses;
        }

        public List<ScrapsBean> getScraps() {
            return scraps;
        }

        public void setScraps(List<ScrapsBean> scraps) {
            this.scraps = scraps;
        }

        public static class ScrapsBean {
            /**
             * scrap_id : 398.0
             * name :
             * unit :
             * quantity : 1.0
             * amount : 0.2
             */

            private int scrap_id;
            private String name;
            private String unit;
            private double quantity;
            private double amount;

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
        }
    }
}
