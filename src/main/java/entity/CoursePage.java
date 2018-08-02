package entity;

public class CoursePage {
    private String id;
    private String c_id;
    private String number;
    private String title;
    private String content;
    private String link;

    public CoursePage() {
    }

    public CoursePage(String c_id, String number, String title, String content, String link) {
        this.c_id = c_id;
        this.number = number;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public CoursePage(String id, String c_id, String number, String title, String content, String link) {
        this.id = id;
        this.c_id = c_id;
        this.number = number;
        this.title = title;
        this.content = content;
        this.link = link;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
