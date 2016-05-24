package com.technologies.gimick.stories.activities;
/*
This is category activity this activity loads all the main categories of stories from the server and shows
them in the form of image buttons.By now it is only fetching 8 categories such as, business, news, politics etc.
But this class is dunamic design class user can add category in the server and this class will automatically show the category in
the screen.

 */
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.adapters.CatRecyclerAdapter;
import com.technologies.gimick.stories.apiinterfaces.GetCat;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.databinding.ActivityCategoryBinding;
import com.technologies.gimick.stories.models.CatDTO;
import com.technologies.gimick.stories.utils.RecyclerViewUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity implements Callback<ArrayList<CatDTO>> {
    ActivityCategoryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category);
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Categories");
        GetCat cat = AppController.getRetroInstance().create(GetCat.class);
        Call<ArrayList<CatDTO>> catApi = cat.getAllCat();
        catApi.enqueue(this);
    }

    @Override
    public void onResponse(Call<ArrayList<CatDTO>> call, Response<ArrayList<CatDTO>> response) {
        ArrayList<CatDTO> list = response.body();
        binding.innerContent.pb.setVisibility(View.GONE);
        binding.innerContent.tvNoData.setVisibility(View.GONE);
        binding.innerContent.rcvCat.setLayoutManager(RecyclerViewUtils.getVerticalLinearLayoutManager(this));
        binding.innerContent.rcvCat.setAdapter(new CatRecyclerAdapter(this, list));
    }

    @Override
    public void onFailure(Call<ArrayList<CatDTO>> call, Throwable t) {
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
