package edu.wgu.grimes.abm1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> listDataHeader;
    private HashMap<String, List<String>> listHashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = findViewById(R.id.lvExp);
        initData();
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);

    }

    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Transfer Courses");
        listDataHeader.add("Term 1");
        listDataHeader.add("Term 2");
        listDataHeader.add("Term 3");
        listDataHeader.add("Term 4");

        List<String> transferCourses = new ArrayList<>();
        List<String> term1 = new ArrayList<>();
        List<String> term2 = new ArrayList<>();
        List<String> term3 = new ArrayList<>();
        List<String> term4 = new ArrayList<>();

        transferCourses.add("Introduction to Geography - C255");
        transferCourses.add("Introduction to Communication - C464");

        term1.add("Introduction to IT - C182");
        term1.add("Scripting and Programming - Foundations - C173");

        term2.add("Web Development Applications - C777");
        term2.add("Network and Security Foundations - C172");

        term3.add("Scripting and Programming - Applications - C867");
        term3.add("IT Applications - C394");

        term4.add("Operating Systems for Programmers - C191");
        term4.add("Mobile Applications Development - C196");

        listHashMap.put(listDataHeader.get(0), transferCourses);
        listHashMap.put(listDataHeader.get(1), term1);
        listHashMap.put(listDataHeader.get(2), term2);
        listHashMap.put(listDataHeader.get(3), term3);
        listHashMap.put(listDataHeader.get(4), term4);

        }
}
