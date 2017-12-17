package bean;

public class UserInfo {
    private Integer id;
    private String userName;
    private String password;
    private String realName;
    private String registerDate;
    private boolean prohibitLogin;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isProhibitLogin() {
        return prohibitLogin;
    }

    public void setProhibitLogin(boolean prohibitLogin) {
        this.prohibitLogin = prohibitLogin;
    }

    public String toString() {
        return id+"#"+userName+"#"+password+"#"+realName+"#"+registerDate+"#"+prohibitLogin;
    }

}
