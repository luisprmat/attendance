package co.com.luisprmat.training.attendance.model.repository;

import android.os.Build;

import com.google.gson.Gson;

import java.io.IOException;

import co.com.luisprmat.training.attendance.model.network.AttendanceLoader;
import co.com.luisprmat.training.attendance.model.network.responses.UserResponse;
import co.com.luisprmat.training.attendance.model.network.responses.apiError.ApiErrorResponse;
import co.com.luisprmat.training.attendance.model.network.responses.TokenResponse;
import co.com.luisprmat.training.attendance.view.login.LoginInfo;
import co.com.luisprmat.training.attendance.view.login.LoginMVP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements LoginMVP.Model {
    private LoginMVP.Presenter presenter;

    @Override
    public void setLoginPresenter(LoginMVP.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void validateCredentials(LoginInfo info) {
        info = new LoginInfo(info.getEmail(), info.getPassword(), Build.MODEL);
        presenter.showProgress("Iniciando sesión ...");
        AttendanceLoader.getApi().login(info).enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                presenter.hideProgress();
                String model = Build.MODEL;
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
                            presenter.authenticationFailure("Hubo un error desconocido");
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
                return;
            }
        });
    }

    @Override
    public void loadUser(String token) {
        AttendanceLoader.getApi().user("Bearer " + token).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.code() == 401) {
                    presenter.loadUser(null);
                }
                presenter.loadUser(response.body());
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                presenter.loadUser(null);
            }
        });
    }
}
