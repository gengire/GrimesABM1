package edu.wgu.grimes.abm1.model;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.ListHeader;

public class Transfers implements ListHeader {

    private String headerText;
    private List<Course> courses = new ArrayList<>();

    public Transfers(String headerText) {
        this.headerText = headerText;
    }

    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public String getHeaderText() {
        return headerText;
    }

    public List<String> getCourseList() {
        final List<String> stringCourseList = new ArrayList<>();
        for (Course course : getCourses()) {
            stringCourseList.add(course.getHeaderText());
        }
        return stringCourseList;
    }
}
