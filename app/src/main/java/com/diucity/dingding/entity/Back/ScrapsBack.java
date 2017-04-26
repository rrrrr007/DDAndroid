package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/4/25 0025.
 */

public class ScrapsBack {
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
        private List<Scraps> scraps;

        public List<Scraps> getScraps() {
            return scraps;
        }

        public void setScraps(List<Scraps> scraps) {
            this.scraps = scraps;
        }

        public static class Scraps {
            private int scrap_id;
            private String name;
            private String unit;

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
        }
    }
}
