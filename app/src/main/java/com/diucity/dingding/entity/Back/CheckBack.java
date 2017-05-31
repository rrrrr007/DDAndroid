package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/5/31 0031.
 */

public class CheckBack {

    /**
     * code : 0.0
     * data : {"check_code":2,"check_message":"支付中","order_id":513}
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
         * check_code : 2.0
         * check_message : 支付中
         * order_id : 513.0
         */

        private int check_code;
        private String check_message;
        private int order_id;

        public int getCheck_code() {
            return check_code;
        }

        public void setCheck_code(int check_code) {
            this.check_code = check_code;
        }

        public String getCheck_message() {
            return check_message;
        }

        public void setCheck_message(String check_message) {
            this.check_message = check_message;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }
    }
}
