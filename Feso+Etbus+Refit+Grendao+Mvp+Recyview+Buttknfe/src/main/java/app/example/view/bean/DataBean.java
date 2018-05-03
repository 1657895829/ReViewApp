package app.example.view.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 数据库不能操作内部类，需把接口返回的实体数据抽取为1个
 */
@Entity
public class DataBean {
        /**
         * uniquekey : a020de72119371364c1bde494ca68164
         * title : 蔡英文叫嚣"绝不屈服" 马英九喊话：别指望美国人
         * date : 2018-05-02 19:34
         * category : 头条
         * author_name : 海外网
         * url : http://mini.eastday.com/mobile/180502193403736.html
         * thumbnail_pic_s : http://00.imgmini.eastday.com/mobile/20180502/20180502193403_a4773c3152cd10d99b0e43802d363e7d_1_mwpm_03200403.jpg
         * thumbnail_pic_s02 : http://00.imgmini.eastday.com/mobile/20180502/20180502193403_a4773c3152cd10d99b0e43802d363e7d_3_mwpm_03200403.jpg
         * thumbnail_pic_s03 : http://00.imgmini.eastday.com/mobile/20180502/20180502193403_a4773c3152cd10d99b0e43802d363e7d_2_mwpm_03200403.jpg
         */
        @Id(autoincrement = true)
        private Long   tid;
        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;

        @Generated(hash = 1324645534)
        public DataBean(Long tid, String uniquekey, String title, String date, String category, String author_name, String url,
                String thumbnail_pic_s, String thumbnail_pic_s02, String thumbnail_pic_s03) {
            this.tid = tid;
            this.uniquekey = uniquekey;
            this.title = title;
            this.date = date;
            this.category = category;
            this.author_name = author_name;
            this.url = url;
            this.thumbnail_pic_s = thumbnail_pic_s;
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

        @Generated(hash = 908697775)
        public DataBean() {
        }

        public String getUniquekey() {
            return uniquekey;
        }

        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getAuthor_name() {
            return author_name;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }

        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }

        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }

    @Override
    public String toString() {
        return "DataBean{" +
                "uniquekey='" + uniquekey + '\'' +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", author_name='" + author_name + '\'' +
                ", url='" + url + '\'' +
                ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                '}';
    }

    public Long getTid() {
        return this.tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }
}
