package entity;

public class Talk {
    private String id;
    private String content;
    private String time;
    private String username;

    public Talk() {
    }

    public Talk(String id, String content, String time, String username) {
        this.id = id;
        this.content = content;
        this.time = time;
        this.username = username;
    }

    public Talk(String content, String time, String username) {
        this.content = content;
        this.time = time;
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
