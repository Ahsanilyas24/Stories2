package com.technologies.gimick.stories.activities;
/*
This is a splash screen or welcome screen. It is the first screen of the application it automatically
redirect control to the main stories class after 3 seconds.


 */
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.databinding.DataBindingUtil;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.Base64;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.databinding.ActivitySplashBinding;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashActivity extends AppCompatActivity implements Animation.AnimationListener {
    ActivitySplashBinding binding;
    public int count=0;
    int tempInt = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        getKeyHash();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bounce);
        binding.contentSplash.ivMainImage.startAnimation(animation);
        animation.setAnimationListener(this);
        SpannableString spannablecontent = new SpannableString("capptv8.me");
        spannablecontent.setSpan(new StyleSpan(Typeface.BOLD),
                0, 7, 0);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        count = readSharedPreferenceInt("cntSP","cntKey");
        if(count==0){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), DiscriptionActivity.class);
            startActivity(intent);
            count++;
            writeSharedPreference(count,"cntSP","cntKey");
            this.finish();
        }
        else {
            startActivity(new Intent(this, StoriesActivity.class));
            this.finish();
        }
    }

    //Read from Shared Preferance
    public int readSharedPreferenceInt(String spName,String key){
        SharedPreferences sharedPreferences = getSharedPreferences(spName,Context.MODE_PRIVATE);
        return tempInt = sharedPreferences.getInt(key, 0);
    }

    //write shared preferences in integer
    public void writeSharedPreference(int ammount,String spName,String key ){

        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, ammount);
        editor.commit();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    private void getKeyHash(){
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.technologies.gimick.stories",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }
}
