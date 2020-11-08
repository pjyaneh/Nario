package com.ucu.ecourse.fragment;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ucu.ecourse.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class CardsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {


    private ImageView img_main_card_1, img_main_card_2;
    private CardView card_main_1_1, card_main_1_2;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;
    private AdView ad_view_card;
    private CardView card_ad_card;
    private NestedScrollView nestedScrollView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        nestedScrollView = (NestedScrollView) inflater.inflate(R.layout.fragment_cards, container, false);



        img_main_card_1 = nestedScrollView.findViewById(R.id.img_main_card_1);
        img_main_card_2 = nestedScrollView.findViewById(R.id.img_main_card_2);


        card_main_1_1 = nestedScrollView.findViewById(R.id.card_main_1_1);
        card_main_1_2 = nestedScrollView.findViewById(R.id.card_main_1_2);


        Glide.with(getContext()).load(R.drawable.logoucu).apply(new RequestOptions().fitCenter()).into(img_main_card_1);

        ad_view_card = nestedScrollView.findViewById(R.id.ad_view_card);
        card_ad_card = nestedScrollView.findViewById(R.id.card_ad_card);

        return nestedScrollView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        img_main_card_1.startAnimation(alphaAnimation);
        img_main_card_2.startAnimation(alphaAnimation);

        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.card_main_1_1:
                break;

            case R.id.card_main_1_2:
                break;


        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 16);
                upAnim.setDuration(150);
                upAnim.setInterpolator(new DecelerateInterpolator());
                upAnim.start();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                downAnim.setDuration(150);
                downAnim.setInterpolator(new AccelerateInterpolator());
                downAnim.start();
                break;
        }
        return false;
    }


    private void showBannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        ad_view_card.loadAd(adRequest);

        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        card_ad_card.setVisibility(View.VISIBLE);
        card_ad_card.startAnimation(animation);
    }


}
