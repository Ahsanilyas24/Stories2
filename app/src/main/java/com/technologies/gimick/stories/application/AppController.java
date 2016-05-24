package com.technologies.gimick.stories.application;

/*
This class is used for sharing data on twitter and facebook. Different kind of keys are decalred and used in this class
*/


import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.utils.Constants;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AppController extends Application {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    public static final String TWITTER_KEY = "d06r6X1E6umamR8kJk9mz8YaO";
    public static final String TWITTER_SECRET = "jPXev86MdyOoKbZbiUJrDgZKlZkH9TMCvuSJVS4kt6nYniKtvQ";


    private static final String API = Constants.IP + "/my_stories/";
    private static AppController instace;
    private static com.nostra13.universalimageloader.core.ImageLoader loader;
    private static Retrofit retrofit;

    public static AppController getInstace() {
        return instace;
    }

    public static com.nostra13.universalimageloader.core.ImageLoader getUniversalImageLoaderInstance() {
        if (loader == null) {
            DisplayImageOptions defaultOptions =
                    new DisplayImageOptions.Builder()
                            .cacheInMemory(true)
                            .cacheOnDisk(true)
                            .showImageForEmptyUri(R.drawable.transparentimage)
                            .showImageOnLoading(R.drawable.transparentimage)
                            .displayer(new FadeInBitmapDisplayer(500))
                            .build();
            ImageLoaderConfiguration mImageLoaderConfig =
                    new ImageLoaderConfiguration.Builder(instace.getApplicationContext())
                            .defaultDisplayImageOptions(defaultOptions)
                            .build();


            loader = com.nostra13.universalimageloader.core.ImageLoader.getInstance();
            loader.init(mImageLoaderConfig);

        }
        return loader;
    }

    public static Retrofit getRetroInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(API)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig),new TweetComposer());
        instace = this;
    }
}
