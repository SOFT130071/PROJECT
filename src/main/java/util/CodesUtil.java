package util;

public class CodesUtil {
    //TODO: 这个地方应该写成配置文件，但是我懒了
    public static String coToStr(int code) {
        switch (code) {
            case 0x010100: // log ok
                return "Log in success!";
            case 0x010101: // wrong username
                return "Username does not exist1";
            case 0x010102: // wrong password
                return "Wrong password!";
            case 0x010103:
                return "Log failed!";
            case 0x010104:
                return "Not logged!";
            case 0x010105:
                return "Logged!";
            case 0x010106:
                return "Logout success!";
            case 0x010107:
                return "Logout failed!";
            case 0x010201:
                return "User exist!";
            case 0x010202:
                return "Appending user failed!";
            case 0x010203:
                return "Wrong verification code!";
            case 0x010200:
                return "Appending user success!";
            case 0x010300:
                return "Mail sent!";
            case 0x010301:
                return "Mail failed!";
            case 0x020100:
                return "Create course success!";
            case 0x020101:
                return "create failed.";
            case 0x020200:
                return "delete course success.";
            case 0x020201:
                return "course page delete failed.";
            case 0x020202:
                return "homework delete failed.";
            case 0x020203:
                return "answer delete failed.";
            case 0x020204:
                return "course table delete failed.";
            case 0x020205:
                return "record delete failed.";
            case 0x020206:
                return "resource delete failed.";
            case 0x020207:
                return "course delete failed.";
            case 0x020300:
                return "course modify success.";
            case 0x020301:
                return "course modify failed.";
        }
        return "Unknown error!";
    }

}
