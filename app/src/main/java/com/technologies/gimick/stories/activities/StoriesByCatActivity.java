package com.technologies.gimick.stories.activities;
/*
This is activity that shows the details of the stories by category. There are different categories of user stories in this application
 like, business, politics, sports etc.
This class fetch data from the server according to specific property.
 */


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.adapters.SwipeStoriesAdapter;
import com.technologies.gimick.stories.apiinterfaces.GetStories;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.databinding.ActivityStoriesByCatBinding;
import com.technologies.gimick.stories.models.StoriesDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StoriesByCatActivity extends AppCompatActivity implements Callback<ArrayList<StoriesDto>> {
    ActivityStoriesByCatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_stories_by_cat);
        TextView tv=(TextView) findViewById(R.id.tvNoData);
        ProgressBar pb=(ProgressBar) findViewById(R.id.pb);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Stories");
        int id = getIntent().getIntExtra("id", -1);
        if (id > -1) {
            GetStories getStories = AppController.getRetroInstance().create(GetStories.class);
            Call<ArrayList<StoriesDto>> apiCall = getStories.getStoriesByCat(id);
            apiCall.enqueue(this);
        }
    }

    @Override
    public void onResponse(Call<ArrayList<StoriesDto>> call, Response<ArrayList<StoriesDto>> response) {
        binding.innerContent.pb.setVisibility(View.GONE);
        binding.innerContent.tvNoData.setVisibility(View.GONE);
        ArrayList<StoriesDto> list = response.body();
        if (list.size()>0){
            binding.innerContent.tvNoData.setVisibility(View.GONE);
            binding.innerContent.stackView.setAdapter(new SwipeStoriesAdapter(this, list));
        }else {
            binding.innerContent.tvNoData.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onFailure(Call<ArrayList<StoriesDto>> call, Throwable t) {
        binding.innerContent.pb.setVisibility(View.GONE);
        binding.innerContent.tvNoData.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
