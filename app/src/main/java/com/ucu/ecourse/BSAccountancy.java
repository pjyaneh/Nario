package com.ucu.ecourse;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class BSAccountancy extends AppCompatActivity {

    private Button bsaTrack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_accountancy);

        bsaTrack = findViewById(R.id.BSA_track);

        bsaTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BSAccountancy.this)
                        .setMessage("Financial Management\nBusiness Administration")
                        .setTitle("Tracks:")
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
            }
        });
    }
}