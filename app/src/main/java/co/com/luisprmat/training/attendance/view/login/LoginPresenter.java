package co.com.luisprmat.training.attendance.view.login;

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

        if (info.getEmail() == null || info.getEmail().trim().isEmpty()) {
            view.showEmailError("Email es requerido");
            return;
        }

        if (info.getPassword() == null || info.getPassword().trim().isEmpty()) {
            view.showPasswordError("Contrseña es requerida");
            return;
        }

        model.validateCredentials(info.getEmail(), info.getPassword());
    }

    @Override
    public void authenticate() {
        if (model.isAuthenticated()) {
            view.showActivity(StudentsActivity.class);
        }
    }

    @Override
    public void authenticationSuccessful() {
        view.showActivity(StudentsActivity.class);
    }

    @Override
    public void authenticationFailure(String message) {
        view.showEmailError(message);
    }
}
