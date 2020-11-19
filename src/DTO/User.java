package DTO;

/**
 *
 * @author HoaDD
 */
public class User {
    private String userName;
    private String fullName;
    private String password;
    private boolean status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User(String userName, String fullName, String password, boolean status) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.status = status;
    }
}
