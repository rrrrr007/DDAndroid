package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/5/18 0018.
 */

public class ListBack {

    /**
     * code : 0.0
     * data : {"items":[{"bill_id":1,"type":1,"time":53,"amount":30,"explain":"账单的描述说明"},{"bill_id":2,"type":1,"time":53,"amount":30,"explain":"账单的描述说明"},{"bill_id":3,"type":1,"time":53,"amount":30,"explain":"账单的描述说明"}]}
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
        private List<ItemsBean> items;

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * bill_id : 1.0
             * type : 1.0
             * time : 53.0
             * amount : 30.0
             * explain : 账单的描述说明
             */

            private int bill_id;
            private int type;
            private long time;
            private double amount;
            private String explain;

            public int getBill_id() {
                return bill_id;
            }

            public void setBill_id(int bill_id) {
                this.bill_id = bill_id;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public long getTime() {
                return time*1000;
            }

            public void setTime(long time) {
                this.time = time;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
                this.amount = amount;
            }

            public String getExplain() {
                return explain;
            }

            public void setExplain(String explain) {
                this.explain = explain;
            }
        }
    }
}
