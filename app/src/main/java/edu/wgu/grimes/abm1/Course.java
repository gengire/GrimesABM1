package edu.wgu.grimes.abm1;

import java.util.List;

public class Course {
    String title;
    String code;
    String startDate;
    String anticipatedEndDate;
    STATUS.COURSE status;
    List<Mentor> mentors;
    List<Assessment> assessments;
    List<String> notes;
}
