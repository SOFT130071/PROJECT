package entity;

public class Statistics {
    private String s_id;
    private String c_id;
    private String cp_id;

    public Statistics() {
    }

    public Statistics(String s_id, String c_id, String cp_id) {
        this.s_id = s_id;
        this.c_id = c_id;
        this.cp_id = cp_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getC_id() {
        return c_id;
    }

    public void setC_id(String c_id) {
        this.c_id = c_id;
    }

    public String getCp_id() {
        return cp_id;
    }

    public void setCp_id(String cp_id) {
        this.cp_id = cp_id;
    }
}
