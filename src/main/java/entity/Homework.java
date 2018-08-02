package entity;

public class Homework {
    private String id;
    private String beg_time;
    private String title;
    private String content;
    private String end_time;
    private String c_id;

    public Homework() {
    }

    public Homework(String id, String beg_time, String title, String content, String end_time, String c_id) {
        this.id = id;
        this.beg_time = beg_time;
        this.title = title;
        this.content = content;
        this.end_time = end_time;
        this.c_id = c_id;
    }

    public Homework(String beg_time, String title, String content, String end_time, String c_id) {
        this.beg_time = beg_time;
        this.title = title;
        this.content = content;
        this.end_time = end_time;
        this.c_id = c_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBeg_time() {
        return beg_time;
    }

    public void setBeg_time(String beg_time) {
        this.beg_time = beg_time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }
}
