package edu.wgu.grimes.abm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.Term;
import edu.wgu.grimes.abm1.model.Transfers;
import edu.wgu.grimes.abm1.util.TermBuilder;

public class MainActivity extends AppCompatActivity {

    private List<ListHeader> listHeaders;
    private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = findViewById(R.id.lvExp);
        initData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listHeaders, listHashMap);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listHeaders = new ArrayList<>();
        listHashMap = new HashMap<>();

        Transfers transfers = new Transfers("Course Transfers");
        transfers.getCourses().add(new Course("Introduction to Geography", "C255"));
        transfers.getCourses().add(new Course("Introduction to Communication", "C464"));
        listHeaders.add(transfers);

        TermBuilder termBuilder = new TermBuilder(1, "October 1, 2018", "March 31, 2019");
        termBuilder.addCourse(new Course("Introduction to IT", "C182"));
        termBuilder.addCourse(new Course("Scripting and Programming - Foundations", "C173"));
        Term term1 = termBuilder.build();
        listHeaders.add(term1);

        termBuilder.setNumber(2).setStartDate("April 1, 2019").setEndDate("September 30, 2019");
        termBuilder.addCourse(new Course("Web Development Applications", "C777"));
        termBuilder.addCourse(new Course("Network and Security Foundations", "C172"));
        Term term2 = termBuilder.build();
        listHeaders.add(term2);

        termBuilder.setNumber(3).setStartDate("October 1, 2019").setEndDate("March 31, 2020");
        termBuilder.addCourse(new Course("Scripting and Programming - Applications", "C867"));
        termBuilder.addCourse(new Course("IT Applications", "C394"));
        Term term3 = termBuilder.build();
        listHeaders.add(term3);

        termBuilder.setNumber(4).setStartDate("April 1, 2020").setEndDate("September 30, 2020");
        termBuilder.addCourse(new Course("Operating Systems for Programmers", "C191"));
        termBuilder.addCourse(new Course("Mobile Applications Development", "C196"));
        Term term4= termBuilder.build();
        listHeaders.add(term4);

        listHashMap.put(transfers.getHeaderText(), transfers.getCourseList());
        listHashMap.put(term1.getHeaderText(), term1.getCourseList());
        listHashMap.put(term2.getHeaderText(), term2.getCourseList());
        listHashMap.put(term3.getHeaderText(), term3.getCourseList());
        listHashMap.put(term4.getHeaderText(), term4.getCourseList());

        }
}
