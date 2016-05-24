package com.technologies.gimick.stories.social;

/*
This is class for sharing stories on facebook

 */

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.technologies.gimick.stories.application.AppController;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;

import io.fabric.sdk.android.Fabric;


public class TwitterSharing {
    private final Context context;

    public TwitterSharing(Context context) {
        this.context = context;
        TwitterAuthConfig authConfig = new TwitterAuthConfig(AppController.TWITTER_KEY, AppController.TWITTER_SECRET);
        Fabric.with(context, new TwitterCore(authConfig), new TweetComposer());

    }


    public void shareTweet(String title, String descp) {
        Uri uri = Uri.parse("android.resource://com.technologies.gimick.stories/mipmap/ic_launcher");

        try {
            Intent intent = new TweetComposer.Builder(context)
                    .text(title + "\n" + descp).image(uri)
                    /*.url(new URL("http://www.twitter.com"))*/
                    .createIntent();
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
