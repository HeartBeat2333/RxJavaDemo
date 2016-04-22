package com.rxjava.sample.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhouyuan0304 on 2016/4/22.
 */
public class Student {
    private String name;
    private List<Course> courses;

    public Student(String name) {
        this.name = name;
        courses = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.setName(name + ", 科目 : " + i);
            courses.add(course);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
