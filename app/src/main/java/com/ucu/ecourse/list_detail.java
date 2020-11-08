package com.ucu.ecourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class list_detail extends AppCompatActivity {

TextView number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        number = findViewById(R.id.userDetail);


        number.setText(getIntent().getStringExtra("number"));

    }
}
