package co.com.luisprmat.training.attendance.model.repository;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.User;
import co.com.luisprmat.training.attendance.model.network.AttendanceLoader;
import co.com.luisprmat.training.attendance.model.network.responses.apiError.ApiErrorResponse;
import co.com.luisprmat.training.attendance.model.network.responses.TokenResponse;
import co.com.luisprmat.training.attendance.view.login.LoginInfo;
import co.com.luisprmat.training.attendance.view.login.LoginMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements LoginMVP.Model {
    private LoginMVP.Presenter presenter;

    private List<User> users;
    private boolean authenticated = false;
    private LoginInfo info;

    public UserRepository() {
        this.users = Arrays.asList(
                new User("luisprmat@hotmail.com", "password"),
                new User("marco@hotmail.com", "password1")
        );
    }

    @Override
    public void setLoginPresenter(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void validateCredentials(LoginInfo info) {
//        for (User user : users) {
//            if (user.getUsername().equals(username)
//            && user.getPassword().equals(password)) {
//                presenter.authenticationSuccessful();
//                this.authenticated = true;
//                //true
//            }
//        }
//        presenter.authenticationFailure("Las credenciales no son válidas");
//        this.authenticated = false;
//        //false
        info = new LoginInfo(info.getEmail(), info.getPassword(), "My New Phone");
        presenter.showProgress("Iniciando sesión ...");
        AttendanceLoader.getApi().login(info).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                presenter.hideProgress();
                if (response.isSuccessful()) {
                    presenter.authenticationSuccessful(response.body().getToken());
                } else {
                    if (response.code() == 422) {
                        Gson gson = new Gson();
                        try {
                            ApiErrorResponse errorResponse = gson.fromJson(
                                    response.errorBody().string(),
                                    ApiErrorResponse.class
                            );
                            String message = errorResponse.getErrors().getEmail().get(0);
                            presenter.authenticationFailure(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        presenter.authenticationFailure("Error desconocido");
                    }
                }
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                presenter.hideProgress();
                presenter.showDialog("Error iniciando sesión ...");
            }
        });
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }
}
