package entity;

public class Course {
    private String id;
    private String title;
    private String t_uid;
    private String img;
    private String content;

    public Course() {
    }

    public Course(String id, String title, String t_uid, String img, String content) {
        this.id = id;
        this.title = title;
        this.t_uid = t_uid;
        this.img = img;
        this.content = content;
    }

    public Course(String title, String t_uid, String img, String content) {
        this.title = title;
        this.t_uid = t_uid;
        this.img = img;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getT_uid() {
        return t_uid;
    }

    public void setT_uid(String t_uid) {
        this.t_uid = t_uid;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
