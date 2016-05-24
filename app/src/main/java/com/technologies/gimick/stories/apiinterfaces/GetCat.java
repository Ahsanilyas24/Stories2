package com.technologies.gimick.stories.apiinterfaces;

/*
This is interface or webservice reference page for getting all user stories.
*/

import com.technologies.gimick.stories.models.CatDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetCat {
    @GET("service.php?operation=getAllCat")
    Call<ArrayList<CatDTO>> getAllCat();

}
