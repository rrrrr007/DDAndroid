package com.diucity.dingding.entity;



import java.util.List;

/**
 * Created by Administrator on 2017/2/13 0013.
 */

public class MessageBack {


    private ResultBean result;
    private List<ItemsBean> items;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ResultBean {


        private int code;
        private String message;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public static class ItemsBean {


        private String columnName;
        private int columnType;
        private int order;
        private List<Articles> articles;

        public String getColumnName() {
            return columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public int getColumnType() {
            return columnType;
        }

        public void setColumnType(int columnType) {
            this.columnType = columnType;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public List<Articles> getArticles() {
            return articles;
        }

        public void setArticles(List<Articles> articles) {
            this.articles = articles;
        }


    }

}
