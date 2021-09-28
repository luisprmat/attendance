package co.com.luisprmat.training.attendance.view.login;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import co.com.luisprmat.training.attendance.R;
import co.com.luisprmat.training.attendance.view.BaseActivity;

public class LoginActivity extends BaseActivity implements LoginMVP.View {
    private LoginMVP.Presenter presenter;

    TextInputLayout tilUsername, tilPassword;
    TextInputEditText etUsername, etPassword;

    MaterialButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
    }

    private void initUI() {
        presenter = new LoginPresenter(this);

        tilUsername = findViewById(R.id.tilUsername);
        etUsername = findViewById(R.id.etUsername);

        tilPassword = findViewById(R.id.tilPassword);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            tilUsername.setError("");
            tilPassword.setError("");

            presenter.login();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.authenticate();
    }

    @Override
    public LoginInfo getLoginInfo() {
        return new LoginInfo(
                etUsername.getText().toString().trim(),
                etPassword.getText().toString().trim()
        );
    }

    @Override
    public void showEmailError(String message) {
        tilUsername.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
        tilPassword.setError(message);
    }

    @Override
    public void showActivity(Class<? extends BaseActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);
    }
}