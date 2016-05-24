package com.technologies.gimick.stories.activities;

/* This is the main class of the project. It fetches all the stories from the server.
* All the switch cases for navigation drawer are also performed in this class
* This is the main class of the project with all the stories that is shown in first screen
* */


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.facebook.FacebookSdk;
import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.adapters.SwipeStoriesAdapter;
import com.technologies.gimick.stories.apiinterfaces.GetStories;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.databinding.ActivityStoriesMainBinding;
import com.technologies.gimick.stories.models.StoriesDto;
import com.technologies.gimick.stories.utils.AnimationUtils;
import com.technologies.gimick.stories.utils.Dialogs;
import com.technologies.gimick.stories.utils.LoginPrefrences;
import com.technologies.gimick.stories.utils.UiAlertDialog;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoriesActivity extends AppCompatActivity implements Callback<ArrayList<StoriesDto>>, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    ActivityStoriesMainBinding binding;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stories_main);
        setSupportActionBar(binding.innerMain.toolbar);
        setTitle("Flip Magazine");
        initGUI();

        FacebookSdk.sdkInitialize(getApplicationContext());
        Dialogs.getInstance(this).showProgressDialog("Getting All Stories...");
        GetStories stories = AppController.getInstace().getRetroInstance().create(GetStories.class);
        Call<ArrayList<StoriesDto>> storiesCall = stories.getStories();
        storiesCall.enqueue(this);

    }

    private void initGUI() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, binding.innerMain.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        binding.navView.setNavigationItemSelectedListener(this);
        binding.innerMain.innerContent.btnGotIt.setOnClickListener(this);
    }

    @Override
    public void onResponse(Call<ArrayList<StoriesDto>> call, Response<ArrayList<StoriesDto>> response) {
        ArrayList<StoriesDto> stories = response.body();
        Dialogs.getInstance(this).dismissProgressDialog();
        binding.innerMain.innerContent.stackView.setAdapter(new SwipeStoriesAdapter(this, stories));
        final LoginPrefrences pref = new LoginPrefrences(this);
        final boolean tutorial = pref.getFirstTimeTutorial();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!tutorial) {
                    if (Build.VERSION.SDK_INT >= 23) {
                        new AnimationUtils().showView(binding.innerMain.innerContent.rlTut);
                    } else {
                        binding.innerMain.innerContent.rlTut.setVisibility(View.VISIBLE);
                    }
                    pref.setFirstTimeTutorial();
                }
            }
        }, 200);

    }

    @Override
    public void onFailure(Call<ArrayList<StoriesDto>> call, Throwable t) {
        Dialogs.getInstance(this).dismissProgressDialog();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                startActivity(new Intent(this, StoriesActivity.class));
                break;
            case R.id.nav_SignOut:
                new LoginPrefrences(this).logOutUser();
                startActivity(new Intent(this, LoginSignUpActivity.class));
                finish();
                return true;
            case R.id.nav_cat:
                binding.drawerLayout.closeDrawers();
                startActivity(new Intent(this, CategoryActivity.class));
                return true;
            case R.id.nav_about:
                binding.drawerLayout.closeDrawers();
                startActivity(new Intent(this, DiscActivity.class));
                return true;
            case R.id.nav_ForgetPass:
                binding.drawerLayout.closeDrawers();
                UiAlertDialog.forgetPassDialog(this, this, "Forget Password");
                break;
            case R.id.nav_ResetPass:
                binding.drawerLayout.closeDrawers();
                UiAlertDialog.resetPassDialog(this, this, "Reset Password");
                break;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_tut) {
            if (flag) {
                if (Build.VERSION.SDK_INT >= 23) {
                    new AnimationUtils().hideView(binding.innerMain.innerContent.rlTut);
                } else {
                    binding.innerMain.innerContent.rlTut.setVisibility(View.INVISIBLE);
                }
                flag = false;
            } else {
                if (Build.VERSION.SDK_INT >= 23) {
                    new AnimationUtils().showView(binding.innerMain.innerContent.rlTut);
                } else {
                    binding.innerMain.innerContent.rlTut.setVisibility(View.VISIBLE);
                }
                flag = true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGotIt:
                if (Build.VERSION.SDK_INT >= 23) {
                    new AnimationUtils().hideView(binding.innerMain.innerContent.rlTut);
                } else {
                    binding.innerMain.innerContent.rlTut.setVisibility(View.INVISIBLE);
                }
                flag = false;
                break;
        }
    }
}
