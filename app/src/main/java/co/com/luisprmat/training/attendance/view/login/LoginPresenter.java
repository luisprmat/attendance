package co.com.luisprmat.training.attendance.view.login;

import co.com.luisprmat.training.attendance.commons.StorageManager;
import co.com.luisprmat.training.attendance.model.network.responses.UserResponse;
import co.com.luisprmat.training.attendance.model.repository.UserRepository;
import co.com.luisprmat.training.attendance.view.students.StudentsActivity;

public class LoginPresenter implements LoginMVP.Presenter {
    private final LoginMVP.View view;
    private final LoginMVP.Model model;

    public LoginPresenter(LoginMVP.View view) {
        this.view = view;
        this.model = new UserRepository();
        this.model.setLoginPresenter(this);
    }

    @Override
    public void login() {
        LoginInfo info = view.getLoginInfo();

        if (info == null) {
            view.showActivity(LoginActivity.class);
        }

        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            view.showEmailError("Email es requerido");
            return;
        }

        if (info.getPassword() == null || info.getPassword().trim().isEmpty()) {
            view.showPasswordError("Contrseña es requerida");
            return;
        }

        model.validateCredentials(info);
    }

    @Override
    public void showProgress(String msg) {
        view.showProgress(msg);
    }

    @Override
    public void loadUser() {
        String token = StorageManager.getInstance(view.getApplicationContext()).getToken();
        if (token != null) {
            model.loadUser(token);
        }
    }

    @Override
    public void loadUser(UserResponse user) {
        if (user != null) {
            view.showActivity(StudentsActivity.class);
        } else {
            StorageManager.getInstance(view.getApplicationContext()).putToken(null);
        }
    }

    @Override
    public void hideProgress() {
        view.hideProgress();
    }

    @Override
    public void authenticate() {
        loadUser();
    }

    @Override
    public void authenticationSuccessful(String authToken) {
        StorageManager.getInstance(view.getApplicationContext()).putToken(authToken);
        view.showActivity(StudentsActivity.class);
    }

    @Override
    public void authenticationFailure(String message) {
        view.showEmailError(message);
    }

    @Override
    public void showDialog(String msg) {
        view.showOkDialog(msg);
    }
}
