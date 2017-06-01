package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/4/10 0010.
 */

public class LoginBack {
    private int code;
    private DataBean data;
    private String message;
    private int money_max;

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

    public int getMoney_max() {
        return money_max;
    }

    public void setMoney_max(int money_max) {
        this.money_max = money_max;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        private int recycler_id;
        private String auth_token;
        private String name;
        private String mobile;
        private double score;
        private int pay_security;

        public int getRecycler_id() {
            return recycler_id;
        }

        public void setRecycler_id(int recycler_id) {
            this.recycler_id = recycler_id;
        }

        public String getAuth_token() {
            return auth_token;
        }

        public void setAuth_token(String auth_token) {
            this.auth_token = auth_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public int getPay_security() {
            return pay_security;
        }

        public void setPay_security(int pay_security) {
            this.pay_security = pay_security;
        }
    }
}
