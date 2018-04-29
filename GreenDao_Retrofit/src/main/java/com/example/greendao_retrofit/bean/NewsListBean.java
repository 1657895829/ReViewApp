package com.example.greendao_retrofit.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

//把接口返回的数据bean中的实体数据类抽取另建1个类，构建为表结构
@Entity
public class NewsListBean {
    /**
     * ctime : 2018-04-26
     * title : 88岁原配写书呛80岁小三，琼瑶的爱情罗生门要讲到山无棱天地合
     * description : 孟大明白
     * picUrl : https://zxpic.gtimg.com/infonew/0/wechat_pics_-62928965.jpg/640
     * url : https://mp.weixin.qq.com/s?__biz=MzI2ODA5MjgyOA==&idx=1&mid=2650267480&sn=9737bb6e8b857afbabacf467488304c9
     */
    @Id(autoincrement = true)
    private Long      id;
    private String ctime;
    private String title;
    private String description;
    private String picUrl;
    private String url;
    @Generated(hash = 1823946131)
    public NewsListBean(Long id, String ctime, String title, String description, String picUrl, String url) {
        this.id = id;
        this.ctime = ctime;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.url = url;
    }
    @Generated(hash = 1159480764)
    public NewsListBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCtime() {
        return this.ctime;
    }
    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPicUrl() {
        return this.picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "NewsListBean{" +
                "id=" + id +
                ", ctime='" + ctime + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}