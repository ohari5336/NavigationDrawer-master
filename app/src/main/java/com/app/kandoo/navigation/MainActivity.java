package com.app.kandoo.navigation;

import android.graphics.Color;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import android.widget.RelativeLayout;

import com.facebook.shimmer.ShimmerFrameLayout;


public class MainActivity extends AppCompatActivity  {
    CardView button;
    RelativeLayout button1;
    // is  a relativeLayout that our cardView is in the bottom of it and we give it margin till our cardView get margin from top
    RelativeLayout button_nav;
    ImageView navIcon;
    Animation anim;
    private String TAG = "gesture";
    float initialX, initialY;
    Animation anim1;
    int mHeight;
    RelativeLayout relativeLayout;
    Animation rotate;
    Animation rotate1;
    private ShimmerFrameLayout mShimmerViewContainer;
    public static String to ="";


    int n = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        relativeLayout = findViewById(R.id.relative);
        rotate = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        rotate1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.rotate);
        button_nav = findViewById(R.id.button_nav);
        navIcon = findViewById(R.id.button_nav2);
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                navIcon.clearAnimation();
                navIcon.setImageResource(R.drawable.close);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        rotate1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                navIcon.clearAnimation();
                navIcon.setImageResource(R.drawable.nav1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        // height from top to middle of screen
        mHeight= this.getResources().getDisplayMetrics().heightPixels;
        mHeight /=2;
        mHeight +=150;
        navIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // n is a variable to understand our drawer is open or close
                if (n==1) {
                    navIcon.startAnimation(rotate);
                    button1.animate().y(mHeight).setDuration(600).setStartDelay(200);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_nav.getLayoutParams();
                    lp.topMargin = mHeight;// use topmargin for the y-property, left margin for the x-property of your view
                    button_nav.setLayoutParams(lp);
                    n= 2;
                    button.setRadius(50);
                    button1.setBackgroundColor(Color.parseColor("#ff8d8d"));
                }else {
                    navIcon.startAnimation(rotate1);
                    int animationpos = 0;
                    button1.animate().y(animationpos).setDuration(600).setStartDelay(200);
                    RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_nav.getLayoutParams();
                    lp.topMargin = 0;// use topmargin for the y-property, left margin for the x-property of your view
                    button_nav.setLayoutParams(lp);
                    n= 1;
                    button.setRadius(0);
                    button1.setBackgroundColor(Color.parseColor("#ececec"));


                }

            }
        });
        button_nav.setVisibility(View.INVISIBLE);
        button = findViewById(R.id.main_layout);
        button1 = findViewById(R.id.relative1);
        anim = new TranslateAnimation(0, 0, 0,550);
        anim.setFillAfter(true);
        anim.setDuration(1000);
        final CardView button = findViewById(R.id.main_layout);
        //button1.animate().y(animationpos).setDuration(1000);
        anim.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                button.setRadius(60);
                //  button_nav1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {

                button1.setVisibility(View.INVISIBLE);
                button_nav.setVisibility(View.VISIBLE);
                navIcon.setVisibility(View.INVISIBLE);

            }
        });




        anim1 = new TranslateAnimation(0, 0, 550,0);
        anim1.setFillAfter(true);
        anim1.setDuration(1000);
        anim1.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                button.setRadius(0);
                button_nav.setVisibility(View.INVISIBLE);
                navIcon.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                button1.setVisibility(View.VISIBLE);

            }
        });

    }


    // close and opening navigation by touching on the screen
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        int action = event.getActionMasked();
        Log.d(TAG, String.valueOf(event.getY()));

        button1.setBackgroundColor(Color.parseColor("#ff8d8d"));
        switch (action) {

            case MotionEvent.ACTION_DOWN:
                initialX = event.getX();
                initialY = event.getY();

                Log.d(TAG, "Action was DOWN");
                break;

            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "Action was MOVE");
                break;

            case MotionEvent.ACTION_UP:
                float finalX = event.getX();
                float finalY = event.getY();

                Log.d(TAG, "Action was UP");

                if (initialX < finalX) {
                    Log.d(TAG, "Left to Right swipe performed");


                }

                if (initialX > finalX) {
                    Log.d(TAG, "Right to Left swipe performed");
                }

                if (initialY < finalY) {

                    if (initialY<=200 && finalY>=(mHeight/2)){
                        navIcon.startAnimation(rotate);
                        button1.animate().y(mHeight).setDuration(600).setStartDelay(200);
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_nav.getLayoutParams();
                        lp.topMargin = mHeight;// use topmargin for the y-property, left margin for the x-property of your view
                        button_nav.setLayoutParams(lp);
                        n= 2;
                        button.setRadius(50);
                        button1.setBackgroundColor(Color.parseColor("#ff8d8d"));
                    }

                    Log.d(TAG, "Up to Down swipe performed");
                }

                if (initialY > finalY) {
                    if (n!=1) {
                        n = 1;
                        navIcon.startAnimation(rotate1);
                        int animationpos = 0;
                        button1.animate().y(animationpos).setDuration(600).setStartDelay(200);
                        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) button_nav.getLayoutParams();
                        lp.topMargin = 0;// use topmargin for the y-property, left margin for the x-property of your view
                        button_nav.setLayoutParams(lp);
                        button.setRadius(0);
                        button1.setBackgroundColor(Color.parseColor("#ececec"));


                    }
                }

                break;

            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG,"Action was CANCEL");
                break;

            case MotionEvent.ACTION_OUTSIDE:
                Log.d(TAG, "Movement occurred outside bounds of current screen element");
                break;
        }
        return super.dispatchTouchEvent(event);
    }



    @Override
    public void onResume() {
        super.onResume();
        mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    protected void onPause() {
        mShimmerViewContainer.stopShimmerAnimation();
        super.onPause();
    }

}
