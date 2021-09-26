package co.com.luisprmat.training.attendance.model.network.responses.apiError;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Error {
    private List<String> email;
    private List<String> password;

    @SerializedName("device_name")
    private List<String> deviceName;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }

    public List<String> getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(List<String> deviceName) {
        this.deviceName = deviceName;
    }
}
