package co.com.luisprmat.training.attendance.view.students;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import co.com.luisprmat.training.attendance.R;
import co.com.luisprmat.training.attendance.model.entity.Student;
import co.com.luisprmat.training.attendance.view.BaseActivity;

public class StudentsActivity extends BaseActivity implements StudentsMVP.View {
    private StudentsMVP.Presenter presenter;

//    RecyclerView rvStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        getSupportActionBar().setTitle("Estudiantes");

        initUI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadStudents();
    }

    private void initUI() {
        presenter = new StudentsPresenter(this);

//        rvStudents = findViewById(R.id.rvStudents);
//        rvStudents.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        presenter.logout();
    }

    @Override
    public void loadStudents(List<Student> students) {
//        StudentsAdapter adapter = new StudentsAdapter(this, students);
//
//        rvStudents.setAdapter(adapter);
    }

    @Override
    public void showActivity(Class<? extends BaseActivity> type) {
        Intent intent = new Intent(this, type);
        startActivity(intent);
    }

    @Override
    public void confirmLogout() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("¿Está seguro de cerrar la sesión?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, which) -> presenter.logoutConfirmed())
                .setNegativeButton(android.R.string.no, null)
                .show();
    }
}