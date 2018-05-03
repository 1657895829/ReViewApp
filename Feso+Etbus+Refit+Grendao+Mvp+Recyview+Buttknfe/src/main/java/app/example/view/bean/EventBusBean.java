package app.example.view.bean;

/**
 * EventBus传值的实体类
 */
public class EventBusBean {
    public String url;
    public String title;

    public EventBusBean(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
