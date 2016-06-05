package com.example.rikirikmen.trashbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

public class SubmitActivity extends AppCompatActivity {

    private List<Trash> trashes;
    private TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView1 = (TextView) findViewById(R.id.textView1);
        Intent i = getIntent();
        trashes = i.getParcelableArrayListExtra("trashList");
        textView1.setText(trashes.get(1).getTrashName());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
