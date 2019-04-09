package com.example.ycx36.recruitment.model.dataBean;

import java.util.List;

public class Gson_NewsDataBean {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 18
         * title : 我校举办2017年青年教师教学竞赛活动
         * description : 为进一步加强教师队伍建设，提高课堂教学水平，以“上好一门课”为目标，以提升青年教师教学能力和业务水平为目的，学校组织开展了2017年青年教师教学竞赛活动。
         * pic : http://rsc.fiysj.cn/uploads/images/QQ截图20180416212636.jpg
         * url : http://rsc.fiysj.cn/mobile/post/18
         * author : 人事处
         * is_recomend : 1
         * looks : 0
         */

        private int id;
        private String title;
        private String description;
        private String pic;
        private String url;
        private String author;
        private int is_recomend;
        private int looks;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getIs_recomend() {
            return is_recomend;
        }

        public void setIs_recomend(int is_recomend) {
            this.is_recomend = is_recomend;
        }

        public int getLooks() {
            return looks;
        }

        public void setLooks(int looks) {
            this.looks = looks;
        }
    }
}
