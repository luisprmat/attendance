package co.com.luisprmat.training.attendance.view.login;

import android.content.Context;

import co.com.luisprmat.training.attendance.model.network.responses.UserResponse;
import co.com.luisprmat.training.attendance.view.BaseActivity;

public interface LoginMVP {
    interface Model {
        void validateCredentials(LoginInfo credentials);

        void setLoginPresenter(Presenter presenter);

        boolean isAuthenticated(String token);
    }

    interface Presenter {
        void login();

        void showProgress(String msg);

        void hideProgress();

        void authenticate();

        void authenticationSuccessful(String authToken);

        void authenticationFailure(String message);

        void showDialog(String msg);
    }

    interface View {
        Context getApplicationContext();

        LoginInfo getLoginInfo();

        void showProgress(String msg);

        void hideProgress();

        void showEmailError(String message);

        void showPasswordError(String message);

        void showOkDialog(String dialogMsg);

        void showActivity(Class<? extends BaseActivity> type);
    }
}
