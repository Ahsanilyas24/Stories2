package com.technologies.gimick.stories.utils;

/*
This is class is used for binding images with stories

 */

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.application.AppController;


public class ImageBindingUtil {


    @BindingAdapter({"imageUrl"})
    public static void loadImage(final ImageView imageView, String url) {
        imageView.setImageResource(R.drawable.transparentimage);
        AppController.getInstace().getUniversalImageLoaderInstance().displayImage(url, imageView);
    }


}