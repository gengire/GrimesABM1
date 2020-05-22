package edu.wgu.grimes.abm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.List;

import edu.wgu.grimes.abm1.model.Course;
import edu.wgu.grimes.abm1.model.STATUS;
import edu.wgu.grimes.abm1.model.Term;
import edu.wgu.grimes.abm1.model.Transfers;
import edu.wgu.grimes.abm1.util.DBHelper;
import edu.wgu.grimes.abm1.util.TermBuilder;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DBHelper(this, null, null, 1);

        setContentView(R.layout.activity_main);

        ExpandableListView listView = findViewById(R.id.lvExp);
//        initData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, loadTermsFromDatabase());
        listView.setAdapter(listAdapter);

    }

    private List<ListHeader> loadTermsFromDatabase() {
        List<ListHeader> listHeaders = new ArrayList<>();
        listHeaders.add(dbHelper.selectTerm(1));
        listHeaders.add(dbHelper.selectTerm(2));
        listHeaders.add(dbHelper.selectTerm(3));
        listHeaders.add(dbHelper.selectTerm(4));
        return listHeaders;
    }

    private void initData() {
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 0, 1);

        String term1Id = dbHelper.insertTerm(1, "October 1, 2018", "March 31, 2019");
        dbHelper.insertCourse("Introduction to IT", "C182", null, "10/08/18", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Scripting and Programming - Foundations", "C173", null, "10/10/18", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Orientation", "ORA1", null, "04/01/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Web Development Foundations", "C779", null, "11/13/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Introduction to Humanities", "C100", null, "12/30/18", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Structured Query Language", "C993", null, "07/30/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Software I", "C482", null, "02/13/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Software II - Advanced Java Concepts", "C195", null, "02/27/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Data Management - Foundations", "C175", null, "03/12/19", STATUS.COURSE.COMPLETED, term1Id);
        dbHelper.insertCourse("Data Management - Applications", "C170", null, "03/18/19", STATUS.COURSE.COMPLETED, term1Id);

        String term2Id = dbHelper.insertTerm(2, "April 1, 2019", "September 30, 2019");
        dbHelper.insertCourse("Web Development Applications", "C777", null, "04/26/19", STATUS.COURSE.COMPLETED, term2Id);
        dbHelper.insertCourse("Network and Security - Foundations", "C172", null, "05/28/19", STATUS.COURSE.COMPLETED, term2Id);
        dbHelper.insertCourse("Business of IT - Project Management", "C176", null, "06/25/19", STATUS.COURSE.COMPLETED, term2Id);
        dbHelper.insertCourse("IT Foundations", "C393", null, "08/14/19", STATUS.COURSE.COMPLETED, term2Id);
        dbHelper.insertCourse("Software Quality Assurance", "C857", null, "08/27/19", STATUS.COURSE.COMPLETED, term2Id);
        dbHelper.insertCourse("Software Engineering", "C188", null, "09/26/19", STATUS.COURSE.COMPLETED, term2Id);

        String term3Id = dbHelper.insertTerm(3, "October 1, 2019", "March 31, 2020");
        dbHelper.insertCourse("Scripting and Programming - Applications", "C867", null, "10/08/19", STATUS.COURSE.COMPLETED, term3Id);
        dbHelper.insertCourse("IT Applications", "C394", null, "10/22/19", STATUS.COURSE.COMPLETED, term3Id);
        dbHelper.insertCourse("Business of IT - Applications", "C846", null, "01/05/20", STATUS.COURSE.COMPLETED, term3Id);
        dbHelper.insertCourse("User Interface Design", "C773", null, "1/12/20", STATUS.COURSE.COMPLETED, term3Id);
        dbHelper.insertCourse("Organizational Behavior and Leadership", "C484", null, "02/04/20", STATUS.COURSE.COMPLETED, term3Id);
        dbHelper.insertCourse("User Experience Design", "C856", null, "02/06/20", STATUS.COURSE.COMPLETED, term3Id);

        String term4Id = dbHelper.insertTerm(4, "April 1, 2020", "September, 2020");
        dbHelper.insertCourse("Operating Systems for Programmers", "C191", null, "05/18/20", STATUS.COURSE.COMPLETED, term4Id);
        dbHelper.insertCourse("Mobile Application Development", "C196", "05/19/20", "06/30/20", STATUS.COURSE.IN_PROGRESS, term4Id);
        dbHelper.insertCourse("Software Development Capstone", "C868", "07/01/20", "07/31/20", STATUS.COURSE.PLAN_TO_TAKE, term4Id);
    }
}
