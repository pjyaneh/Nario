package com.ucu.ecourse;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BSElem extends AppCompatActivity {

    private Button elemEd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_elem);

        elemEd = findViewById(R.id.bsElem_track);

        elemEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BSElem.this)
                        .setMessage("General Education")
                         .setTitle("Major in:")
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
            }
        });
    }
}