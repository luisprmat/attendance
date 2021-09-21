package co.com.luisprmat.training.attendance.model.network;

import co.com.luisprmat.training.attendance.model.repository.StudentsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface AttendanceApi {
    @GET("students")
    Call<StudentsResponse> getData();
}
