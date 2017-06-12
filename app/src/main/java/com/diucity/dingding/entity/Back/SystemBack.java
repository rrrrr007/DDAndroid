package com.diucity.dingding.entity.Back;

import java.util.List;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class SystemBack {

    /**
     * code : 0.0
     * data : {"notices":[{"notice_id":190,"send_time":"2017-05-23 17:46:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/425b1c97b1fc23cbaa18a62551e24a72.html","read":0},{"notice_id":189,"send_time":"2017-05-23 17:45:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/6268d1e456b7a062ee60071150e31af5.html","read":0},{"notice_id":183,"send_time":"2017-05-23 17:38:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/7d3534f39448a3132a58ec5c6d8904ff.html","read":0},{"notice_id":197,"send_time":"2017-05-23 10:02:23","title":"测试1111","description":"菲菲","covers_uri":"upload/material/material_1/c65e6ac2f348ec3989269cd6f24eb2a6.jpg","target_uri":"http://images.diucity.com/notice/conf/64b553eb1d8a5059633b15ebd5117e7e.html","read":0},{"notice_id":195,"send_time":"2017-05-23 10:00:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/787f5b09caf31ec330a8132fb6fb5ab4.html","read":0},{"notice_id":196,"send_time":"2017-05-23 10:00:23","title":"11111111","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/241d7f85fc41e8865a6b49af6d697d87.html","read":0},{"notice_id":192,"send_time":"2017-05-23 09:58:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/547df364d787ebc1aecfa5dbe4cb2c1f.html","read":0},{"notice_id":193,"send_time":"2017-05-23 09:58:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/3d99135d43a2ccd0f60d9c8c4a7d86c3.html","read":0},{"notice_id":191,"send_time":"2017-05-23 09:53:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/53f8fcd2b410ed4799c53ffed9fd1711.html","read":0},{"notice_id":185,"send_time":"2017-05-23 09:39:23","title":"cleinet.ping","description":"","covers_uri":"","target_uri":"http://images.diucity.com/notice/conf/61b1cad9424e1a82bee5d2949bc45985.html","read":0}]}
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
        private List<NoticesBean> notices;

        public List<NoticesBean> getNotices() {
            return notices;
        }

        public void setNotices(List<NoticesBean> notices) {
            this.notices = notices;
        }

        public static class NoticesBean {
            /**
             * notice_id : 190.0
             * send_time : 2017-05-23 17:46:23
             * title : cleinet.ping
             * description :
             * covers_uri :
             * target_uri : http://images.diucity.com/notice/conf/425b1c97b1fc23cbaa18a62551e24a72.html
             * read : 0.0
             */

            private int notice_id;
            private long send_time;
            private String title;
            private String description;
            private String covers_uri;
            private String target_uri;
            private int read;

            public int getNotice_id() {
                return notice_id;
            }

            public void setNotice_id(int notice_id) {
                this.notice_id = notice_id;
            }

            public long getSend_time() {
                return send_time*1000;
            }

            public void setSend_time(long send_time) {
                this.send_time = send_time;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getCovers_uri() {
                return covers_uri;
            }

            public void setCovers_uri(String covers_uri) {
                this.covers_uri = covers_uri;
            }

            public String getTarget_uri() {
                return target_uri;
            }

            public void setTarget_uri(String target_uri) {
                this.target_uri = target_uri;
            }

            public int getRead() {
                return read;
            }

            public void setRead(int read) {
                this.read = read;
            }
        }
    }
}
