package co.com.luisprmat.training.attendance.view.students;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;

public interface StudentsMVP {
    interface Model {
        void loadStudents();
    }

    interface Presenter {
        void loadStudents();

        void loadStudents(List<Student> students);

        void logout();

        void logoutConfirmed();
    }

    interface View {
        Context getApplicationContext();

        void loadStudents(List<Student> students);

        void showActivity(Class<? extends AppCompatActivity> type);

        void confirmLogout();
    }
}
