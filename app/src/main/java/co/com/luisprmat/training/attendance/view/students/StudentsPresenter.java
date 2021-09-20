package co.com.luisprmat.training.attendance.view.students;

import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;
import co.com.luisprmat.training.attendance.model.repository.StudentsRepository;
import co.com.luisprmat.training.attendance.view.login.LoginActivity;

public class StudentsPresenter implements StudentsMVP.Presenter {
    private final StudentsMVP.View view;
    private final StudentsMVP.Model model;

    public StudentsPresenter(StudentsMVP.View view) {
        this.view = view;
        this.model = new StudentsRepository(view.getApplicationContext(), this);
    }

    @Override
    public void loadStudents() {
        model.loadStudents();
    }

    @Override
    public void loadStudents(List<Student> students) {
        view.loadStudents(students);
    }

    @Override
    public void logout() {
        view.confirmLogout();
    }

    @Override
    public void logoutConfirmed() {
        view.showActivity(LoginActivity.class);
    }
}
