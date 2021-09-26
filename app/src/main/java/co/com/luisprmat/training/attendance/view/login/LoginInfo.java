package co.com.luisprmat.training.attendance.view.login;

import com.google.gson.annotations.SerializedName;

public class LoginInfo {
    private String email;
    private String password;

    @SerializedName("device_name")
    private String deviceName;

    public LoginInfo(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public LoginInfo(String email, String password, String deviceName) {
        this.email = email;
        this.password = password;
        this.deviceName = deviceName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
}
