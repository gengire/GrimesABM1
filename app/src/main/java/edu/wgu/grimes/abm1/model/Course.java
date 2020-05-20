package edu.wgu.grimes.abm1.model;

import java.util.List;

public class Course {
    private String title;
    private String code;
    private String startDate;
    private String anticipatedEndDate;
    private STATUS.COURSE status;
    private List<Mentor> mentors;
    private List<Assessment> assessments;
    private List<String> notes;

    public Course(String title, String code) {
        this.title = title;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getAnticipatedEndDate() {
        return anticipatedEndDate;
    }

    public void setAnticipatedEndDate(String anticipatedEndDate) {
        this.anticipatedEndDate = anticipatedEndDate;
    }

    public STATUS.COURSE getStatus() {
        return status;
    }

    public void setStatus(STATUS.COURSE status) {
        this.status = status;
    }

    public List<Mentor> getMentors() {
        return mentors;
    }

    public void setMentors(List<Mentor> mentors) {
        this.mentors = mentors;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public String getHeaderText() {
        return getTitle() + "   |   " + getCode();
    }
}
