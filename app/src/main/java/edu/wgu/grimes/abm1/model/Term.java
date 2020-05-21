package edu.wgu.grimes.abm1.model;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.ListHeader;

public class Term implements ListHeader {
    private String guid;
    private int number;
    private String startDate;
    private String endDate;
    private List<Course> courses = new ArrayList<>();

    public Term() {
        // empty
    }

    public Term(int number, String startDate, String endDate) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String getHeaderText() {
        return "Term " + number + "   |   " + startDate + " to " + endDate;
    }

    @Override
    public List<String> getCourseList() {
        List<String> stringCourseList = new ArrayList<>();
        for (Course course : getCourses()) {
            stringCourseList.add(course.getHeaderText());
        }
        return stringCourseList;
    }

    public String getGuid() { return this.guid; }

    public void setGuid(String guid) { this.guid = guid; }

    public int getNumber() { return number; }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<Course> getCourses() {
        return courses;
    }

}
