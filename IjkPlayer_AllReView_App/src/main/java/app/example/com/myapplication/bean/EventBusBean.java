package app.example.com.myapplication.bean;

/**
 * Eventbus 实体类
 */
public class EventBusBean {
    private String  position;
    private String  text;

    public EventBusBean(String position, String text) {
        this.position = position;
        this.text = text;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
