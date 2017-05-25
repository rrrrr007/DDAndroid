package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/5/25 0025.
 */

public class SupplierBack {

    /**
     * code : 0
     * data : {"avatar":"http://wx.qlogo.cn/mmopen/vi_32/dTyEibufUO2hTjbA0vF4cByND3N9vIxiac32C2uvibqo6KcsfPpeXpfd89ldWOhLibyQqic1ZiaSgHLUck8xhpvDydxQ/0","name":"西门大官银","sell_status":1,"supplier_id":100003}
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
         * avatar : http://wx.qlogo.cn/mmopen/vi_32/dTyEibufUO2hTjbA0vF4cByND3N9vIxiac32C2uvibqo6KcsfPpeXpfd89ldWOhLibyQqic1ZiaSgHLUck8xhpvDydxQ/0
         * name : 西门大官银
         * sell_status : 1
         * supplier_id : 100003
         */

        private String avatar;
        private String name;
        private int sell_status;
        private int supplier_id;

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSell_status() {
            return sell_status;
        }

        public void setSell_status(int sell_status) {
            this.sell_status = sell_status;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }
    }
}
