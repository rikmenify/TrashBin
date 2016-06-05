package com.example.rikirikmen.trashbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SampleExpand extends AppCompatActivity {

    public Integer lastExpandPosition = -1;
    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> itemCollection;
    ExpandableListView expListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_expand);

        createGroupList();
        createCollection();

        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        setGroupIndicatorToRight();

        final ExpandableListAdapter expListAdapter = new AdapterExpandable(
                this, groupList, itemCollection);
        expListView.setAdapter(expListAdapter);
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandPosition != -1
                        && groupPosition != lastExpandPosition) {
                    expListView.collapseGroup(lastExpandPosition);
                }
                lastExpandPosition = groupPosition;
            }
        });
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("HP");
        groupList.add("Dell");
        groupList.add("Lenovo");
        groupList.add("Sony");
        groupList.add("HCL");
        groupList.add("Samsung");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        String[] hpModels = {"HP Pavilion G6-2014TX", "ProBook HP 4540",
                "HP Envy 4-1025TX"};
        String[] hclModels = {"HCL S2101", "HCL L2102", "HCL V2002"};
        String[] lenovoModels = {"IdeaPad Z Series", "Essential G Series",
                "ThinkPad X Series", "Ideapad Z Series"};
        String[] sonyModels = {"VAIO E Series", "VAIO Z Series",
                "VAIO S Series", "VAIO YB Series"};
        String[] dellModels = {"Inspiron", "Vostro", "XPS"};
        String[] samsungModels = {"NP Series", "Series 5", "SF Series"};

        itemCollection = new LinkedHashMap<String, List<String>>();

        for (String group : groupList) {
            if (group.equals("HP")) {
                loadChild(hpModels);
            } else if (group.equals("Dell"))
                loadChild(dellModels);
            else if (group.equals("Sony"))
                loadChild(sonyModels);
            else if (group.equals("HCL"))
                loadChild(hclModels);
            else if (group.equals("Samsung"))
                loadChild(samsungModels);
            else
                loadChild(lenovoModels);

            itemCollection.put(group, childList);
        }
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(width - getDipsFromPixel(60), width
                - getDipsFromPixel(30));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }

    private void loadChild(String[] models) {
        childList = new ArrayList<String>();
        for (String model : models)
            childList.add(model);
    }
}
