package com.ucu.ecourse.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.ucu.ecourse.R;
import com.ucu.ecourse.adapter.RecyclerViewAdapter;


import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter adapter;
    private int color = 0;
    private List<String> data;
    private String insertData;
    private boolean loading;
    private int loadTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        Toolbar toolbar = findViewById(R.id.toolbar_recycler_view);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



    }





}
