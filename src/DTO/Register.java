package DTO;

/**
 *
 * @author HoaDD
 */
public class Register {
    private String user;
    private String password;
    private String confirm;
    private String fullname;
    private boolean status = true;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Register(String user, String password, String confirm, String fullname) {
        this.user = user;
        this.password = password;
        this.confirm = confirm;
        this.fullname = fullname;
    }
}
