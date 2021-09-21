package co.com.luisprmat.training.attendance.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import co.com.luisprmat.training.attendance.R;

public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        progress = new ProgressDialog(this);
    }

    public void showProgress(String msg) {
        progress.setCancelable(false);
        progress.setMessage(msg);
        progress.show();
    }

    public void hideProgress() {
        if (progress.isShowing()) progress.dismiss();
    }

    public void showOkDialog(String dialogMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this);

        builder.setMessage(dialogMsg).setTitle(R.string.app_name);

        AlertDialog dialog = builder.create();
        dialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", (dialogInterface, i) -> {
            dialog.dismiss();
            finish();
        });

        dialog.show();
    }
}
