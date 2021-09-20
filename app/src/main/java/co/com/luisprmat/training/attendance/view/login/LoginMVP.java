package co.com.luisprmat.training.attendance.view.login;

import androidx.appcompat.app.AppCompatActivity;

public interface LoginMVP {
    interface Model {
        void validateCredentials(String username, String password);

        void setLoginPresenter(Presenter presenter);

        boolean isAuthenticated();
    }

    interface Presenter {
        void login();

        void authenticate();

        void authenticationSuccessful();

        void authenticationFailure(String message);
    }

    interface View {
        LoginInfo getLoginInfo();

        void showEmailError(String message);

        void showPasswordError(String message);

        void showActivity(Class<? extends AppCompatActivity> type);
    }
}
