package com.manifestsoftwarelab.demoapp.Pojo;

import java.util.List;

/**
 * Created by DELL on 8/27/2017.
 */

public class InstitutePojo {
    String insti_name;
    String course_name;
    List<StudentPojo> studentPojoList;

    public String getInsti_name() {
        return insti_name;
    }

    public void setInsti_name(String insti_name) {
        this.insti_name = insti_name;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public List<StudentPojo> getStudentPojoList() {
        return studentPojoList;
    }

    public void setStudentPojoList(List<StudentPojo> studentPojoList) {
        this.studentPojoList = studentPojoList;
    }
}
