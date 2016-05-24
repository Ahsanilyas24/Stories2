package com.technologies.gimick.stories.apiinterfaces;

/*
This is interface or webservice reference page for getting all user stories and by getting user stories by reference.
*/
import com.technologies.gimick.stories.models.StoriesDto;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface GetStories {
    @GET("service.php?operation=getAllStories")
    Call<ArrayList<StoriesDto>> getStories();

    @GET("service.php?operation=getStoriesByCat")
    Call<ArrayList<StoriesDto>> getStoriesByCat(@Query("id") int id);
}
