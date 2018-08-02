package entity;

public class ChooseCourse {
    private String c_id;
    private String s_id;

    public ChooseCourse() {
    }

    public ChooseCourse(String c_id, String s_id) {
        this.c_id = c_id;
        this.s_id = s_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }
}
