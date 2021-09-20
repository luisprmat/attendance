package co.com.luisprmat.training.attendance.model.repository;

import java.util.Arrays;
import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.User;
import co.com.luisprmat.training.attendance.view.login.LoginMVP;

public class UserRepository implements LoginMVP.Model {
    private LoginMVP.Presenter presenter;

    private List<User> users;
    private boolean authenticated = false;

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
    public void validateCredentials(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)
            && user.getPassword().equals(password)) {
                presenter.authenticationSuccessful();
                this.authenticated = true;
                //true
            }
        }
        presenter.authenticationFailure("Las credenciales no son válidas");
        this.authenticated = false;
        //false
    }

    @Override
    public boolean isAuthenticated() {
        return this.authenticated;
    }
}
