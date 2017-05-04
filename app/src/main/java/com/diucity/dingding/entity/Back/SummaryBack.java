package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/5/3 0003.
 */

public class SummaryBack {


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
         * card_bank :
         * card_number_4 :
         * income : 30.0
         */

        private String card_bank;
        private String card_number_4;
        private double income;

        public String getCard_bank() {
            return card_bank;
        }

        public void setCard_bank(String card_bank) {
            this.card_bank = card_bank;
        }

        public String getCard_number_4() {
            return card_number_4;
        }

        public void setCard_number_4(String card_number_4) {
            this.card_number_4 = card_number_4;
        }

        public double getIncome() {
            return income;
        }

        public void setIncome(double income) {
            this.income = income;
        }
    }
}
