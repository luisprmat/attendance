package co.com.luisprmat.training.attendance.model.repository;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;
import co.com.luisprmat.training.attendance.view.students.StudentsMVP;
import co.com.luisprmat.training.attendance.view.students.StudentsPresenter;

public class StudentsRepository implements StudentsMVP.Model {
    private StudentsMVP.Presenter presenter;

    List<Student> students;

    public StudentsRepository(Context context, StudentsPresenter presenter) {
        this.presenter = presenter;
        this.students = Arrays.asList(
                new Student("Marco Vargas", "7A"),
                new Student("Pepito Perez", "10A"),
                new Student("Pablo Hernández", "8B")
        );
    }

    @Override
    public void loadStudents() {
        presenter.loadStudents(this.students);
    }
}
