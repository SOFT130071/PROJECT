package entity;

import util.annotation.Constraints;
import util.annotation.DBTable;
import util.annotation.SQLInteger;
import util.annotation.SQLString;

@DBTable("user")
public class User {

    @SQLInteger(constraint = @Constraints(primaryKey = true, unique = true))
    private String uid;
    @SQLString(constraint = @Constraints(unique = true))
    private String username;
    @SQLString
    private String nickname;
    @SQLString
    private String email;
    @SQLString
    private String password;

    public User(String uid, String username, String nickname, String password, String email) {
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
    }

    public User() {
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
