











































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
    @SQLInteger
    private String logged;

    public User(String... uid_username_nickname_email_password_type_logged) {
        switch (uid_username_nickname_email_password_type_logged.length) {
            case 6:
                logged = uid_username_nickname_email_password_type_logged[5];
            case 5:
                password = uid_username_nickname_email_password_type_logged[4];
            case 4:
                email = uid_username_nickname_email_password_type_logged[3];
            case 3:
                nickname = uid_username_nickname_email_password_type_logged[2];
            case 2:
                username = uid_username_nickname_email_password_type_logged[1];
            case 1:
                uid = uid_username_nickname_email_password_type_logged[0];
            default:
                break;
        }
    }

    public String getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getLogged() {
        return logged;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoged(String logged) {
        this.logged = logged;
    }
}
