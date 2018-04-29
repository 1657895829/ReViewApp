package com.example.greendao_retrofit.bean;

import java.util.List;

/**
 * 接口返回的数据
 */
public class NewsBean {

    /**
     * code : 200
     * msg : success
     * newslist : [{"ctime":"2018-04-26","title":"88岁原配写书呛80岁小三，琼瑶的爱情罗生门要讲到山无棱天地合","description":"孟大明白","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62928965.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzI2ODA5MjgyOA==&idx=1&mid=2650267480&sn=9737bb6e8b857afbabacf467488304c9"},{"ctime":"2018-04-26","title":"把朋友推向马路、给同事投毒\u2026\u2026生而为人，请你务必善良！","description":"周冲的影像声色","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62927038.static/640","url":"https://mp.weixin.qq.com/s?__biz=MzA4OTI5NDMyNw==&idx=3&mid=2651698128&sn=b202d1ec8fe888febf6c5c1fbecf2198"},{"ctime":"2018-04-26","title":"道明寺让推特都瘫痪，《流星花园》一碰就燃","description":"世界时装之苑ELLE","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62928851.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MTY3OTM1NTY2MQ==&idx=3&mid=2654359813&sn=2be2970c6da57c9629e4fa0eabe40a02"},{"ctime":"2018-04-25","title":"努力了，问心无愧； 其他的，交给命运","description":"微杂志","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62928416.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MzAwOTEyMjgyOA==&idx=4&mid=2652547536&sn=b4041566a5aa69305dadb9afa094d6b0"},{"ctime":"2018-04-25","title":"一日一诗：\u201c在我有生之年/我多想背着你去看海\u201dll孙显平：母亲河（读诗版）","description":"冯站长之家","picUrl":"https://t1.qpic.cn/mblogpic/48da2c609d0a7f9d083e/2000","url":"https://mp.weixin.qq.com/s?__biz=MzA5OTQyMDgyOQ==&idx=3&mid=2652565798&sn=fb07ebcbe44dba153f749a0710bddabe"},{"ctime":"2018-04-25","title":"戳进来 | 看包识女人，你的CHANEL包袋暴露了你的性格！","description":"时尚COSMO","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-61887621.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=Mjc0NzU0MzU0MA==&idx=2&mid=2653062534&sn=25bae91d48a2617f5be175a9bc104f00"},{"ctime":"2018-04-20","title":"\u201c扒小号\u201d的套路你们玩不腻吗？","description":"南方人物周刊","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62927756.jpg/640","url":"https://mp.weixin.qq.com/s?__biz=MTY0MzI5NDcwMQ==&idx=1&mid=2651201157&sn=e6c9f31b41bf967a1c6fae7b9b2b9a5a"},{"ctime":"2018-04-20","title":"聚焦 | 超音速旅行未终结 德媒：中国正雄心勃勃力争\u201c世界最快\u201d","description":"参考消息","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-34811459.static/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5MzA0MTg2MA==&idx=4&mid=2653833128&sn=91ddffb773e4f574356b952cd4b1f021"},{"ctime":"2018-04-20","title":"\u201c姓m的这位\u201d！麦当劳在KFC旁打广告，竟惊动多地公安","description":"南方都市报","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62909422.static/640","url":"https://mp.weixin.qq.com/s?__biz=MTk1MjIwODAwMQ==&idx=2&mid=2650760314&sn=82870875b96d11fe290c78a27dc3ca24"},{"ctime":"2018-04-20","title":"主人只要每次一回家，把鞋子一脱，家里猫就是这反应，啧啧啧....","description":"大爱猫咪控","picUrl":"https://zxpic.gtimg.com/infonew/0/wechat_pics_-62927694.static/640","url":"https://mp.weixin.qq.com/s?__biz=MjM5MDAzNDcwMA==&idx=5&mid=2651917742&sn=39fa5e1381a3c86dd3e41056c414ff44"}]
     */

    private int code;
    private String msg;
    private List<NewsListBean> newslist;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NewsListBean> getNewslist() {
        return newslist;
    }

    public void setNewslist(List<NewsListBean> newslist) {
        this.newslist = newslist;
    }

}
