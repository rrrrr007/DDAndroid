package com.diucity.dingding.entity.Back;

/**
 * Created by Administrator on 2017/5/16 0016.
 */

public class XXBack {


    /**
     * code : 0
     * data : {"staff_id":10001,"auth_token":"993jal-2lakd2sj"}
     */

    private String code;
    private String message;
    private DataBean data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * staff_id : 10001
         * auth_token : 993jal-2lakd2sj
         */

        private int staff_id;
        private String auth_token;

        public int getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(int staff_id) {
            this.staff_id = staff_id;
        }

        public String getAuth_token() {
            return auth_token;
        }

        public void setAuth_token(String auth_token) {
            this.auth_token = auth_token;
        }
    }
}
