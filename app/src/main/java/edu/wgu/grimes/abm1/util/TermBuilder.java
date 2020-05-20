package edu.wgu.grimes.abm1.util;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.Term;

public class TermBuilder {

    private int number;
    private String startDate;
    private String endDate;
    private List<Course> courses = new ArrayList<>();

    public TermBuilder(int number, String startDate, String endDate) {
        this.number = number;
        this.startDate = startDate;
        this.endDate = endDate;
        String missingValue = null;
        if (number < 1 || startDate == null || endDate == null) {
            throw new IllegalStateException("number, startDate, and endDate are not nullable");
        }
    }

    public Term build() {
        Term term = new Term(getNumber(), getStartDate(), getEndDate());
        term.getCourses().addAll(courses);
        cleanUp();
        return term;
    }

    public TermBuilder addCourse(Course course) {
        courses.add(course);
        return this;
    }

    private void cleanUp() {
        number = 0;
        startDate = null;
        endDate = null;
        courses.clear();
    }

    public int getNumber() {
        return number;
    }

    public TermBuilder setNumber(int number) {
        this.number = number;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public TermBuilder setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public TermBuilder setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }
}
