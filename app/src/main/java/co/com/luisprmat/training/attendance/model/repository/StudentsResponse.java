package co.com.luisprmat.training.attendance.model.repository;

import java.util.List;

import co.com.luisprmat.training.attendance.model.entity.Student;

public class StudentsResponse {
    private List<Student> data;

    public List<Student> getData() {
        return data;
    }
}
