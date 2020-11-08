package com.ucu.ecourse;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BSSecondaryEduc extends AppCompatActivity {

    private Button secEd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_s_secondary_educ);

        secEd = findViewById(R.id.bsSecEd_track);

        secEd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BSSecondaryEduc.this)
                        .setMessage("English\nFilipino\nGeneral Science\nMathematics\nPhysical Education\nHealth and Music\nSocial Studies\nBusiness")
                        .setTitle("Major in:")
                        .setPositiveButton(getString(R.string.dialog_ok), null)
                        .show();
            }
        });
    }
}