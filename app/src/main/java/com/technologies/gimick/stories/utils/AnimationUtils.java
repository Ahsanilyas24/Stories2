package com.technologies.gimick.stories.utils;

/*
This is class for animation used in this project

 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewAnimationUtils;


public class AnimationUtils {


    public void showView(View myView) {
        // previously invisible view


// get the center for the clipping circle
        int cx = myView.getWidth() / 1;
        int cy = myView.getHeight() / 1;

// get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, 0, 0, finalRadius);

// make the view visible and start the animation
        myView.setVisibility(View.VISIBLE);
        anim.start();
    }

    public void hideView(final View myView) {
        // previously visible view

// get the center for the clipping circle
        int cx = myView.getWidth() / 1;
        int cy = myView.getHeight() / 1;

// get the initial radius for the clipping circle
        float initialRadius = (float) Math.hypot(cx, cy);

// create the animation (the final radius is zero)
        Animator anim =
                ViewAnimationUtils.createCircularReveal(myView, cx, 0, initialRadius, 0);

// make the view invisible when the animation is done
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                myView.setVisibility(View.INVISIBLE);
            }
        });

// start the animation
        anim.start();

    }
}
