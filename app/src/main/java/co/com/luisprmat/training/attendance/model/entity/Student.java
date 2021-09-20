package co.com.luisprmat.training.attendance.model.entity;

public class Student {
    private String id;
    private String name;
    private String course;

    public Student(String name, String course) {
        this.name = name;
        this.course = course;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCourse() {
        return course;
    }
}
