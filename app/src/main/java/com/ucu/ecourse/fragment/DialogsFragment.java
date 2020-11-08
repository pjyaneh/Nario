package com.ucu.ecourse.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.ucu.ecourse.ABCommunication;
import com.ucu.ecourse.BSAccountancy;
import com.ucu.ecourse.BSArchi;
import com.ucu.ecourse.BSCrim;
import com.ucu.ecourse.BSElem;
import com.ucu.ecourse.BSIT;
import com.ucu.ecourse.BSPharma;
import com.ucu.ecourse.BSPolSci;
import com.ucu.ecourse.BSPsych;
import com.ucu.ecourse.BSSecondaryEduc;
import com.ucu.ecourse.BSSocialWork;
import com.ucu.ecourse.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by zhang on 2016.08.07.
 */
public class DialogsFragment extends Fragment {

    private Button btn_dialog_1, btn_dialog_2, btn_dialog_3, btn_dialog_4, btn_dialog_5,
            btn_dialog_6, btn_dialog_7, btn_dialog_8, btn_dialog_9, btn_dialog_10, btn_dialog_11;
    Calendar calendar;
    private AdView ad_view_dialog;
    private CardView card_ad_dialog;


    ImageView imageView;
    Random r = new Random();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        NestedScrollView nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_dialogs, container, false);



        ad_view_dialog = nestedScrollView.findViewById(R.id.ad_view_dialog);
        card_ad_dialog = nestedScrollView.findViewById(R.id.card_ad_dialog);
        imageView = nestedScrollView.findViewById(R.id.imgQuote);


        final int []imageArray=
                {R.drawable.q1,R.drawable.q2,R.drawable.q3,R.drawable.q4,R.drawable.q5,R.drawable.q6,R.drawable.q7,R.drawable.q8,R.drawable.q9};

        int i=r.nextInt(imageArray.length);
        imageView.setImageResource(imageArray[i]);
        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i=0;
            public void run() {
                /*imageView.setImageResource(imageArray[i]);
                i++;
                if(i>imageArray.length-1)
                {
                    i=0;
                }*/
                int k=r.nextInt(imageArray.length);
                imageView.setImageResource(imageArray[k]);
                handler.postDelayed(this, 10000);  //for interval...
            }

        };
        handler.postDelayed(runnable, 10000); //for initial delay..
        return nestedScrollView;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        calendar = Calendar.getInstance();


    }


    public void showAd() {
        try {
            SharedPreferences sharedPreferences = getContext().getSharedPreferences("app", MODE_PRIVATE);
            if (!sharedPreferences.getBoolean("isDonated", false)) {
                AdRequest adRequest = new AdRequest.Builder().build();
                ad_view_dialog.loadAd(adRequest);

                Animation animation = new AlphaAnimation(0.0f, 1.0f);
                animation.setDuration(500);
                card_ad_dialog.setVisibility(View.VISIBLE);
                card_ad_dialog.startAnimation(animation);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
