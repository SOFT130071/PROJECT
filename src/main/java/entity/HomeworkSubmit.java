package entity;

public class HomeworkSubmit {
    private String s_id;
    private String hw_id;
    private String content;
    private String score;

    public HomeworkSubmit() {
    }

    public HomeworkSubmit(String s_id, String hw_id, String content, String score) {
        this.s_id = s_id;
        this.hw_id = hw_id;
        this.content = content;
        this.score = score;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getHw_id() {
        return hw_id;
    }

    public void setHw_id(String hw_id) {
        this.hw_id = hw_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

}
