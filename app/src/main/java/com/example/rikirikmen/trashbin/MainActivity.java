package com.example.rikirikmen.trashbin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private List<Trash> trashList;
    private ButtonAdapter adapter;
    private RecyclerView recyclerView;
    private Button btnProceed;
    private List<Trash> trashes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_trash);
        btnProceed = (Button) findViewById(R.id.btnProcced);
        trashList = new ArrayList<>();
        adapter = new ButtonAdapter(this, trashList);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        prepareImg();

        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trashes.addAll(adapter.getList());
                Toast.makeText(MainActivity.this, ""+ String.valueOf(trashes.get(0).getTrashQty()), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void prepareImg(){
         int[] buttonImage = new int[]{
                R.drawable.apple, R.drawable.asparagus,
                R.drawable.aubergine, R.drawable.avocado,
                R.drawable.baguette, R.drawable.bacon,
                R.drawable.banana, R.drawable.beans,
                R.drawable.blueberries, R.drawable.biscuit,
                R.drawable.boiled, R.drawable.bowl,
                R.drawable.bread, R.drawable.butcher,
                R.drawable.cabbage
        };
        Trash trash = new Trash("Example 1", 0, buttonImage[0]);
        trashList.add(trash);

        for (int i = 1; i<buttonImage.length;i++){
            int a = i+1;
            trash = new Trash("Example"+ a, 0, buttonImage[i]);
            trashList.add(trash);
        }

        adapter.notifyDataSetChanged();
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
}
