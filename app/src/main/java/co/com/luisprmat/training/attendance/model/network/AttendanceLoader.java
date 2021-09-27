package co.com.luisprmat.training.attendance.model.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AttendanceLoader {
    private static AttendanceApi api;
    private static AttendanceLoader loader;
//    private final String URL_BASE = "https://luisprmat-attendance.herokuapp.com/api/v1/";
    private final String URL_BASE = "http://10.0.2.2:8000/api/v1/";

    // Singleton pattern
    public static AttendanceApi getApi() {
        if (loader == null) loader = new AttendanceLoader();
        return api;
    }

    private AttendanceLoader() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        api = retrofit.create(AttendanceApi.class);
    }
}
