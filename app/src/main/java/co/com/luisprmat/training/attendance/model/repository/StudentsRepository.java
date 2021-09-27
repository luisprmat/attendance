package co.com.luisprmat.training.attendance.model.repository;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;
import co.com.luisprmat.training.attendance.model.network.AttendanceLoader;
import co.com.luisprmat.training.attendance.model.network.responses.StudentsResponse;
import co.com.luisprmat.training.attendance.view.login.LoginMVP;
import co.com.luisprmat.training.attendance.view.students.StudentsMVP;
import co.com.luisprmat.training.attendance.view.students.StudentsPresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentsRepository implements StudentsMVP.Model {
    private StudentsMVP.Presenter presenter;

    List<Student> students;

    public StudentsRepository(Context context, StudentsPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void loadStudents() {
        presenter.showProgress("Cargando estudiantes ...");
        AttendanceLoader.getApi().getData().enqueue(new Callback<StudentsResponse>() {
            @Override
            public void onResponse(Call<StudentsResponse> call, Response<StudentsResponse> response) {
                presenter.hideProgress();
                if (response.isSuccessful() && response.body().getData() != null) {
                    students = new ArrayList<>();
                    students.addAll(response.body().getData());
                    presenter.loadStudents(students);
                } else {
                    presenter.showDialog("Error cargando estudiantes");
                }
            }

            @Override
            public void onFailure(Call<StudentsResponse> call, Throwable t) {
                presenter.hideProgress();
                presenter.showDialog("Error cargando estudiantes");
            }
        });
    }

    @Override
    public void logout(String token) {
        presenter.showProgress("Cerrando sesión ...");
        AttendanceLoader.getApi().logout("Bearer "+token).enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                presenter.hideProgress();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                presenter.hideProgress();
                presenter.showDialog("Error de red");
            }
        });
    }
}
