package com.ucu.ecourse;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BSIT extends AppCompatActivity {

    private Button bsitTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_i_t);

        bsitTrack = findViewById(R.id.bsit_track);

        bsitTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BSIT.this)
                        .setMessage("Mobile Development\nWeb Development")
                        .setTitle("Tracks:")
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
            }
        });
    }
}