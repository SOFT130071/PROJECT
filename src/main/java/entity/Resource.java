package entity;

public class Resource {
    private String c_id;
    private String content;
    private String number;

    public Resource() {
    }

    public Resource(String c_id, String content, String number) {
        this.c_id = c_id;
        this.content = content;
        this.number = number;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
