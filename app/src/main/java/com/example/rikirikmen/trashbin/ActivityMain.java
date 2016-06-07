package com.example.rikirikmen.trashbin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.rikirikmen.trashbin.Adapter.AdapterExpandable;
import com.example.rikirikmen.trashbin.Singleton.Singleton;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import parceable.Trash;


public class ActivityMain extends AppCompatActivity {

    private ExpandableListView expListView;
    private Button btnProcess;
    private Integer lastExpandPosition = -1;
    private List<String> groupList;
    private List<Trash> trashList;
    private Map<String, List<Trash>> itemCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // PREPARE Data
        createGroupList();
        createCollection();
        final AdapterExpandable expAdapter = new AdapterExpandable(this, groupList, itemCollection);
        Singleton.getInstance().setItemCollections(itemCollection);

        // EXPANDABLE LIST VIEW
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);
        expListView.setAdapter(expAdapter);
        setGroupIndicatorToRight(); // SET arrow indicator to right
        // TO SET only one tab can be opened
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                lastExpandPosition = groupPosition;
            }
        });
        // END of EXPANDABLE LIST VIEW

        // BUTTON PROCESS
        btnProcess = (Button) findViewById(R.id.btnProcess);
        btnProcess.setText("Done Collecting");
        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCollection = Singleton.getInstance().getItemCollections();
                expAdapter.notifyDataSetChanged();
                Toast.makeText(
                        ActivityMain.this.getApplicationContext(),
                        String.valueOf(itemCollection.get("Plastics").get(0).getTrashQty()),
                        Toast.LENGTH_SHORT).show();
            }
        });
        // END of BUTTON PROCESS
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createGroupList() {
        groupList = new ArrayList<String>();
        groupList.add("Plastics");
        groupList.add("Glasses");
        groupList.add("Papers");
        groupList.add("Foams");
        groupList.add("Others");
    }

    private void createCollection() {
        // preparing laptops collection(child)
        Trash[] plastics = {
                new Trash("Example 1", 0, R.drawable.apple),
                new Trash("Example 2", 0, R.drawable.asparagus),
                new Trash("Example 3", 0, R.drawable.aubergine)};
        Trash[] glasses = {
                new Trash("Example 4", 0, R.drawable.avocado),
                new Trash("Example 5", 0, R.drawable.baguette),
                new Trash("Example 6", 0, R.drawable.bacon),
                new Trash("Example 7", 0, R.drawable.banana),
                new Trash("Example 8", 0, R.drawable.beans),
                new Trash("Example 9", 0, R.drawable.blueberries)};
        Trash[] papers = {
                new Trash("Example 10", 0, R.drawable.biscuit),
                new Trash("Example 11", 0, R.drawable.boiled),
                new Trash("Example 12", 0, R.drawable.bread),
                new Trash("Example 13", 0, R.drawable.bowl)};
        Trash[] foams = {
                new Trash("Example 14", 0, R.drawable.butcher)};
        Trash[] others = {
                new Trash("Example 15", 0, R.drawable.cabbage)};

        itemCollection = new LinkedHashMap<String, List<Trash>>();

        for (String group : groupList) {
            if (group.equals("Plastics"))
                loadChild(plastics);
            else if (group.equals("Glasses"))
                loadChild(glasses);
            else if (group.equals("Papers"))
                loadChild(papers);
            else if (group.equals("Foams"))
                loadChild(foams);
            else if (group.equals("Others"))
                loadChild(others);

            itemCollection.put(group, trashList);
        }
    }

    private void loadChild(Trash[] models) {
        trashList = new ArrayList<Trash>();
        for (Trash model : models)
            trashList.add(model);
    }

    private void setGroupIndicatorToRight() {
        /* Get the screen width */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;

        expListView.setIndicatorBounds(
                width - getDipsFromPixel(30),
                width - getDipsFromPixel(10));
    }

    // Convert pixel to dip
    public int getDipsFromPixel(float pixels) {
        // Get the screen's density scale
        final float scale = getResources().getDisplayMetrics().density;
        // Convert the dps to pixels, based on density scale
        return (int) (pixels * scale + 0.5f);
    }
}
