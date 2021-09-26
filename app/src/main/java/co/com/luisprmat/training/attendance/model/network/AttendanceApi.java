package co.com.luisprmat.training.attendance.model.network;

import co.com.luisprmat.training.attendance.model.network.responses.StudentsResponse;
import co.com.luisprmat.training.attendance.model.network.responses.TokenResponse;
import co.com.luisprmat.training.attendance.view.login.LoginInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AttendanceApi {
    @GET("students")
    Call<StudentsResponse> getData();

    @Headers({"Accept: application/json"})
    @POST("login")
    Call<TokenResponse> login(@Body LoginInfo info);
}
