package edu.wgu.grimes.abm1;

import java.util.Collection;
import java.util.List;

import edu.wgu.grimes.abm1.model.Course;

public interface ListHeader {
    String getHeaderText();
    List<String> getCourseList();
    List<Course> getCourses();
}
