package com.technologies.gimick.stories.apiinterfaces;

/*
This is interface or webservice for login and signup.
*/

import com.technologies.gimick.stories.models.FlatDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserLoginSignUp {
    @GET("service.php?operation=login")
    Call<FlatDTO> signIn(@Query("email") String email, @Query("password") String password);

    @GET("service.php?operation=signUp")
    Call<FlatDTO> signUp(@Query("name") String name, @Query("email") String email, @Query("password") String password);
}
