package co.com.luisprmat.training.attendance.view.login;

public class LoginInfo {
    private final String email;
    private final String password;

    public LoginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
