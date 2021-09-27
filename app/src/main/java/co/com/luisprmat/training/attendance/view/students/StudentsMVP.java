package co.com.luisprmat.training.attendance.view.students;

import android.content.Context;

import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;
import co.com.luisprmat.training.attendance.view.BaseActivity;

public interface StudentsMVP {
    interface Model {
        void loadStudents();

        void logout(String token);
    }

    interface Presenter {
        void loadStudents();

        void showProgress(String msg);

        void hideProgress();

        void loadStudents(List<Student> students);

        void logout();

        void logoutConfirmed();

        void showDialog(String msg);
    }

    interface View {
        Context getApplicationContext();

        void loadStudents(List<Student> students);

        void showActivity(Class<? extends BaseActivity> type);

        void showProgress(String msg);

        void hideProgress();

        void confirmLogout();

        void showOkDialog(String dialogMsg);
    }
}
